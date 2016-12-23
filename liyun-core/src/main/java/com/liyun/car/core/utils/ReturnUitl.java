package com.liyun.car.core.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReturnUitl{
	
	private static final DecimalFormat DFDZH = new DecimalFormat("00000");
	
	private static Logger log  = LoggerFactory.getLogger(ReturnUitl.class);

	/**
	 * 
	 * @param response
	 * @param status 0：成功  1：失败
	 */
	public static void write(HttpServletResponse response, int status) {
		PrintWriter w = null;
		StringBuffer sb = new StringBuffer();
		try {
			 w = response.getWriter();
			 sb.append("{\"responseCode\":\"");
			 sb.append(status);
			 sb.append("\"}");
			 w.print(sb.toString());
		} catch (IOException e) {
			 e.printStackTrace();
		}finally{
			if(w!=null)w.close();
		}
		
		
	}

	/**
	 * 
	 * @param response
	 * @param status
	 * @param resopnseMsg
	 */
	public static void write(HttpServletResponse response, int status,
			String resopnseMsg) {
		PrintWriter w = null;
		StringBuffer sb = new StringBuffer();
		try {
			 w = response.getWriter();
			 
			 sb.append("{\"responseCode\":\"");
			 sb.append(status);
			 sb.append("\"");
			 
			 sb.append(",");
			 
			 sb.append("\"responseMsg\":\"");
			 sb.append(resopnseMsg);
			 sb.append("\"");
			 
			 sb.append("}");
			 w.print(sb.toString());
		} catch (IOException e) {
			 e.printStackTrace();
		}finally{
			if(w!=null)w.close();
		}
		
	}
	
	
	/**
	 * 
	 * @param response
	 * @param status
	 * @param maxId  返回内容
	 * @param resopnseMsg
	 * @throws IOException
	 */
	public static void write(HttpServletResponse response, int status, String maxId,
			String resopnseMsg) throws IOException {
		PrintWriter w = null;
		StringBuffer sb = new StringBuffer();
		w = response.getWriter();
		 
		sb.append("{\"responseCode\":\"");
		sb.append(status);
		sb.append("\"");
		 
		sb.append(",");
		 
		sb.append("\"content\":\"");
		sb.append(maxId);
		sb.append("\"");
		sb.append(",");
		
		sb.append("\"responseMsg\":\"");
		sb.append(resopnseMsg);
		sb.append("\"");
		 
		sb.append("}");
		w.print(sb.toString());
		w.close();
		
	}
}
