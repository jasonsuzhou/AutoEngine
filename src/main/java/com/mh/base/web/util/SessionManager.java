package com.mh.base.web.util;

import javax.servlet.http.HttpServletRequest;

public class SessionManager {

	public interface SessionKey {
		String ADMIN_USER_NAME = "ADMIN_SESSION_USER_NAME";
	}

	public String getAdminUsername(HttpServletRequest request) {
		return (String) request.getSession().getAttribute(SessionKey.ADMIN_USER_NAME);
	}

}
