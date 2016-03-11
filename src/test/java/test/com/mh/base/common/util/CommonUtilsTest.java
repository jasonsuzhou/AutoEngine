package test.com.mh.base.common.util;

import org.junit.Assert;
import org.junit.Test;

import com.mh.base.common.util.CommonUtils;

public class CommonUtilsTest {

	@Test
	public void testGetUUID() {
		System.out.println(CommonUtils.getUUID());
		Assert.assertEquals(32, CommonUtils.getUUID().length());
	}

}
