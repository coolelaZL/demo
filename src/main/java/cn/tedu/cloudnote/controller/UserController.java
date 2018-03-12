package cn.tedu.cloudnote.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloudnote.entity.User;
import cn.tedu.cloudnote.exception.NameException;
import cn.tedu.cloudnote.exception.PasswordException;
import cn.tedu.cloudnote.service.UserService;
import cn.tedu.cloudnote.util.JsonResult;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	@Resource
	private UserService userService;
	@ResponseBody
	@RequestMapping("/login.do")
	public Object login(@RequestParam("name") String name,@RequestParam("password") String pwd){
		System.out.println(name+","+pwd);
		User user = userService.login(name, pwd);
		System.out.println(user);
		return new JsonResult(user);
	}
	@ResponseBody
	@RequestMapping("/regist.do")
	public Object regist(String name,@RequestParam("password") String pwd,String nick){
		User user = userService.regist(name,pwd,nick);
		System.out.println(user);
		return new JsonResult(user);
	}
	/**
	 * 多个请求都要处理同样的异常时,可以用注释@ExceptionHandler(*.class)来实现
	 * @param e
	 * @return
	 */
	@ExceptionHandler(NameException.class)
	@ResponseBody
	public JsonResult handleNameExp(NameException e){
		e.printStackTrace();
		return new JsonResult(2,e);
	}
	@ExceptionHandler(PasswordException.class)
	@ResponseBody
	public JsonResult handlePwdExp(PasswordException e){
		e.printStackTrace();
		return new JsonResult(2,e);
	}
}
