package cn.tedu.cloudnote.test;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloudnote.dao.NotebookDao;
import cn.tedu.cloudnote.entity.Notebook;

public class TestNotebookDao {
	NotebookDao notebookDao;
	@Before
	public void init(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		notebookDao=ctx.getBean("notebookDao",NotebookDao.class);
	}
	@Test
	public void testFindNotebooksByUserId(){
		String userId="52f9b276-38ee-447f-a3aa-0d54e7a736e4";
		List<Map<String,Object>> notebooks=notebookDao.findNotebooksByUserId(userId);
		for(Map<String,Object> notebook:notebooks){
			System.out.println(notebook);
		}
	}
	@Test
	public void testFindNotebookById(){
		String notebookId = "0037215c-09fe-4eaa-aeb5-25a340c6b39b";
		Notebook notebook = notebookDao.findNotebookById(notebookId);
		System.out.println(notebook);
	}
}
