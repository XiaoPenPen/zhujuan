package com.game.zhujuan.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class WebContext {

	/**
	 * 得到request对象
	 */
	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public static ServletContext getServletContext(){
		return getRequest().getServletContext();
	}

	public static String getUserId(){
		return getRequest().getHeader("user_key");
	}

	public static String getUserRole(){
		return getRequest().getHeader("user_role");
	}

	public static String getUserType(){
		return getRequest().getHeader("user_type");
	}
}
