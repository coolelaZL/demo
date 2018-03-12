package cn.tedu.cloudnote.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloudnote.util.JsonResult;

public abstract class BaseController {

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public JsonResult handleExp(Exception e) {
		e.printStackTrace();
		return new JsonResult(e);
	}

}