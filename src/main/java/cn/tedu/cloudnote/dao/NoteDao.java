package cn.tedu.cloudnote.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.cloudnote.entity.Note;
import cn.tedu.cloudnote.entity.Notebook;

public interface NoteDao {
	List<Map<String,Object>>findNotesByNotebookId(String notebookId);
	Note findNoteById(String noteId);
	int updateNote(Note note);
	int addNote(Note note);
}
