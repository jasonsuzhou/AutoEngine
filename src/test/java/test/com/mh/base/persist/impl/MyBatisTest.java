package test.com.mh.base.persist.impl;

import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
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
	public void testGetUserByCondition1() {
		String statement = "test.com.mh.base.persist.impl.myBatisUserMapper.getUserByCondition";
		User user = new User();
		user.setName("salk");
		List<User> userList = session.selectList(statement, user);
		System.out.println(userList.size());
	}

	@Test
	public void testGetUserByPager() throws SQLException {
		String statement = "test.com.mh.base.persist.impl.myBatisUserMapper.getUserByPager";
		User user = new User();
		user.setName("salk");
		int start = 0;
		int size = 5;
		MappedStatement mst = session.getConfiguration().getMappedStatement(statement);
		BoundSql boundSql = mst.getBoundSql(user);
		String sql = " select count(*) total_count from (" + boundSql.getSql() + ") t ";
		PreparedStatement pstmt = session.getConnection().prepareStatement(sql);
		setParameters(pstmt, mst, boundSql, user);
		long count = 0;
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			count = rs.getLong("total_count");
		}
		rs.close();
		pstmt.close();
		RowBounds rows = new RowBounds(start, size);
		List<User> userList = session.selectList(statement, user, rows);
		for (User user1 : userList) {
			System.out.println(user1.getId());
		}
		System.out.println("total count:" + count);
	}

	private static void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql,
			Object parameterObject) throws SQLException {
		ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		if (parameterMappings != null) {
			Configuration configuration = mappedStatement.getConfiguration();
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)
							&& boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value)
									.getValue(propertyName.substring(prop.getName().length()));
						}
					} else {
						value = metaObject == null ? null : metaObject.getValue(propertyName);
					}
					TypeHandler typeHandler = parameterMapping.getTypeHandler();
					if (typeHandler == null) {
						throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName
								+ " of statement " + mappedStatement.getId());
					}
					typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
				}
			}
		}
	}

	@Test
	public void testGetUserByCondition2() {
		String statement = "test.com.mh.base.persist.impl.myBatisUserMapper.getUserByCondition";
		User user = new User();
		user.setAge(20);
		List<User> userList = session.selectList(statement, user);
		System.out.println(userList.size());
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
	public void testAddUserSelective() {
		String statement = "test.com.mh.base.persist.impl.myBatisUserMapper.addUserSelective";
		User user = new User();
		user.setName("salk" + new Random().nextInt(999));
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
	public void testUpdateUserByIdSelectiveNoName() {
		String statement = "test.com.mh.base.persist.impl.myBatisUserMapper.updateUserByIdSelective";
		User user = new User();
		user.setId(1);
		user.setAge(20);
		int result = session.update(statement, user);
		System.out.println("update " + result + " rows affected.");
	}

	@Test
	public void testUpdateUserByIdSelectiveNoAge() {
		String statement = "test.com.mh.base.persist.impl.myBatisUserMapper.updateUserByIdSelective";
		User user = new User();
		user.setId(1);
		user.setName("Jason");
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
	public void testDeleteUserList() {
		String statement = "test.com.mh.base.persist.impl.myBatisUserMapper.deleteUserList";
		List<Integer> list = new ArrayList<Integer>();
		list.add(2);
		list.add(4);
		int result = session.delete(statement, list);
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
