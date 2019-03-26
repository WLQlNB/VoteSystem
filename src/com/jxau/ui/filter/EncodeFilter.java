package com.jxau.ui.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import com.jxau.util.filter.MyRequest;

public class EncodeFilter implements Filter {
	private FilterConfig config;
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String encoding = config.getInitParameter("encoding");
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		HttpServletRequest req = (HttpServletRequest)request;
		if("GET".equals(req.getMethod().toUpperCase())){
			MyRequest temp = new MyRequest(req);
			temp.setEncoding(encoding);
			request=temp;
		}		
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig config) throws ServletException {		
		this.config = config;
	}

}
