package com.jxau.ui.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.jxau.pojo.User;
import com.jxau.service.UserService;
import com.jxau.service.impl.UserServiceImpl;


public class OffLineListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		User user = (User)session.getAttribute(User.SESSIONNAME);
		UserService userService = new UserServiceImpl();
		userService.online(user,false);
	}

}