package com.mh.base.manage.service.impl;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.mh.base.manage.service.EmailService;

public class EmailServiceImpl implements EmailService {

	public static void sendHtmlEmail() throws EmailException {
		HtmlEmail email = new HtmlEmail();
		email.setHostName("smtp.163.com");
		email.setSmtpPort(25);
		//email.setAuthentication("jason.yao525@gmail.com", "storm880521");
		//email.setSSLOnConnect(true);
		email.setAuthentication("yaominhua-1314@163.com", "storm880521");
		email.setFrom("yaominhua-1314@163.com");
		//email.setMsg("This is a test mail send by java... :-)");
		email.setHtmlMsg("<html><h1>This is html text h1</h1></html>");
		email.addTo("1031897438@qq.com");
		email.setSubject("Do not reply!");
		email.send();
	}
	
	public static void main(String[] args) {
		try {
			EmailServiceImpl.sendHtmlEmail();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

