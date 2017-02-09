package com.liyun.car.core.converter;

import java.lang.reflect.Field;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import com.liyun.car.common.entity.Dict;

public final class DictConverterFactory implements
		ConverterFactory<String, Dict> {

	public <T extends Dict> Converter<String, T> getConverter(
			Class<T> targetType) {
		return new StringToDict(targetType);
	}

	private class StringToDict<T extends Dict> implements Converter<String, T> {

		private final Class<T> dictType;

		public StringToDict(Class<T> dictType) {
			this.dictType = dictType;
		}

		public T convert(String source) {
			if (source.length() == 0) {
				return null;
			}
			Object obj = new Object();
			try {
				Field field = dictType.getDeclaredField("code");
				
				field.setAccessible(true);
				field.set(obj, source);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return (T) obj;
		}
	}

}
