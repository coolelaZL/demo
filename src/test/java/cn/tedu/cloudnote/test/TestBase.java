package cn.tedu.cloudnote.test;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloudnote.service.NotebookService;

public class TestBase {
	protected ApplicationContext ctx;
	public TestBase() {
		super();
	}

	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext("conf/spring-mybatis.xml",
			"conf/spring-mvc.xml");
	}

}