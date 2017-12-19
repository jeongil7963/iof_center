package com.iof.center.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	//@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		try {
			System.out.println("Ctrl interceptor");
			Map<String, Object> map = (Map<String, Object>) request.getSession().getAttribute("user");

			if (map == null) {
				response.sendRedirect("/user/login");
				return false;
			}
			Object str = map.get("user_id");
			if (str == null) {
				response.sendRedirect("/user/login");
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/user/login");
			return false;
		}
		//loginUser 세션key 존재시 main 페이지 이동
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		super.afterConcurrentHandlingStarted(request, response, handler);
	}
}