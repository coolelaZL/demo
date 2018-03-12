package cn.tedu.cloudnote.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloudnote.service.NotebookService;
import cn.tedu.cloudnote.util.JsonResult;

@Controller
@RequestMapping("/notebook")
public class NotebookController extends BaseController {
	@Resource
	private NotebookService notebookService;
	
	@RequestMapping("/list.do")
	@ResponseBody
	public JsonResult list(String userId){
		List<Map<String,Object>> list=notebookService.listNotebooks(userId);
		System.out.println();
		return new JsonResult(list);
	}
}
