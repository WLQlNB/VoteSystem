package com.jxau.ui.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jxau.pojo.User;
import com.jxau.service.UserService;
import com.jxau.service.impl.UserServiceImpl;

public class AutoLoginFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		if (session.getAttribute(User.SESSIONNAME) == null) {
			Cookie[] cookies = req.getCookies();
			for (Cookie cookie : cookies) {
				if (User.SESSIONNAME.equals(cookie.getName())) {
					int id = Integer.parseInt(cookie.getValue());
					try {
						UserService userService = new UserServiceImpl();
						User user = userService.getUser(id);
						userService.online(user, true);
						session.setAttribute(User.SESSIONNAME, user);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}			
		}
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}
}

