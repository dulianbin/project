package com.zhaopin.report.util.freemarker;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class FrontUtils {
		
	
	public static void  frontData(HttpServletRequest request,String solution,
			Map<String, Object> map){
	}
	
	public static String getTargetPath(){
		return "";
	}
	
	public static String getTplPath(HttpServletRequest request,
			String solution, String dir, String name) {
		return solution + "/" + dir + "/"
				+ MessageResolver.getMessage(request, name) + ".html";
	}
}
