package com.mh.base.manage.service;

public interface EmailService {

	public interface MAIL_163_SERVER_INFO {
		String HOST = "smtp.163.com";
		int PORT = 25;
		int PORT_SSL = 465;// 994
	}

	public interface MAIL_QQ_SERVER_INFO {
		String HOST = "smtp.qq.com";
		int PORT = 25;
		int PORT_SSL = 465;// 587
	}

	public interface MAIL_GMAIL_SERVER_INFO {
		String HOST = "smtp.gmail.com";
		int PORT_SSL = 465;
	}

}
