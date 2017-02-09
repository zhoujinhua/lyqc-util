package com.liyun.car.hibernate.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestUtils;

import com.liyun.car.common.entity.Page;

public class ServletPageContext<T> {
	
	private Page<T> page;

	private ServletPageContext(){
		page = new Page<T>();
	}
	
	public static <T> ServletPageContext<T> getContext(){
		return new ServletPageContext<T>();
	}
	
	public void setCurrentPage(Page<T> page){
		this.page = page;
	}
	
	@SuppressWarnings("rawtypes")
	public Page getCurrentPage(HttpServletRequest request){
		int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
		page.setNum(pn);
		
		return page;
	}
}
