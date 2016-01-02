package com.mh.base.persist.util;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mh.proj.persist.domain.DonotRemove;

public class MyBatisHelper {
	private static String confName = "myBatisConf.xml";
	private static String confPath = null;
	private static SqlSessionFactory sessionFactory = null;
	private static SqlSession session = null;
	private static boolean isInit = false;

	public static SqlSession getSession(boolean autoCommit) {
		InputStream in = null;
		try {
			confPath = DonotRemove.class.getResource("").getPath() + confName;
			in = new FileInputStream(confPath);
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		}
		if (!isInit) {
			sessionFactory = new SqlSessionFactoryBuilder().build(in);
			session = sessionFactory.openSession(autoCommit);
			isInit = true;
		}
		return session;

	}
}
