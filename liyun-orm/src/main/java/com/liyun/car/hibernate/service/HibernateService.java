package com.liyun.car.hibernate.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.liyun.car.common.entity.Page;
import com.liyun.car.common.enums.OperMode;
import com.liyun.car.common.enums.OrderMode;


public interface HibernateService {

	<T extends Serializable> Page<T> pageList(T t, int pn, OrderMode orderMode, String orderParam, String...params);
	
	public <T extends Serializable> Page<T> pageList(T t, int pn, String...params);
	
	public <T extends Serializable> Page<T> pageList(int pn, String hql);
	
	public <T extends Serializable> List<T> getList(String hql);

	<T extends Serializable> void saveEntity(T t);
	
	<T extends Serializable> void updateEntity(T t, String...params);
	
	<T extends Serializable> void deleteEntity(T t);
	/**
	 * 不支持OperMode.In操作
	 * @param t
	 * @param mode
	 * @param params
	 * @return
	 */
	<T extends Serializable> List<T> getEntitysByParams(T t, OperMode mode, String...params);
	
	/**
	 * 不支持OperMode.OR操作
	 * @param in
	 * @param map
	 * @return
	 */
	<T extends Serializable> List<T> getEntitysByParamMap(Class<T> type, OperMode in, Map<String, Object> map);
	
	<T extends Serializable> T getEntityById(Class<T> type, int id, boolean init); 

	<T extends Serializable> T getEntityByCode(Class<T> type, String id, boolean init);

	<T extends Serializable> List<T> getList(T t, String... params);

	/**
	 * 执行本地脚本，结果分步
	 * @param sql
	 * @param point
	 * @param step
	 * @return
	 */
	List<?> getObjByNativeSql(String sql, Integer point, Integer step);


}
