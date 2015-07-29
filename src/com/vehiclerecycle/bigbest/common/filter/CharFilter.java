package com.vehiclerecycle.bigbest.common.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class CharFilter
 */
@WebFilter(description = "敏感词汇过滤", urlPatterns = { "/*" })
public class CharFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Enumeration<String> enumeration=request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String string = (String) enumeration.nextElement();
//			System.out.println(string+":"+request.getParameter(string));
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}
   

}
