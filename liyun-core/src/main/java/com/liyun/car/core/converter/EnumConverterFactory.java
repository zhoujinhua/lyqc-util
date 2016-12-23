package com.liyun.car.core.converter;

import java.lang.reflect.Method;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

public final class EnumConverterFactory implements
		ConverterFactory<String, Enum> {

	public <T extends Enum> Converter<String, T> getConverter(
			Class<T> targetType) {
		return new StringToEnum(targetType);
	}

	private class StringToEnum<T extends Enum> implements Converter<String, T> {

		private final Class<T> enumType;

		public StringToEnum(Class<T> enumType) {
			this.enumType = enumType;
		}

		public T convert(String source) {
			if (source.length() == 0) {
				return null;
			}
			Method method;
			try {
				method = enumType.getMethod("values", new Class[]{});
				T[] enums = (T[]) method.invoke(enumType, new Object[0]);
				
				for(T e : enums){
					if(((com.liyun.car.common.service.Enum)e).getValue().equals(source.trim())){
						return e;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}

}
