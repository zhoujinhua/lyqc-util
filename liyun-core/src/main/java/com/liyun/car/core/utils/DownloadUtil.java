package com.liyun.car.core.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DownloadUtil {
	
	public static void download(HttpServletRequest request,
			HttpServletResponse response, File file, String displayName)
			throws IOException {
		if (!file.exists() || !file.canRead()) {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write("您下载的文件不存在！");
			return;
		}
		String userAgent = request.getHeader("User-Agent");
		boolean isIE = (userAgent != null)
				&& (userAgent.toLowerCase().indexOf("msie") != -1);
		response.reset();
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "must-revalidate, no-transform");
		response.setDateHeader("Expires", 0L);
		response.setContentType("application/x-download");
		response.setContentLength((int) file.length());
		if (isIE) {
			displayName = URLEncoder.encode(displayName, "UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ displayName + "\"");
		} else {
			displayName = new String(displayName.getBytes("UTF-8"), "ISO8859-1");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ displayName);
		}
		BufferedInputStream is = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			is = new BufferedInputStream(new FileInputStream(file));
			IOUtils.copy(is, os);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(is);
		}
	}
	
	public static Object invokeMethodMain(Object obj , String methodName) throws Exception{
		String[] part = methodName.split("\\.");
		int i = 0;
		while(i < part.length){
			String method = part[i].substring(0, 1).toUpperCase() + part[i].substring(1);
			obj = invokeMethod(obj, method);
			i++;
		}
		return obj;
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object invokeMethod(Object obj , String methodName) throws Exception{
		Class ownerClass= obj.getClass();
		methodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
		
		Method method = null;
		
		try{
			method = ownerClass.getMethod("get"+methodName);
		} catch(SecurityException e){} 
		catch (NoSuchMethodException e){
			return null;
		}
		return method.invoke(obj);
	}

	public static Workbook getWorkbook(InputStream is) throws IOException {
		Workbook wb = null;
		if (!is.markSupported()) {
			is = new PushbackInputStream(is, 8);
		}
		if (POIFSFileSystem.hasPOIFSHeader(is)) {
			// Excel2003及以下版本
			wb = (Workbook) new HSSFWorkbook(is);
		} else if (POIXMLDocument.hasOOXMLHeader(is)) {
			// Excel2007及以上版本
			wb = new XSSFWorkbook(is);
		} else {
			throw new IllegalArgumentException("你的Excel版本目前poi无法解析！");
		}
		return wb;
	}
	
	//追加
	public static File download(List<?> list,List<String> headers,File file) throws Exception{
		if(list!=null&&!list.isEmpty()){
			Workbook wb= null;
			Sheet sheet= null;
			FileInputStream fs = null;
			int nu = 0;
			boolean flag = true;
			if(!file.exists()){
				wb = new XSSFWorkbook();
				sheet= wb.createSheet();
			} else {
				flag = false;
				fs =new FileInputStream(file);
				wb=getWorkbook(fs); 
				sheet=wb.getSheetAt(0);
				nu = sheet.getLastRowNum();
			}
			OutputStream os = new FileOutputStream(file);      
			
			//字体样式
			Font font = wb.createFont();  
			font.setColor(XSSFFont.COLOR_NORMAL);
			font.setFontName("微软雅黑");
			font.setFontHeightInPoints((short) 10);
			
			//表头样式
			CellStyle cellStyle = wb.createCellStyle();//创建格式
		    cellStyle.setFont(font);
		    cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		    cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		    cellStyle.setWrapText(true);
			
			//表格样式
			CellStyle cellStyleBody = wb.createCellStyle();//创建格式
			cellStyleBody.setFont(font);
			cellStyleBody.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			cellStyleBody.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			cellStyleBody.setWrapText(true);
			
			Row row = sheet.createRow(0);
			for(int i = 0 ; i< headers.size() ;i++){
				sheet.setColumnWidth(i, 20*256);
				Cell cell = row.createCell(i, 0);
				cell.setCellStyle(cellStyle);
				cell.setCellType(XSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(headers.get(i));
			}
			for(int i=0;i<list.size();i++){
				if(!flag){
					row=sheet.createRow((short)(nu+i+1));
				} else {
					row=sheet.createRow((short)(i+1));
				}
				
				if(headers.size()==1){
					Object obj = list.get(i);
					Cell cell = row.createCell(0);
					cell.setCellStyle(cellStyleBody);
					cell.setCellType(XSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(obj==null?"":obj.toString());
				} else {
					Object[] array = (Object[]) list.get(i);
					for(int j=0;j<array.length;j++){
						Cell cell = row.createCell(j);
						cell.setCellStyle(cellStyleBody);
						cell.setCellType(XSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(array[j]==null?"":array[j].toString());
					}
				}
			}
			
			wb.write(os);
			if(!flag){
				fs.close();
			}
			os.flush();
			os.close();
		}
		
		return file;
	}
	
}

