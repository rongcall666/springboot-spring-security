package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import com.example.demo.dao.NodesRepository;
import com.example.demo.entity.Nodes;
/**
 *获取所有节点所需要的请求，并将资源与所需角色内容放入Security中保存，当第一执行 
 *认证拦截时，被调用，
 *之后当请求来了之后，根据请求的路径，给出该请求所需要的角色
 */
@Service
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {
	@Autowired
	private NodesRepository nodesRepository;
	private HashMap<String, Collection<ConfigAttribute>> map = null;
	/**
	 * 根据请求的url去获取该请求所需要的角色
	 */
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		if (map == null)
			loadResourceDefine();
		HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
		AntPathRequestMatcher matcher;
		String url;
		for (Iterator<String> iter = map.keySet().iterator(); iter.hasNext();) {
			url = iter.next();
			matcher = new AntPathRequestMatcher(url);
			if (matcher.matches(request)) {
				return map.get(url);
			}
		}
		return null;
	}
	/**
	 * 第一次加载时，将所有的资源信息加载入并保存
	 */
	private void loadResourceDefine() {
		ConfigAttribute cfg;
		List<Nodes> list = nodesRepository.findAll();
		map = new HashMap();
		Collection<ConfigAttribute> list2;
		for (int i = 0; i < list.size(); i++) {
			if (map.get(list.get(i).getNodeurl()) != null) {
				list2 = map.get(list.get(i).getNodeurl());
			} else {
				list2 = new ArrayList<>();
			}
			cfg = new SecurityConfig(list.get(i).getNodename());
			list2.add(cfg);
			map.put(list.get(i).getNodeurl(), list2);
		}

	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

}
