package com.spring.acorn.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginUser") == null){
			System.out.println(">>>>>>>> current user is not logined ");
			
			//이전 페이지로 이동하기 위한 작업
			saveDest(request);
			
			response.sendRedirect("/user/login");
			return false;
		}

		//Object user = session.getAttribute("loginUser");
		
		/*if(session != null){
			System.out.println(">>>>>>>> session check ");
			response.sendRedirect("/board/listPage");
			return false;
		}
		if(user == null){
			System.out.println(">>>>>>>> session check ");
			response.sendRedirect("/board/listPage");
			return false;
		}
		*/
		
		return true;
		
	}
	
	public void saveDest(HttpServletRequest request){
		String uri = request.getRequestURI();
		String query = request.getQueryString();
		
		if( query == null || query.equals("null")){
			query = "";
		}else{
			query = "?" + query;
		}
		System.out.println(request.getMethod());
		/*
		if(request.getMethod().equals("get")){
			request.getSession().setAttribute("dest", uri + query);
			System.out.println(uri + query);
		}
		*/
		request.getSession().setAttribute("dest", uri + query);
		
		/*
		 * request.getSession().setAttribute("dest", uri + query);
		*/
	}
}
