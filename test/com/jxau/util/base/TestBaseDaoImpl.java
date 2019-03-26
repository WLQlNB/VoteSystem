package com.jxau.util.base;

import org.junit.Assert;
import org.junit.Test;

import com.jxau.dao.UserDao;
import com.jxau.dao.impl.UserDaoImpl;
import com.jxau.pojo.User;
import com.jxau.pojo.UserQueryModel;
import com.jxau.util.format.Md5Class;

import java.util.List;

/**
 * 测试类：
 * 1> 包名和目标类的包名一致
 * 2> 类名 = "Test"+目标类类名
 * @author Ma Wenhai
 *
 */
public class TestBaseDaoImpl {
	@Test
	public void testInsert() throws Exception{
		User user = new User();
		user.setName("三");
		user.setPwd(Md5Class.stringToMd5("123456"));
		
		UserDao userDao = new UserDaoImpl();
		int actual = userDao.insert(user);
		int expected=1;
		Assert.assertEquals(expected, actual);
		
	}
	
	@Test
	public void testGetAll() throws Exception{
		UserDao userDao = new UserDaoImpl();
		List actual = userDao.getAll();
		int expected=1;
		Assert.assertEquals(expected,actual.size());
	}
}
	
