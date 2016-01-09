package com.mh.base.persist.util;

import java.util.ArrayList;
import java.util.List;

public class Pager<T> {

	private long totalCount;
	private int totalPage;
	private int currentPage;
	private int previousPage;
	private int nextPage;
	private int offset = 0;
	private int limit = 10;
	private List<T> resultSet = new ArrayList<T>();

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		long tempCount = this.totalCount / this.limit;
		long modResult = this.totalCount % this.limit;
		this.totalPage = (int) (modResult == 0 ? tempCount : tempCount + 1);
		return this.totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		this.currentPage = (offset / 10) + 1;
		return this.currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.offset = (currentPage - 1) * limit;
		this.currentPage = currentPage;
	}

	public int getPreviousPage() {
		this.previousPage = this.currentPage == 1 ? -1 : this.currentPage - 1;
		return this.previousPage;
	}

	public void setPreviousPage(int previousPage) {
		this.previousPage = previousPage;
	}

	public int getNextPage() {
		this.nextPage = this.currentPage == this.totalPage ? -1 : this.currentPage + 1;
		return this.nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public List<T> getResultSet() {
		return resultSet;
	}

	public void setResultSet(List<T> resultSet) {
		this.resultSet = resultSet;
	}

	public void addResultSet(T result) {
		resultSet.add(result);
	}

	public String toJSONString() {
		return JSON.convertToJSONString(this);
	}

}