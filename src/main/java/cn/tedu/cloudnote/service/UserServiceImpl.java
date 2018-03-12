package cn.tedu.cloudnote.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloudnote.dao.UserDao;
import cn.tedu.cloudnote.entity.User;
import cn.tedu.cloudnote.exception.NameException;
import cn.tedu.cloudnote.exception.PasswordException;
import cn.tedu.cloudnote.util.NoteUtil;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource
	private UserDao userDao;

	public User login(String name, String pwd) throws NameException, PasswordException {
		if(name==null||name.trim().isEmpty()){
			throw new NameException("用户名不能为空");
		}
		if(pwd==null||pwd.trim().isEmpty()){
			throw new PasswordException("密码不能为空");
		}
		User user = userDao.findByName(name);
		if(user==null){
			throw new NameException("用户名错误");
		}
		String md5pwd = NoteUtil.md5(pwd);
		if(user.getPassword().equals(md5pwd)){
			return user;
		}else{
			throw new PasswordException("密码错误");
		}
	}

	public User regist(String name, String pwd, String nick) throws NameException, PasswordException {
		if(name==null||name.trim().isEmpty()){
			throw new NameException("用户名不能为空");
		}
		if(pwd==null||pwd.trim().isEmpty()){
			throw new PasswordException("密码不能为空");
		}
		User user = userDao.findByName(name);
		if(user!=null){
			throw new NameException("用户名已注册");
		}
		if(nick==null||nick.trim().isEmpty()){
			nick=name;
		}
		String id=NoteUtil.createId();
		pwd = NoteUtil.md5(pwd);
		String token="";
		user=new User(id,name,pwd,token,nick);
		userDao.addUser(user);
		return user;
	}
	
}
