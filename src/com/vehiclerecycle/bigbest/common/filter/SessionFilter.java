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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;

import com.vehiclerecycle.bigbest.util.MessageUtil;

/**
 * Servlet Filter implementation class SessionFilter
 */
@WebFilter(description = "用户session判断", urlPatterns = { "/*" })
public class SessionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SessionFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest=(HttpServletRequest) request;
		HttpSession session=httpRequest.getSession();
		Enumeration<String> enumeration=session.getAttributeNames();
		while (enumeration.hasMoreElements()) {
			String string = (String) enumeration.nextElement();
//			System.out.println(string+":"+session.getAttribute(string));
		}
		MessageUtil.setLoacle(request.getLocale());
//		System.out.println(MessageUtil.getMessage("ME_QW"));
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
