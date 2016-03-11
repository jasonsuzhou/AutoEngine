package com.mh.base.web.util;

import java.util.HashMap;
import java.util.Map;

import com.mh.base.persist.util.JSON;

public class JQueryDataTableDefault implements JQueryDataTableLanguage {

	private static JQueryDataTableDefault instance = new JQueryDataTableDefault();

	private String sProcessing = "Processing...";
	private String sLengthMenu = "Show _MENU_ entries";
	private String sZeroRecords = "No matching records found";
	private String sInfo = "Showing _START_ to _END_ of _TOTAL_ entries";
	private String sInfoEmpty = "Showing 0 to 0 of 0 entries";
	private String sInfoFiltered = "(filtered from _MAX_ total entries)";
	private String sInfoPostFix = "";
	private String sSearch = "Search:";
	private String sUrl = "";
	private String sEmptyTable = "No data available in table";
	private String sLoadingRecords = "Loading...";
	private String sInfoThousands = ",";
	private Map<String, String> oPaginate = new HashMap<String, String>();
	private Map<String, String> oAria = new HashMap<String, String>();

	public static JQueryDataTableDefault getInstance() {
		return instance;
	}

	private JQueryDataTableDefault() {
		oPaginate.put("sFirst", "First");
		oPaginate.put("sPrevious", "Previous");
		oPaginate.put("sNext", "Next");
		oPaginate.put("sLast", "Last");
		oAria.put("sSortAscending", ": activate to sort column ascending");
		oAria.put("sSortDescending", ": activate to sort column descending");
	}

	@Override
	public String toJSONString() {
		return JSON.convertToJSONString(this);
	}

}
