package cn.tedu.cloudnote.service;

import java.util.List;
import java.util.Map;

import cn.tedu.cloudnote.exception.UserNotFoundException;

public interface NotebookService {
	List<Map<String,Object>> listNotebooks(String userId)throws UserNotFoundException;
}
