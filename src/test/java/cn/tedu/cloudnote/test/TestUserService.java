package cn.tedu.cloudnote.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloudnote.entity.User;
import cn.tedu.cloudnote.service.UserService;

public class TestUserService {
	private UserService service;
	@Before
	public void testUserService(){
		String[] conf = {"conf/spring-mvc.xml",
				"conf/spring-mybatis.xml"};
		ApplicationContext ctx = new ClassPathXmlApplicationContext(conf);
		service = ctx.getBean("userService",UserService.class); 	
	}
	@Test
	public void testLogin(){
		User user = service.login("demo", "123456");
		System.out.println(user);
	}
	@Test
	public void testRegist(){
		User user = service.regist("solong", "123456", "");
		System.out.println(user);
	}
}
