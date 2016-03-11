package com.mh.base.web.util;

import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;

import com.mh.base.persist.util.JSON;
import com.mh.base.web.springboot.config.SpringMVCConfigure;

public class JSONHttpResponse {

	public static final int OK = 200;
	public static final int ERROR = 500;

	public int status;

	private String message;
	
	private MessageSource messageSource;

	public JSONHttpResponse(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public JSONHttpResponse(MessageSource messageSource, int status) {
		this.messageSource = messageSource;
		this.status = status;
	}

	public JSONHttpResponse(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		if (StringUtils.isEmpty(this.message)) {
			switch (status) {
			case OK:
				return messageSource.getMessage("system.global.alert.success", null, SpringMVCConfigure.getLocale());
			}
		}
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String toJSONString() {
		return JSON.convertToJSONString(this);
	}

}
