package com.mh.base.persist.domain;

import com.mh.base.module.entity.BaseEntity;

public class Function extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1774554945876564682L;

	private String name = null;
	private String link = null;
	private int parent = 0;
	private int level = 0;

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

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
