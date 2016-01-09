package test.com.mh.base.persist.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mh.base.persist.impl.MyBatisTemplate;

public class UserDAOImpl extends MyBatisTemplate<User, Integer> implements UserDAO {

	@Override
	public String getSimpleClassName() {
		return "User";
	}

	@Override
	public SqlSession getSession() {
		String confPath = UserDAOImpl.class.getResource("").getPath() + File.separator + "myBatisConf.xml";
		SqlSessionFactory sessionFactory = null;
		SqlSession session = null;
		try {
			sessionFactory = new SqlSessionFactoryBuilder().build(new FileInputStream(confPath));
			session = sessionFactory.openSession(true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return session;
	}

	@Override
	public String getMapperLocatedPackage() {
		return "test.com.mh.base.persist.impl";
	}

}
