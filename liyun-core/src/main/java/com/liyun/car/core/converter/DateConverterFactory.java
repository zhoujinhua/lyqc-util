package com.liyun.car.core.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

public final class DateConverterFactory implements
		ConverterFactory<String, java.util.Date> {
	private SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat SDF=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public <T extends Date> Converter<String, T> getConverter(
			Class<T> targetType) {
		return new StringToDate(targetType);
	}

	private class StringToDate<T extends Date> implements Converter<String, T> {

		private final Class<T> dateType;

		public StringToDate(Class<T> dateType) {
			this.dateType = dateType;
		}

		public T convert(String source) {
			if (source.length() == 0) {
				return null;
			}
			try {
				return (T) formater.parse(source.trim());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

}
