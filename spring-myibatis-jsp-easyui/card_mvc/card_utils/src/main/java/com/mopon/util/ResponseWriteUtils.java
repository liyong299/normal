package com.mopon.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class ResponseWriteUtils {
	

	public static void returnAjax(HttpServletResponse response, String content) {
		
		try {
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
