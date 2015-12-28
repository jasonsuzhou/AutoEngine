package test.com.mh.base.persist.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Random;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class MyBatisTest {
	private static String confName = "myBatisConf.xml";
	private static String confPath = null;
	private static SqlSessionFactory sessionFactory = null;
	private static SqlSession session = null;

	@BeforeClass
	public static void setupRun() throws Exception {
		confPath = MyBatisTest.class.getResource("").getPath() + File.separator + confName;
		sessionFactory = new SqlSessionFactoryBuilder().build(new FileInputStream(confPath));
		// session = sessionFactory.openSession(true);
		session = sessionFactory.openSession();
		session.getConnection().setAutoCommit(true);
	}

	@AfterClass
	public static void afterRun() throws Exception {
		if (session != null) {
			session.close();
		}
	}

	@Test
	public void testGetUserById() {
		String statement = "test.com.mh.base.persist.impl.myBatisUserMapper.getUserById";
		User user = session.selectOne(statement, 1);
		System.out.println(user.getId() + ":" + user.getName() + ":" + user.getAge());
	}

	@Test
	public void testGetAllUserList() {
		String statement = "test.com.mh.base.persist.impl.myBatisUserMapper.getAllUserList";
		List<User> userList = session.selectList(statement);
		for (User user : userList) {
			System.out.println(user.getId() + ":" + user.getName() + ":" + user.getAge());
		}
	}

	@Test
	public void testAddUser() {
		String statement = "test.com.mh.base.persist.impl.myBatisUserMapper.addUser";
		User user = new User();
		user.setName("salk" + new Random().nextInt(999));
		user.setAge(28);
		int result = session.insert(statement, user);
		System.out.println("insert " + result + " rows affected.");
	}

	@Test
	public void testUpdateUser() {
		String statement = "test.com.mh.base.persist.impl.myBatisUserMapper.updateUser";
		User user = new User();
		user.setId(1);
		user.setName("Jason");
		user.setAge(27);
		int result = session.update(statement, user);
		System.out.println("update " + result + " rows affected.");
	}

	@Test
	public void testDeleteUser() {
		String statement = "test.com.mh.base.persist.impl.myBatisUserMapper.deleteUser";
		int result = session.delete(statement, 3);
		System.out.println("delete " + result + " rows affected.");
	}

	@Test
	public void testGetOrderById() {
		String statement = "test.com.mh.base.persist.impl.myBatisOrderMapper.getOrderById";
		Order order = session.selectOne(statement, 1);
		System.out.println("Result Order:" + order.getId() + ":" + order.getOrderNo() + ":" + order.getOrderPrice());
	}
	
	@Test
	public void testSelectOrderById() {
		String statement = "test.com.mh.base.persist.impl.myBatisOrderMapper.selectOrderById";
		Order order = session.selectOne(statement, 1);
		System.out.println("Result Order:" + order.getId() + ":" + order.getOrderNo() + ":" + order.getOrderPrice());
	}
}
