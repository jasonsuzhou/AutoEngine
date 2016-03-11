package com.mh.base.web.springboot.controller;

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

import com.mh.base.manage.service.AdminService;
import com.mh.base.manage.service.FunctionService;
import com.mh.base.persist.domain.Admin;
import com.mh.base.persist.domain.Function;
import com.mh.base.persist.util.JSON;
import com.mh.base.web.springboot.config.SpringMVCConfigure;
import com.mh.base.web.util.JQueryDataTableI18NSwitcher;
import com.mh.base.web.util.JSONHttpResponse;
import com.mh.base.web.util.SessionManager;

@Controller
@SessionAttributes({ SessionManager.SessionKey.ADMIN_USER_NAME })
@RequestMapping("/admin/")
public class AdminController {

	private static final Locale LOCALE = SpringMVCConfigure.getLocale();

	@Autowired
	private AdminService adminServiceImpl;
	@Autowired
	private FunctionService functionServiceImpl;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "index", method = { RequestMethod.GET })
	public String singInPage() {
		return "adminmgr/login";
	}

	@RequestMapping(value = "signIn", method = { RequestMethod.POST, RequestMethod.GET })
	public String signIn(HttpServletRequest request, Map<String, String> root) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (this.adminServiceImpl.signInCheck(username, password)) {
			request.getSession().setAttribute(SessionManager.SessionKey.ADMIN_USER_NAME, username);
			root.put(SessionManager.SessionKey.ADMIN_USER_NAME, username);

			return "adminmgr/blank";
		} else {
			root.put("error_message", "Invalid username or password.");
			return "adminmgr/login";
		}
	}

	@RequestMapping(value = "addAdmin", method = { RequestMethod.GET })
	public String addAdmin(Map<String, Object> root) {
		root.put("label_operation", getI18NString("system.global.operation.add"));
		root.put("label_modal_name", getI18NString("system.modal.admin"));
		root.put("button_submit", getI18NString("system.global.operation.submit"));
		root.put("field_admin_username", getI18NString("system.modal.admin.username"));
		root.put("field_admin_password", getI18NString("system.modal.admin.password"));
		root.put("field_admin_confirm_password", getI18NString("system.modal.admin.confirmpassword"));
		root.put("action_url", "addAdminSubmit");
		return "adminmgr/admin/add_edit";
	}

	@RequestMapping(value = "editAdmin/{id}", method = { RequestMethod.GET })
	public String editAdmin(@PathVariable int id, Map<String, Object> root) {
		root.put("label_operation", getI18NString("system.global.operation.add"));
		root.put("label_modal_name", getI18NString("system.modal.admin"));
		root.put("button_submit", getI18NString("system.global.operation.submit"));
		root.put("field_admin_username", getI18NString("system.modal.admin.username"));
		root.put("field_admin_password", getI18NString("system.modal.admin.password"));
		root.put("field_admin_confirm_password", getI18NString("system.modal.admin.confirmpassword"));
		root.put("action_url", "addAdminSubmit");
		Admin admin = this.adminServiceImpl.queryAdminById(id);
		root.put("admin", admin);
		return "adminmgr/admin/edit";
	}

	@RequestMapping(value = "addAdminSubmit", method = {
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String addAdminSubmit(@ModelAttribute Admin admin,
			@ModelAttribute(SessionManager.SessionKey.ADMIN_USER_NAME) String sesUsername) {
		admin.setCreationBy(sesUsername);
		admin.setLastUpdatedBy(sesUsername);
		try {
			int result = adminServiceImpl.addAdmin(admin);
			if (result == 10000) {
				String rtn = new JSONHttpResponse(JSONHttpResponse.ERROR,
						getI18NString("system.modal.admin.error.10000")).toJSONString();
				return rtn;
			}
		} catch (Exception e) {
			return new JSONHttpResponse(JSONHttpResponse.ERROR, e.getMessage()).toJSONString();
		}
		return new JSONHttpResponse(messageSource, JSONHttpResponse.OK).toJSONString();
	}

	@RequestMapping(value = "signOut", method = { RequestMethod.GET })
	public String signOut(HttpServletRequest request) {
		request.getSession().removeAttribute(SessionManager.SessionKey.ADMIN_USER_NAME);
		return "adminmgr/login";
	}

	@RequestMapping(value = "queryAllAdmin", method = { RequestMethod.GET })
	public String queryAllAdmin(Map<String, Object> root) {
		String tableTitle = this.messageSource.getMessage("system.global.label.list",
				new Object[] { getI18NString("system.modal.admin") }, LOCALE);
		String modifyModalTitle = this.messageSource.getMessage("system.global.label.modify",
				new Object[] { getI18NString("system.modal.admin") }, LOCALE);
		root.put("jquery_data_table_language",
				JQueryDataTableI18NSwitcher.getLanguage(getI18NString("jquery.datatable.language")));
		root.put("admin_list_table_title", tableTitle);
		root.put("operation_delete", this.getI18NString("system.global.operation.delete"));
		root.put("operation_edit", getI18NString("system.global.operation.edit"));
		root.put("operation_add", getI18NString("system.global.operation.add"));
		root.put("button_ok", getI18NString("system.global.button.ok"));
		root.put("button_cancel", getI18NString("system.global.button.cancel"));
		root.put("button_savechange", getI18NString("system.global.button.savechange"));
		root.put("delete_modal_title", getI18NString("system.global.alert.delete"));
		root.put("delete_modal_body", getI18NString("system.global.alert.delete.confirmation"));
		root.put("modify_modal_title", modifyModalTitle);
		List<String> lsColumnName = new ArrayList<String>();
		lsColumnName.add(this.getI18NString("system.global.operation.actions"));
		lsColumnName.add("db-key");
		lsColumnName.add(this.getI18NString("system.modal.admin.username"));
		lsColumnName.add(this.getI18NString("system.modal.admin.password"));
		lsColumnName.add(this.getI18NString("system.modal.admin.salt"));
		root.put("adminColumnNameList", lsColumnName);

		List<Admin> lsAdmin = adminServiceImpl.queryAllAdmin();
		root.put("adminList", lsAdmin);
		return "adminmgr/admin/query_list";
	}

	@RequestMapping(value = "delAdmin/{id}", method = {
			RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String delAdminById(@PathVariable int id) {
		try {
			this.adminServiceImpl.deleteAdminById(id);
		} catch (Exception e) {
			return new JSONHttpResponse(JSONHttpResponse.ERROR, e.getMessage()).toJSONString();
		}
		return new JSONHttpResponse(messageSource, JSONHttpResponse.OK).toJSONString();
	}

	@RequestMapping(value = "addFunc", method = { RequestMethod.GET })
	public String addFunc(Map<String, Object> root) {
		root.put("label_operation", getI18NString("system.global.operation.add"));
		root.put("label_modal_name", getI18NString("system.modal.function"));
		root.put("button_submit", getI18NString("system.global.operation.submit"));
		root.put("field_function_name", getI18NString("system.modal.function.name"));
		root.put("field_function_link", getI18NString("system.modal.function.link"));
		root.put("function_level_1", getI18NString("system.modal.function.level.1"));
		root.put("function_level_2", getI18NString("system.modal.function.level.2"));
		root.put("function_level_3", getI18NString("system.modal.function.level.3"));
		root.put("label_select_2", getI18NString("system.modal.function.label.select", getI18NString("system.modal.function.level.2")));
		root.put("label_select_3", getI18NString("system.modal.function.label.select", getI18NString("system.modal.function.level.3")));
		return "adminmgr/function/add";
	}

	@RequestMapping(value = "addFunctionSubmit", method = {
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String addFunctionSubmit(@ModelAttribute Function function,
			@ModelAttribute(SessionManager.SessionKey.ADMIN_USER_NAME) String sesUsername) {
		function.setCreationBy(sesUsername);
		function.setLastUpdatedBy(sesUsername);
		try {
			int result = functionServiceImpl.addFunction(function);
			if (result > 10000) {
				String rtn = new JSONHttpResponse(JSONHttpResponse.ERROR,
						getI18NString("system.modal.function.error." + result)).toJSONString();
				return rtn;
			}
		} catch (Exception e) {
			return new JSONHttpResponse(JSONHttpResponse.ERROR, e.getMessage()).toJSONString();
		}
		return new JSONHttpResponse(messageSource, JSONHttpResponse.OK).toJSONString();
	}
	
	@RequestMapping(value = "getFunctionList/{level}", method = {
			RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String getFunctionList(@PathVariable int level) {
		List<Function> funcList = this.functionServiceImpl.getFunctionList(level);
		return JSON.convertToJSONString(funcList);
	}

	private String getI18NString(String code) {
		return messageSource.getMessage(code, null, LOCALE);
	}

	private String getI18NString(String code, String param) {
		return messageSource.getMessage(code, new Object[] { param }, LOCALE);
	}

}
