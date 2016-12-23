package com.liyun.car.core.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.apache.commons.io.IOUtils;

/**
 * 对象克隆
 */
public final class CloneUtils {

	/**
	 * 深克隆
	 * @param object
	 * @return
	 */
	public static Serializable deepClone(Serializable object){
		if(object == null)
			return null;
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new ByteArrayInputStream(toBytes(object)));
			return (Serializable) in.readObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			IOUtils.closeQuietly(in);
		}
	}
	
	
	/**
	 * 将对象转换到byte数组
	 * @param object
	 * @return
	 */
	public static byte[] toBytes(Serializable object){
		ByteArrayOutputStream outArray = new ByteArrayOutputStream();
		ObjectOutputStream out=null;
		try {
			out = new ObjectOutputStream(outArray);
			out.writeObject(object);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		byte [] data = outArray.toByteArray();
		IOUtils.closeQuietly(out);
		return data;
	}
}
