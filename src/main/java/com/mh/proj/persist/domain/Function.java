package com.mh.proj.persist.domain;

import com.mh.base.module.entity.BaseEntity;

public class Function extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8644232666967379997L;
	private String name;
	private String link;
	private int level;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
