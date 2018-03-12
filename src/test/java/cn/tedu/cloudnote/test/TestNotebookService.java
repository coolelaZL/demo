package cn.tedu.cloudnote.test;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloudnote.service.NotebookService;

public class TestNotebookService extends TestBase {
	NotebookService notebookService;
	

	@Test
	public void test(){
		notebookService=ctx.getBean("notebookService",NotebookService.class);
		String userId = "52f9b276-38ee-447f-a3aa-0d54e7a736e4";
		List<Map<String,Object>> notebooks = notebookService.listNotebooks(userId);
		for(Map<String,Object> notebook:notebooks){
			System.out.println(notebook);
		}
	}
}
