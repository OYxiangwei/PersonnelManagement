package com.oy.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class loginInterceptor implements HandlerInterceptor {

	private static final String[] IGNORE_URI = { "/login" };

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	// Controller 处理前进行调用
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean flag = false;
		String url = request.getServletPath();
		for (String s : IGNORE_URI) {
			if (url.contains(s)) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			String username = (String) request.getSession().getAttribute("username");
			if (username == null) {
				request.setAttribute("message", "请先登录");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			} else {
				flag = true;
			}
		}
		return flag;
	}

}
