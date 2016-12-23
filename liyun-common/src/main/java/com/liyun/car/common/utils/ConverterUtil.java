package com.liyun.car.common.utils;

import java.io.UnsupportedEncodingException;

public class ConverterUtil {

	/**
	 * URLDecoder,UTF-8
	 * @param encoder
	 * @return
	 */
	public static String URLDecoder(String encoder) { 
		try {
			encoder = java.net.URLDecoder.decode(encoder,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("url解码异常,encoder["+encoder+"]");
			e.printStackTrace();
		}
	    return encoder;
	} 
	/**
	 * decodeUnicode
	 * @param theString
	 * @return
	 */
	public static String decodeUnicode(String theString) {      
	    char aChar;      
	    int len = theString.length();      
	    StringBuffer outBuffer = new StringBuffer(len);      
	    for (int x = 0; x < len;) {      
	     aChar = theString.charAt(x++);      
	     if (aChar == '\\') {      
	      aChar = theString.charAt(x++);      
	      if (aChar == 'u') {      
	       // Read the xxxx      
	       int value = 0;      
	       for (int i = 0; i < 4; i++) {      
	        aChar = theString.charAt(x++);      
	        switch (aChar) {      
	        case '0':      
	        case '1':      
	        case '2':      
	        case '3':      
	        case '4':      
	        case '5':      
	         case '6':      
	          case '7':      
	          case '8':      
	          case '9':      
	           value = (value << 4) + aChar - '0';      
	           break;      
	          case 'a':      
	          case 'b':      
	          case 'c':      
	          case 'd':      
	          case 'e':      
	          case 'f':      
	           value = (value << 4) + 10 + aChar - 'a';      
	          break;      
	          case 'A':      
	          case 'B':      
	          case 'C':      
	          case 'D':      
	          case 'E':      
	          case 'F':      
	           value = (value << 4) + 10 + aChar - 'A';      
	           break;      
	          default:      
	           throw new IllegalArgumentException(      
	             "Malformed   \\uxxxx   encoding.");      
	          }      
	        }      
	         outBuffer.append((char) value);      
	        } else {      
	         if (aChar == 't')      
	          aChar = '\t';      
	         else if (aChar == 'r')      
	          aChar = '\r';      
	         else if (aChar == 'n')      
	          aChar = '\n';      
	         else if (aChar == 'f')      
	          aChar = '\f';      
	         outBuffer.append(aChar);      
	        }      
	       } else     
	       outBuffer.append(aChar);      
	      }      
	      return outBuffer.toString();      
	  }
	/**
	 * decodeUnicode,null变为""
	 * @param str
	 * @return
	 */
	public static String decodeUnicodeAny(String str){
		if ("null" == str || null==str)
			return "";
		else
			return decodeUnicode(str);
	}
	
	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static Double decodeUnicodeAny(Object obj){
		if ("null" == obj || null==obj)
			return 0d;
		else
			return (Double) obj;
	}
	
	/**
	 * obj转换为double类型  null则变为0
	 * @param obj
	 * @return
	 */
	public static Double convert(Object obj){
		return (Double) (obj==null?0d:obj);
	}
	
	/**
	 * null转为"" 其他转为string类型
	 * @param obj
	 * @return
	 */
	public static String convertToString(Object obj){
		return (obj==null?"":String.valueOf(obj));
	}
	
}
