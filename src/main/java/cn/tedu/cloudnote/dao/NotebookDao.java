package cn.tedu.cloudnote.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.cloudnote.entity.Note;
import cn.tedu.cloudnote.entity.Notebook;

public interface NotebookDao {
	List<Map<String,Object>>findNotebooksByUserId(String userId);
	Notebook findNotebookById(String notebookId);
}
