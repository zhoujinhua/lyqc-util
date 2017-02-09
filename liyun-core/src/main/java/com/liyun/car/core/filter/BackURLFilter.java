package com.liyun.car.core.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *拦截没有登陆情况下方view文件夹下的JSP页面
 * Version: 1.0
 */
public class BackURLFilter implements Filter{
	
	protected FilterConfig filterConfig = null;
	@Override
    public void init(FilterConfig filterConfig) throws ServletException {
    	this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        
        String uri = request.getServletPath()+ (request.getPathInfo() == null ? "" : request.getPathInfo());
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("loginUser");
		if(obj ==null){
			response.sendError(405);
			return;
		}
		filterChain.doFilter(servletRequest, servletResponse);
    }
    
    @Override
    public void destroy() {
    	
    }
}
