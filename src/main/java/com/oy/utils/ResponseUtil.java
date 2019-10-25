package com.oy.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {
	public static void write(HttpServletResponse response, Object object) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		PrintWriter ptWriter = response.getWriter();
		ptWriter.println(object.toString());
		ptWriter.flush();
		ptWriter.close();
	}
}
