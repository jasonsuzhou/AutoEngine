package com.mh.base.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.UUID;

import org.springframework.util.StringUtils;

public class CommonUtils {

	public static String getErrorStackTrace(Throwable e) {
		String result = "";
		Writer writer = new StringWriter();
		PrintWriter pw = new PrintWriter(writer);
		e.printStackTrace(pw);
		result = writer.toString();
		try {
			pw.close();
			writer.close();
		} catch (Exception ex) {
			// TODO
			ex.printStackTrace();
		}
		return result;
	}

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static String getFirstCharLower(String value) {
		return StringUtils.uncapitalize(value);
	}

}
