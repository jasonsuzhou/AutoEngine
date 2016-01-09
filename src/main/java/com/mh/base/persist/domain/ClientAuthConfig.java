package com.mh.base.persist.domain;

import com.mh.base.module.entity.BaseEntity;

public class ClientAuthConfig extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4268485271605690916L;

	private String appId;
	private String appSecret;
	private String nonce;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
