package com.zhaopin.report.interceptor;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ser.StdSerializerProvider;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zhaopin.report.common.vo.Response;


public class BaseHandlerInterceptorAdapter extends HandlerInterceptorAdapter {

	private static Logger logger = Logger
			.getLogger(BaseHandlerInterceptorAdapter.class);

	public void writeResp(HttpServletResponse response, Response<Object> resp) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		StdSerializerProvider sp = new StdSerializerProvider();
		// sp.setNullValueSerializer(new JsonNullSerializer());
		objectMapper.setSerializerProvider(sp);
		String jsonStr = "";
		try {
			jsonStr = objectMapper.writeValueAsString(resp);
		} catch (Exception e1) {
			logger.error("to json error", e1);
		}
		this.writeAjaxResult(response, jsonStr);
	}

	private void writeAjaxResult(HttpServletResponse response, String s) {
		try {
			response.setHeader("Content-Type", "application/json;charset=UTF-8");
			response.getOutputStream().write(s.getBytes("utf-8"));
		} catch (Exception e) {
			logger.error("ajax output exception", e);
		}
	}
}
