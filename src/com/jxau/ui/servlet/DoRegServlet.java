package com.jxau.ui.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jxau.dao.UserDao;
import com.jxau.dao.impl.UserDaoImpl;
import com.jxau.pojo.User;
import com.jxau.service.UserService;
import com.jxau.service.impl.UserServiceImpl;
import com.jxau.util.exception.RuleException;

public class DoRegServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取浏览器提交的数据
		String name = request.getParameter("name");
		String pwd= request.getParameter("pwd");
		String confirmPwd=request.getParameter("confirmPwd");
		
		//实体类对象作为数据载体
		User user = new User();
		user.setName(name);
		user.setPwd(pwd);
		user.setConfirmPwd(confirmPwd);
		
		try {
			//2 创建业务逻辑对象，调用业务方法处理数据
			UserService service = new UserServiceImpl(); 
			service.register(user);
			//3 衔接JSP：重定向到/login登录页面
			response.sendRedirect(request.getContextPath()+"/login");
			
		}catch(RuleException re){
			//因为用户操作不当违反业务规则
			//进行数据回显：还是发回注册表单  reg.jsp
			//通过域对象传回接收到的数据，错误原因
			request.setAttribute("user", user);
			request.setAttribute("message", re.getMessage());
			request.getRequestDispatcher("/WEB-INF/pages/reg.jsp")
			    .forward(request, response);			
			
		}catch (Exception e) {	
			//TODO  统一异常处理
			e.printStackTrace();
		}
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
