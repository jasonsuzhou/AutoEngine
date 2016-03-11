package com.mh.base.web.util;

public class JQueryDataTableI18NSwitcher {

	public static String getLanguage(String key) {
		if ("Chinese".equalsIgnoreCase(key)) {
			return JQueryDataTableChinese.getInstance().toJSONString();
		}
		return JQueryDataTableDefault.getInstance().toJSONString();
	}

}
