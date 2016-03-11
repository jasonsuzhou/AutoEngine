package com.mh.base.persist.domain;

import com.mh.base.common.util.CommonUtils;
import com.mh.base.module.entity.BaseEntity;

public class Admin extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8917758340577538394L;
	private String username;
	private String password;
	private String salt;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public void initSalt() {
		this.salt = CommonUtils.getUUID();
	}

}
