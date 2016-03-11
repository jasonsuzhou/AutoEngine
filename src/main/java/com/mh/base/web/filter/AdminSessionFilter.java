package com.mh.base.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.mh.base.web.util.SessionManager;

public class AdminSessionFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String username = "test";// (String) req.getSession().getAttribute(SessionManager.SessionKey.ADMIN_USER_NAME);
		StringBuffer reuqestURL = req.getRequestURL();
		if (!reuqestURL.toString().contains("signIn")) {
			if (username == null || username.isEmpty()) {
				req.getRequestDispatcher("index").forward(request, response);
			} else {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
