package com.liyun.car.hibernate.id;


/**
 * 转换器接口
 * @author niezf
 * 2011-10-31 root.nie@163.com
 */
public interface Converter {

	/**
	 * 从一个类型转换到另一个类型
	 * @param val
	 * @return
	 */
	public Object conver(Object val);
	
}
