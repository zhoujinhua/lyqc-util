package com.liyun.car.core.utils;

import java.util.regex.Pattern;

/**
 * 校验工具类
 */
public final class CheckUtils {
	
	/**
	 * 检查是否是正整数
	 * @param val
	 * @return
	 */
	public static boolean isInteger(String val){
		return match("[0-9]+", val);
	}

	/**
	 * 检查是否是字母组成
	 * @param val
	 * @return
	 */
	public static boolean isLetter(String val){
		return match("[a-zA-Z]+", val);
	}
	
	/**
	 * 检查是否是实数
	 * @param val
	 * @return
	 */
	public static boolean isDecimal(String val){
		try{
			Double.parseDouble(val);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	/**
	 * 检查是否是一个合法的代码
	 * 合法的代码由[字母、数字、$、_、小数点、-]组成，但不能以小数点和中划线开头。
	 * @param val
	 * @return
	 */
	public static boolean isCode(String val){
		return match("[a-zA-Z0-9$_]+[a-zA-Z0-9$_\\-.]*",val);
	}
	
	/**
	 * 最大长度
	 * @param val
	 * @param len
	 * @return
	 */
	public static boolean maxLength(String val,int len){
		return val!=null && val.length()<=len;
	}
	
	/**
	 * 最小长度
	 * @param val
	 * @param len
	 * @return
	 */
	public static boolean minLength(String val,int len){
		return val!=null && val.length() >= len;
	}
	
	public static boolean match(String pattern,String value){
		if(value == null){
			return false;
		}
		return Pattern.matches(pattern, value);
	}
	
	/**
	 * 检查是否是Email
	 * @param val
	 * @return
	 */
	public static boolean isEmail(String val){
		return match("^[a-zA-Z]+[0-9a-zA-Z._]*@[a-z0-9A-Z\\-.]*[^.]$",val);
	}
	
}
