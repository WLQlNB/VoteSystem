package com.jxau.service.impl;

import java.util.List;

import com.jxau.dao.UserDao;
import com.jxau.dao.impl.UserDaoImpl;
import com.jxau.pojo.User;
import com.jxau.pojo.UserQueryModel;
import com.jxau.service.UserService;
import com.jxau.util.exception.RuleException;
import com.jxau.util.format.Md5Class;

public class UserServiceImpl implements UserService {
	private UserDao userDao;

	public UserServiceImpl() {
		userDao = new UserDaoImpl();
	}

	@Override
	public void register(User user) throws Exception {
		if (user.getName() == null || user.getName().trim().length() == 0) {
			throw new RuleException("用户账号不能为空");
		}
		if (user.getPwd() == null || user.getPwd().trim().length() < 6) {
			throw new RuleException("登录密码不能为空或者密码长度不足6位");
		}
		if (!user.getPwd().equals(user.getConfirmPwd())) {
			throw new RuleException("密码和确认密码不一致");
		}
		UserQueryModel queryModel = new UserQueryModel();
		queryModel.setName(user.getName());
		List list =userDao.getByCondition(queryModel);
		if (list.size() > 0) {
			throw new RuleException("用户账号已经被注册");
		}
		user.setPwd(Md5Class.stringToMd5(user.getPwd()));
		user.setOnline(1);
		userDao.insert(user);
	}

	@Override
	public User login(User user) throws Exception{
		if (user.getName() == null || user.getName().trim().length() == 0) {
			throw new RuleException("用户账号不能为空");
		}
		if (user.getPwd() == null || user.getPwd().trim().length() < 6) {
			throw new RuleException("登录密码不能为空或者密码长度不足6位");
		}
		User target = null;
		UserQueryModel queryModel = new UserQueryModel();
		queryModel.setName(user.getName());
		queryModel.setPwd(Md5Class.stringToMd5(user.getPwd()));
		List list = userDao.getByCondition(queryModel);
		if(list!=null && list.size()>0){
			target = (User)list.get(0);
			if(target.getOnline()==2){
				throw new RuleException("用户已经在线");
			}
			else{
				target.setOnline(2);
				userDao.update(target);
			}
		}
		else{
			throw new RuleException("用户名不存在或者密码错误");
		}
		
		return target;
	}

	@Override
	public User getUser(int id) throws Exception {
		User user = (User)userDao.getModel(id);
		return user;
	}

	@Override
	public void online(User user,boolean inOrOut) {		
		try {
			if(inOrOut){
				user.setOnline(2);
			}
			else{
			    user.setOnline(1);
			}
			
			userDao.update(user);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


}
