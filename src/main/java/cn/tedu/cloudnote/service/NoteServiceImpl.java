package cn.tedu.cloudnote.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloudnote.dao.NoteDao;
import cn.tedu.cloudnote.dao.NotebookDao;
import cn.tedu.cloudnote.dao.UserDao;
import cn.tedu.cloudnote.entity.Note;
import cn.tedu.cloudnote.entity.Notebook;
import cn.tedu.cloudnote.entity.User;
import cn.tedu.cloudnote.exception.NoteNotFoundException;
import cn.tedu.cloudnote.exception.NotebookNotFoundException;
import cn.tedu.cloudnote.exception.UserNotFoundException;

@Service("noteService")
public class NoteServiceImpl implements NoteService {
	
	@Resource
	private NoteDao noteDao;
	@Resource
	private NotebookDao notebookDao;
	@Resource
	private UserDao userDao;
	
	public List<Map<String, Object>> listNotes(String notebookId) 
		throws NotebookNotFoundException {
		if(notebookId==null||notebookId.trim().isEmpty()){
			throw new NotebookNotFoundException("笔记本ID空");
		}
		Notebook notebook = notebookDao.findNotebookById(notebookId);
		if(notebook==null){
			throw new NotebookNotFoundException("笔记本不存在");
		}
		
		return noteDao.findNotesByNotebookId(notebookId);
	}

	public Note getNote(String noteId) throws NoteNotFoundException {
		if(noteId==null||noteId.trim().isEmpty()){
			throw new NoteNotFoundException("笔记ID空");
		}
		Note note = noteDao.findNoteById(noteId);
		if(note==null){
			throw new NoteNotFoundException("笔记ID错误");
		}
		return note;
	}

	public boolean update(String noteId, String title, String body) throws NoteNotFoundException {
		if(noteId==null||noteId.trim().isEmpty()){
			throw new NoteNotFoundException("笔记ID不能空");
		}
		Note note = noteDao.findNoteById(noteId);
		if(note==null){
			throw new NoteNotFoundException("没有对应的笔记");
		}
		Note data = new Note();
		if(title!=null && !title.equals(note.getTitle())){
			data.setTitle(title);
		}
		if(body!=null && !body.equals(note.getBody())){
			data.setBody(body);
		}
		data.setId(noteId);
		data.setLastModifyTime(System.currentTimeMillis());
		int n = noteDao.updateNote(data);
		return n==1;
	}

	public Note addNote(String userId, String notebookId, String title)
			throws UserNotFoundException, NotebookNotFoundException {
		if(userId==null||userId.trim().isEmpty()){
			throw new UserNotFoundException("UserIdkong");
		}
		User user = userDao.findUserById(userId);
		if(user==null){
			throw new UserNotFoundException("没有这个用户");
		}
		if(notebookId==null||notebookId.trim().isEmpty()){
			throw new NotebookNotFoundException("NotebookId空");
		}
		Notebook notebook = notebookDao.findNotebookById(notebookId);
		if(notebook==null){
			throw new NotebookNotFoundException("没有笔记本");
		}
		if(title==null||title.trim().isEmpty()){
			title="葵花宝典";
		}
		String id = UUID.randomUUID().toString();
		String statusId = "0";
		String typeId = "0";
		String body="";
		long time = System.currentTimeMillis();
		Note note = new Note(id,notebookId,userId,statusId,typeId,title,body,time,time);
		int n = noteDao.addNote(note);
		if(n!=1){
			throw new NoteNotFoundException("保存失败");
		}
		return note;
	}

}
