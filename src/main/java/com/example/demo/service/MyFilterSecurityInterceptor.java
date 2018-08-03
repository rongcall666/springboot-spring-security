package com.example.demo.service;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;

/**
 * 自定义拦截器，添加决策管理器以及资源信息
 *
 */

@Service
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

	@Autowired
	private MyInvocationSecurityMetadataSourceService myFilterSecurityInterceptor;

	/**
	 * 设置角色认证类
	 */
	@Autowired
	public void setMyAccessDecisionManager(MyAccessDecisionManager myAccessDecisionManager) {
		super.setAccessDecisionManager(myAccessDecisionManager);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		invoke(fi);
	}
	/**
	 * 拦截器执行完，后续拦截器继续执行
	 */
	private void invoke(FilterInvocation fi) throws IOException, ServletException {
		InterceptorStatusToken token = super.beforeInvocation(fi);
		try {
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}
	}

	@Override
	public void destroy() {

	}

	@Override
	public Class<?> getSecureObjectClass() {
		return FilterInvocation.class;
	}
	/**
	 * 资源与角色对应的
	 */
	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		  return this.myFilterSecurityInterceptor;
	}

}
