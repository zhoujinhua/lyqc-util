package com.liyun.car.common.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 从配置文件取值
 * 
 * @author zhoufei
 *
 */
public class PropertyUtil {
	private static final Logger logger = LoggerFactory.getLogger(PropertyUtil.class);

	private static PropertyUtil config;
	private Properties prop = new Properties();

	public PropertyUtil(InputStreamReader fileStream) {
		try {
			prop.load(fileStream);
		} catch (IOException e) {
			logger.error("加载配置 文件出错!", e);
		}
	}

	public static synchronized PropertyUtil getInstance(String path) {
		try {
			if (config == null) {
				InputStreamReader in = new InputStreamReader(PropertyUtil.class.getResourceAsStream(path), "UTF-8");
				config = new PropertyUtil(in);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return config;
	}

	public static String getPropertyValue(String key) {
		return (String) getInstance("/store/store.properties").prop.get(key);
	}

	public static String getPropertyValue(String key, String defaultValue) {
		return (String) getInstance("/store/store.properties").prop.getProperty(key, defaultValue);
	}
}
