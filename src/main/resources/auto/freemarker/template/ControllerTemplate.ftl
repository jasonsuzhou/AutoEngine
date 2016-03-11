package com.mh.proj.web.springboot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mh.base.web.util.JQueryDataTableI18NSwitcher;
import com.mh.base.web.util.JSONHttpResponse;
import com.mh.base.web.util.SessionManager;

import com.mh.proj.manage.service.${simpleClassName}Service;
import com.mh.proj.persist.domain.${simpleClassName};
import com.mh.proj.web.springboot.config.SpringMVCConfigure;
import com.mh.proj.persist.domain.${simpleClassName};

@Controller
@SessionAttributes({ SessionManager.SessionKey.ADMIN_USER_NAME })
@RequestMapping("/admin/")
public class ${simpleClassName}Controller {

    @RequestMapping(value = "add${simpleClassName}", method = { RequestMethod.GET })
	public String add${simpleClassName}(Map<String, Object> root) {
		root.put("label_operation", getI18NString("system.global.operation.add");
		root.put("label_modal_name", getI18NString("system.modal.{lowerSimpleClassName}");
		root.put("button_submit", getI18NString("system.global.operation.submit");
		return "adminmgr/{lowerSimpleClassName}/add";
	}

	@RequestMapping(value = "add${simpleClassName}Submit", method = { RequestMethod.POST })
	public @ResponseBody String add${simpleClassName}Submit(Map<String, Object> root) {
		return getI18NString("system.global.alert.success");
	}

	private String getI18NString(String code) {
		return messageSource.getMessage(code, null, LOCALE);
	}

}