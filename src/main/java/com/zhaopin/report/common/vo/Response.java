package com.zhaopin.report.common.vo;

import java.io.Serializable;



public class Response<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4251971013956884975L;

	private T data;

	private int retCode;

	private String message;


	public int getRetCode() {
		return retCode;
	}

	public void setRetCode(int retCode) {
		this.retCode = retCode;

	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}