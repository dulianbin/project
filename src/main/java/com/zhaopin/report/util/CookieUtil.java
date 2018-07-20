/**
 * @author dlb
 * 2017年4月25日 下午4:15:33
 */
package com.zhaopin.report.util;

import javax.servlet.http.Cookie;

/**
 * @author dlb
 * 2017年4月25日 下午4:15:33
 */
public class CookieUtil {

	
	public static Cookie buildTokenCookie(String cookieName, String value) {
		// 生成新的Token返回给客户端
		Cookie tokenCookie = new Cookie(cookieName, value);
		tokenCookie.setPath("/");
		tokenCookie.setMaxAge(60 * 60 * 24 * 30);
		return tokenCookie;
	}
	
	public static String getCookie(Cookie[] cookies, String cookieName) {
		if (cookies == null || StringUtil.empty(cookieName)) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (cookieName.equals(cookie.getName())) {
				return cookie.getValue();
			}
		}
		return null;
	}
	
}
