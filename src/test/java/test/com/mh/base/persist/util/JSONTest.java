package test.com.mh.base.persist.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.mh.base.persist.util.JSON;
import com.mh.base.web.util.JQueryDataTableChinese;
import com.mh.base.web.util.JSONHttpResponse;

public class JSONTest {

	@Test
	public void testSingleBeanToString() {
		String result = JSON.convertToJSONString(prepareUser());
		System.out.println(result);
		Assert.assertEquals("{\"id\":1,\"name\":\"jason\",\"role\":\"admin\"}", result);
	}
	
	@Test
	public void testSingleBeanToString2() {
		String result = JSON.convertToJSONString(prepareUser2());
		System.out.println(result);
	}
	
	@Test
	public void testJSONHttpResponse() {
		String rtn = new JSONHttpResponse(JSONHttpResponse.ERROR,
				"测试中文").toJSONString();
		System.out.println(rtn);
	}

	@Test
	public void testStringToBean() {
		String JSONString = "{\"id\":1,\"name\":\"jason\",\"role\":\"admin\"}";
		User user = JSON.convertToObject(JSONString, User.class);
		Assert.assertEquals(1, user.getId());
		Assert.assertEquals("jason", user.getName());
		Assert.assertEquals("admin", user.getRole());
	}

	@Test
	public void testStringToMap() {
		String JSONString = "{\"id\":1,\"name\":\"jason\",\"role\":\"admin\"}";
		Map map = JSON.convertToObject(JSONString, Map.class);
		Set set = map.entrySet();
		Iterator it = set.iterator();
		Entry entry = null;
		while (it.hasNext()) {
			entry = (Entry) it.next();
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
	}
	
	@Test
	public void testStringToArray() {
		String JSONString = "[{\"id\":2,\"name\":\"sunny\",\"role\":\"user\"},{\"id\":3,\"name\":\"salk\",\"role\":\"admin\"}]";
		User[] userArray = JSON.convertToArray(JSONString, User[].class);
		for (int i=0;i<userArray.length;i++) {
			User user = userArray[i];
			System.out.println("id:"+user.getId()+";name:"+user.getName()+";role:"+user.getRole());
		}
	}

	@Test
	public void testListBeanToString() {
		String result = JSON.convertToJSONString(prepareListUser());
		System.out.println(result);
	}

	@Test
	public void testMapObjectToString() {
		Map<String, List<User>> map = new HashMap<String, List<User>>();
		map.put("UserList", prepareListUser());
		String result = JSON.convertToJSONString(map);
		System.out.println(result);
	}

	@Test
	public void testMapToString() {
		Map map = new HashMap();
		map.put(1, "id");
		map.put("name", "jason");
		map.put("role", "admin");
		map.put("age", 18);
		String result = JSON.convertToJSONString(map);
		System.out.println(result);
	}

	@Test
	public void testMapContainsBeanToString() {
		Map map = new HashMap();
		map.put(1, "id");
		map.put("name", "jason");
		map.put("role", "admin");
		map.put("age", 18);
		map.put("Mentee", prepareUser());
		String result = JSON.convertToJSONString(map);
		System.out.println(result);
	}

	@Test
	public void testMapContainsListToString() {
		Map map = new HashMap();
		map.put(1, "id");
		map.put("name", "jason");
		map.put("role", "admin");
		map.put("age", 18);
		map.put("Mentee", prepareListUser());
		String result = JSON.convertToJSONString(map);
		System.out.println(result);
	}

	private User prepareUser() {
		return new User(1, "jason", "admin");
	}
	
	private User prepareUser2() {
		return new User(1, "詹森", "管理员");
	}

	private List<User> prepareListUser() {
		List<User> listUser = new ArrayList<User>();
		listUser.add(new User(2, "sunny", "user"));
		listUser.add(new User(3, "salk", "admin"));
		return listUser;
	}
	
	@Test
	public void testJQueryDataTableLanguageJSON() {
		JQueryDataTableChinese language = JQueryDataTableChinese.getInstance();
		System.out.println(language.toJSONString());
	}
}
