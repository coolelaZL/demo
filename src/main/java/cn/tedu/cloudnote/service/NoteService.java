package cn.tedu.cloudnote.service;

import java.util.List;
import java.util.Map;

import cn.tedu.cloudnote.entity.Note;
import cn.tedu.cloudnote.exception.NoteNotFoundException;
import cn.tedu.cloudnote.exception.NotebookNotFoundException;
import cn.tedu.cloudnote.exception.UserNotFoundException;

public interface NoteService {
	List<Map<String,Object>> listNotes(String notebookId)throws NotebookNotFoundException;
	Note getNote(String noteId)throws NoteNotFoundException;
	boolean update(String noteId,String title,String body)
		throws NoteNotFoundException;
	public Note addNote(String userId,String notebookId,String title)
		throws UserNotFoundException,NotebookNotFoundException;
}
