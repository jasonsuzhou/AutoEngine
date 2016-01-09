package test.com.mh.base.persist.util;

import org.junit.Assert;
import org.junit.Test;

import com.mh.base.persist.util.Pager;


public class PagerTest {
	
	@Test
	public void testGetTotalPage() {
		Pager<User> pager = new Pager<User>();
		pager.setOffset(0);
		pager.setLimit(10);
		pager.setTotalCount(87);
		Assert.assertEquals(9, pager.getTotalPage());
		pager.setTotalCount(90);
		Assert.assertEquals(9, pager.getTotalPage());
		pager.setTotalCount(91);
		Assert.assertEquals(10, pager.getTotalPage());
	}
	
	@Test
	public void testGetCurrentPage() {
		Pager<User> pager = new Pager<User>();
		pager.setOffset(0);
		pager.setLimit(10);
		pager.setTotalCount(87);
		Assert.assertEquals(1, pager.getCurrentPage());
		pager.setOffset(9);
		Assert.assertEquals(1, pager.getCurrentPage());
		pager.setOffset(10);
		Assert.assertEquals(2, pager.getCurrentPage());
		pager.setOffset(11);
		Assert.assertEquals(2, pager.getCurrentPage());
	}
	
	@Test
	public void testGetOffset() {
		Pager<User> pager = new Pager<User>();
		pager.setTotalCount(87);
		pager.setCurrentPage(1);
		Assert.assertEquals(0, pager.getOffset());
		pager.setCurrentPage(2);
		Assert.assertEquals(10, pager.getOffset());
		pager.setCurrentPage(3);
		Assert.assertEquals(20, pager.getOffset());
	}

}
