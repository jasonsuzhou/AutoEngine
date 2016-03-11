package com.mh.base.web.util;

import java.util.HashMap;
import java.util.Map;

import com.mh.base.persist.util.JSON;

public class JQueryDataTableChinese implements JQueryDataTableLanguage {

	private static JQueryDataTableChinese instance = new JQueryDataTableChinese();

	private String sProcessing = "处理中...";
	private String sLengthMenu = "显示 _MENU_ 项结果";
	private String sZeroRecords = "没有匹配结果";
	private String sInfo = "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项";
	private String sInfoEmpty = "显示第 0 至 0 项结果，共 0 项";
	private String sInfoFiltered = "(由 _MAX_ 项结果过滤)";
	private String sInfoPostFix = "";
	private String sSearch = "搜索:";
	private String sUrl = "";
	private String sEmptyTable = "表中数据为空";
	private String sLoadingRecords = "载入中...";
	private String sInfoThousands = ",";
	private Map<String, String> oPaginate = new HashMap<String, String>();
	private Map<String, String> oAria = new HashMap<String, String>();

	public static JQueryDataTableChinese getInstance() {
		return instance;
	}

	private JQueryDataTableChinese() {
		oPaginate.put("sFirst", "首页");
		oPaginate.put("sPrevious", "上页");
		oPaginate.put("sNext", "下页");
		oPaginate.put("sLast", "末页");
		oAria.put("sSortAscending", ": 以升序排列此列");
		oAria.put("sSortDescending", ": 以降序排列此列");
	}

	public String getsProcessing() {
		return sProcessing;
	}

	public void setsProcessing(String sProcessing) {
		this.sProcessing = sProcessing;
	}

	public String getsLengthMenu() {
		return sLengthMenu;
	}

	public void setsLengthMenu(String sLengthMenu) {
		this.sLengthMenu = sLengthMenu;
	}

	public String getsZeroRecords() {
		return sZeroRecords;
	}

	public void setsZeroRecords(String sZeroRecords) {
		this.sZeroRecords = sZeroRecords;
	}

	public String getsInfo() {
		return sInfo;
	}

	public void setsInfo(String sInfo) {
		this.sInfo = sInfo;
	}

	public String getsInfoEmpty() {
		return sInfoEmpty;
	}

	public void setsInfoEmpty(String sInfoEmpty) {
		this.sInfoEmpty = sInfoEmpty;
	}

	public String getsInfoFiltered() {
		return sInfoFiltered;
	}

	public void setsInfoFiltered(String sInfoFiltered) {
		this.sInfoFiltered = sInfoFiltered;
	}

	public String getsInfoPostFix() {
		return sInfoPostFix;
	}

	public void setsInfoPostFix(String sInfoPostFix) {
		this.sInfoPostFix = sInfoPostFix;
	}

	public String getsSearch() {
		return sSearch;
	}

	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}

	public String getsUrl() {
		return sUrl;
	}

	public void setsUrl(String sUrl) {
		this.sUrl = sUrl;
	}

	public String getsEmptyTable() {
		return sEmptyTable;
	}

	public void setsEmptyTable(String sEmptyTable) {
		this.sEmptyTable = sEmptyTable;
	}

	public String getsLoadingRecords() {
		return sLoadingRecords;
	}

	public void setsLoadingRecords(String sLoadingRecords) {
		this.sLoadingRecords = sLoadingRecords;
	}

	public String getsInfoThousands() {
		return sInfoThousands;
	}

	public void setsInfoThousands(String sInfoThousands) {
		this.sInfoThousands = sInfoThousands;
	}

	public Map<String, String> getoPaginate() {
		return oPaginate;
	}

	public void setoPaginate(Map<String, String> oPaginate) {
		this.oPaginate = oPaginate;
	}

	public Map<String, String> getoAria() {
		return oAria;
	}

	public void setoAria(Map<String, String> oAria) {
		this.oAria = oAria;
	}

	public String toJSONString() {
		return JSON.convertToJSONString(this);
	}

}
