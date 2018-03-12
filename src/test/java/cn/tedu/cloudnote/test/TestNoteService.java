package cn.tedu.cloudnote.test;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloudnote.entity.Note;
import cn.tedu.cloudnote.service.NoteService;

public class TestNoteService extends TestBase{
	NoteService service;

	@Before
	public void initTest(){
		service = ctx.getBean("noteService",NoteService.class);
	}
	@Test
	public void testFindAll(){
		String notebookId = "0037215c-09fe-4eaa-aeb5-25a340c6b39b";
		List<Map<String,Object>> noteList = service.listNotes(notebookId);
		System.out.println(noteList);
	}
	@Test
	public void testFindOne(){
		String noteId = "019cd9e1-b629-4d8d-afd7-2aa9e2d6afe0";
		Note note = service.getNote(noteId);
		System.out.println(note);
	}
	@Test
	public void testUpdate(){
		String id = "019cd9e1-b629-4d8d-afd7-2aa9e2d6afe0";
		String title = "Test";
		String body = "今天天气不错,哈哈哈";
		boolean b = service.update(id, title, body);
		Note note = service.getNote(id);
		System.out.println(b);
		System.out.println(note);
	}
}
