package com.zhaopin.report.util;

import java.util.HashMap;
import java.util.Map;

public enum CallBackConstant {

	SUCCESS(1010, "操作成功"),
	FAILED(1020, "操作失败"),
	PARAMETER_ERROR(1021,"参数异常"),
	NOT_EXIST(1022,"该用户名不存在"),
	REGISTERED(1023,"该手机号码已注册"), 
	LOGIN_TIME_OUT(1011,"登录超时，请重新登录"),
	TOKEN_TIME_OUT(1024,"会话超时"),
	UPLOAD_FILE_ERROR(1025,"文件上传失败");

	private Integer code;

	private String msg;
	
	private String json;
	
	private CallBackConstant(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
	
	public Map<String, Object> callback() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("code", this.code);
		map.put("msg", this.msg);
		return map;
	}
	
	/**
	 * 返回json
	 * @param json
	 * @return
	 */
	public Map<String, Object> callback(Object obj) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("code", this.code);
		map.put("msg", this.msg);
		map.put("data", obj);
		return map;
	}
	
	/**
	 * 返回错误信息
	 * @param msg 提示信息
	 * @return
	 */
	public Map<String, Object> callbackError(String msg) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("code", this.code);
		map.put("msg", msg);
		return map;
	}
	
	/**
	 * 正常返回json信息
	 * @param msg 提示信息
	 * @param json 
	 * @return
	 */
	public Map<String, Object> callback(String msg, String json) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("code", this.code);
		map.put("msg", msg);
		map.put("data", json);
		return map;
	}
	

	@Override
	public String toString() {
		StringBuffer res = new StringBuffer();
		res.append("{\"code\":\"");
		res.append(this.code);
		res.append("\", \"msg\":\"");
		res.append(this.msg);
		res.append("\", \"data\":");
		res.append(this.json);
		res.append("}");
		return res.toString();
	}
	
}
