package com.mh.base.persist.util;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSON<T> {

	public static <T> String convertToJSONString(T object) {
		String result = null;
		OutputStream os = new ByteArrayOutputStream();
		JsonGenerator generator = null;
		try {
			generator = new ObjectMapper().getFactory().createGenerator(os, JsonEncoding.UTF8);
			generator.writeObject(object);
			result = os.toString();
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		} finally {
			if (generator != null) {
				try {
					generator.close();
				} catch (Exception e) {
					// TODO
					e.printStackTrace();
				}
			}
			try {
				os.close();
			} catch (Exception e) {
				// TODO
				e.printStackTrace();
			}
		}
		return result;
	}

	public static <T> T convertToObject(String JSONString, Class<T> classType) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(JSONString, classType);
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
			return null;
		}
	}

	public static <T> T[] convertToArray(String JSONString, Class<T[]> classType) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(JSONString, classType);
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
			return null;
		}
	}
}
