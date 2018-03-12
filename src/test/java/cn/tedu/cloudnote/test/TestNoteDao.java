package cn.tedu.cloudnote.test;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloudnote.dao.NoteDao;
import cn.tedu.cloudnote.entity.Note;

public class TestNoteDao {
	NoteDao noteDao;
	@Before
	public void init(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		noteDao=ctx.getBean("noteDao",NoteDao.class);
	}
	@Test
	public void testFindNoteById(){
		String noteId="003ec2a1-f975-4322-8e4d-dfd206d6ac0c";
		Note note = noteDao.findNoteById(noteId);
		System.out.println(note);
	}
	@Test
	public void testFindNotesByNotebookId(){
		String notebookId = "0037215c-09fe-4eaa-aeb5-25a340c6b39b";
		List<Map<String,Object>> notes = noteDao.findNotesByNotebookId(notebookId);
		System.out.println(notes);
	}
	@Test
	public void testUpdateNote(){
		Note note = new Note();
		String noteId = "019cd9e1-b629-4d8d-afd7-2aa9e2d6afe0";
		note.setId(noteId);
		note.setTitle("zlTest");
		note.setBody("test123");
		note.setLastModifyTime(System.currentTimeMillis());
		noteDao.updateNote(note);
		note = noteDao.findNoteById(noteId);
		System.out.println(note);
	}
}
