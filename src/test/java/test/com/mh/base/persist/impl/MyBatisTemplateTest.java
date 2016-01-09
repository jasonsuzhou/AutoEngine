package test.com.mh.base.persist.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mh.base.persist.util.Pager;

public class MyBatisTemplateTest {

	@Test
	public void testGetByPager() {
		UserDAO dao = new UserDAOImpl();
		User user = new User();
		user.setName("salk");
		Pager<User> pager = new Pager<User>();
		pager.setOffset(0);
		pager.setLimit(10);
		pager = dao.queryListByPager(user, pager);
		System.out.println(pager.toJSONString());
		pager.setCurrentPage(2);
		System.out.println(dao.queryListByPager(user, pager).toJSONString());
	}

	@Test
	public void testDeleteByList() {
		UserDAO dao = new UserDAOImpl();
		List<Integer> list = new ArrayList<Integer>();
		list.add(22);
		list.add(23);
		int result = dao.deleteByIds(list);
		System.out.println("MyBatisTemplateTest::testDeleteByList::" + result + " rows affected.");
	}
	
	@Test
	public void testDeleteByArray() {
		UserDAO dao = new UserDAOImpl();
		int result = dao.deleteByIds(new Integer[]{25,26});
		System.out.println("MyBatisTemplateTest::testDeleteByArray::" + result + " rows affected.");
	}

}
