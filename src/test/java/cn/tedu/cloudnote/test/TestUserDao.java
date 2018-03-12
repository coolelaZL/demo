package cn.tedu.cloudnote.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloudnote.dao.UserDao;
import cn.tedu.cloudnote.entity.User;

public class TestUserDao {
	UserDao dao;
	@Before
	public void init(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		dao = ctx.getBean("userDao",UserDao.class);
	}
	@Test
	public void testFindByName(){
		User user = dao.findByName("demo");
		System.out.println(user);
	}
	@Test
	public void testAddUser(){
		User user = new User("2","soro","123456","123","123");
		dao.addUser(user);
	}
	@Test
	public void testFindUserById(){
		User user = dao.findUserById("52f9b276-38ee-447f-a3aa-0d54e7a736e4");
		System.out.println(user);
	}
}
