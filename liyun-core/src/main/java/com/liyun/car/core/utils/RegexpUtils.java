package com.liyun.car.core.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具包
 */
public final class RegexpUtils {
	
	private static final Map<String,Pattern> patterns = new HashMap<String,Pattern>();
	
	public static String[] match(String regex,Integer flags,String target){
		Pattern pattern = null;
		if((pattern=patterns.get(regex)) == null){
			pattern = Pattern.compile(regex,flags==null?0:flags);
			synchronized (patterns) {
				patterns.put(regex, pattern);
			}
		}
		Matcher matcher = pattern.matcher(target);
		if(matcher.find()){
			String[] result = new String[matcher.groupCount()+1];
			for(int i=0;i<result.length;i++){
				result[i] = matcher.group(i);
			}
			return result;
		}
		return null;
	}
	
	/**
	 * 检查字符串是否匹配正则表达式
	 * @param regex
	 * @param flags
	 * @param target
	 * @return
	 */
	public static boolean isMatched(String regex,Integer flags,String target){
		String[] result = match(regex,flags,target);
		if(result!=null && result.length > 0){
			return true;
		}
		return false;
	}
	
	public static boolean isMatched(String regex,String target){
		return isMatched(regex,null,target);
	}
	/**
	 * 匹配正则，并返回所有匹配的分组
	 * @param regex
	 * @param target
	 * @return
	 */
	public static String[] match(String regex,String target){
		return match(regex,null,target);
	}
	
	/**
	 * 匹配单个正则，并提取group(1)
	 * @param regex
	 * @param flags
	 * @param target
	 * @return
	 */
	public static String matchOne(String regex,Integer flags,String target){
		String result [] = match(regex,flags,target);
		if(result!=null && result.length > 1){
			return result[1];
		}
		return null;
	}
	
	/**
	 * 匹配单个正则，并提取group(1)
	 * @param regex
	 * @param target
	 * @return
	 */
	public static String matchOne(String regex,String target){
		return matchOne(regex,null,target);
	}
}
