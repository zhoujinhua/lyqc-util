package com.liyun.car.hibernate.service.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;

import com.liyun.car.common.entity.Page;
import com.liyun.car.common.enums.OperMode;
import com.liyun.car.common.enums.OrderMode;
import com.liyun.car.common.utils.BeanInvokeUtils;
import com.liyun.car.hibernate.hibernate.CrapCriteria;
import com.liyun.car.hibernate.hibernate.HibernateSupport;
import com.liyun.car.hibernate.service.HibernateService;

public class HibernateServiceSupport extends HibernateSupport implements HibernateService{

	@Override
	public <T extends Serializable> Page<T> pageList(T t, int pn, OrderMode orderMode, String orderParam, String...params) {
		if(t == null){
			throw new RuntimeException("查询失败,bean不能为空.");
		}
		CrapCriteria<T> cri = (CrapCriteria<T>) getSession().getCriteria(t.getClass());
		if(params!=null && params.length!=0){
			cri.addRestriction1(t, OperMode.EQ, params);
		}
		if(orderMode != null){
			if(orderMode == OrderMode.ASC){
				cri.addOrder(Order.asc(orderParam));
			} else {
				cri.addOrder(Order.desc(orderParam));
			}
		}
		return cri.getResultList(pn);
	}
	@Override
	public <T extends Serializable> Page<T> pageList(T t, int pn, String...params) {
		/*if(t == null){
			throw new RuntimeException("查询失败,bean不能为空.");
		}
		if(params!=null && params.length!=0){
			return (Page<T>) getSession().getCriteria(t.getClass()).addRestriction1(t, OperMode.EQ, params).getResultList(pn);
		} else {
			return (Page<T>) getSession().getCriteria(t.getClass()).getResultList(pn);
		}*/
		return pageList(t, pn, null, null, params);
	}
	
	@Override
	public <T extends Serializable> List<T> getList(T t, String...params) {
		if(t == null){
			throw new RuntimeException("查询失败,bean不能为空.");
		}
		if(params!=null && params.length!=0){
			return (List<T>) getSession().getCriteria(t.getClass()).addRestriction1(t, OperMode.EQ, params).getResultList();
		} else {
			return (List<T>) getSession().getCriteria(t.getClass()).getResultList();
		}
	}

	@Override
	public <T extends Serializable> void saveEntity(T t) {
		getSession().persist(t);
	}

	@Override
	public <T extends Serializable> void updateEntity(T t, String... params) {
		getSession().update(t, params);
	}

	@Override
	public <T extends Serializable> void deleteEntity(T t) {
		getSession().remove(t);
	}

	@Override
	public <T extends Serializable> List<T> getEntitysByParams(T t, OperMode mode, String... params) {
		if(t == null){
			throw new RuntimeException("查询失败,bean不能为空.");
		}
		if(params!=null && params.length!=0){
			return (List<T>) getSession().getCriteria(t.getClass()).addRestriction1(t, mode, params).getResultList();
		} else {
			return (List<T>) getSession().getCriteria(t.getClass()).getResultList();
		}
	}

	@Override
	public <T extends Serializable> List<T> getEntitysByParamMap(Class<T> type, OperMode in, Map<String, Object> map) {
		if(map!=null && !map.isEmpty()){
			return (List<T>) getSession().getCriteria(type).addRestriction(in, map).getResultList();
		} else {
			return (List<T>) getSession().getCriteria(type).getResultList();
		}
	}

	@Override
	public <T extends Serializable> T getEntityById(Class<T> type, int id, boolean init) {
		T t = (T) getSession().find(type, id);
		if(init && t!=null){
			Field[] fields = type.getDeclaredFields();
			for(Field field : fields){
				if(field.getType().getSimpleName().equals("List")){
					try {
						Hibernate.initialize(BeanInvokeUtils.invokeMethod(t, field.getName()));
					} catch (Exception e) {
						throw new RuntimeException("方法调用失败");
					}
				}
			}
		}
		return t;
	}
	
	@Override
	public <T extends Serializable> T getEntityByCode(Class<T> type, String id, boolean init) {
		T t = (T) getSession().find(type, id);
		if(init && t!=null){
			Field[] fields = type.getDeclaredFields();
			for(Field field : fields){
				if(field.getType().getSimpleName().equals("List")){
					try {
						Hibernate.initialize(BeanInvokeUtils.invokeMethod(t, field.getName()));
					} catch (Exception e) {
						throw new RuntimeException("方法调用失败");
					}
				}
			}
		}
		return t;
	}

	@Override
	public <T extends Serializable> Page<T> pageList(int pn, String hql) {
		return getSession().createQuery(hql).getResultList(pn);
	}

	@Override
	public <T extends Serializable> List<T> getList(String hql) {
		return getSession().createQuery(hql).getResultList();
	}

	@Override
	public List<?> getObjByNativeSql(String sql, Integer point, Integer step) {
		if(sql == null || sql.equals("")){
			throw new RuntimeException("脚本不可为空.");
		}
		if(sql.indexOf("limit")!=-1){
			throw new RuntimeException("脚本有误,limit关键字不能存在于脚本中.");
		}
		if(point!=null && step!=null){
			String build = sql + " limit " + point*step + "," + step;
			return getSession().createNativeQuery(build).getResultList();
		} 
		return getSession().createNativeQuery(sql).getResultList();
	}

}
