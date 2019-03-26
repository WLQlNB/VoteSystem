package com.jxau.ui.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jxau.util.dao.DbHelper;
public class TransFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 向下转型
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		try {
			// 开启事务
			DbHelper.getConnection();
			DbHelper.beginTrans();
			
			// 向后放行
			chain.doFilter(request, response);
			
			//提交事务
			DbHelper.commitTrans();
			DbHelper.close();
		} catch (Exception e) {
			try {
				DbHelper.rollbackTrans();
				DbHelper.close();
				throw new RuntimeException(e);
			} catch (Exception e1) {
				throw new RuntimeException(e);
			}
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
