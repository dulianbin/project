package com.zhaopin.report.common.bean;


import java.util.ArrayList;
import java.util.List;

import com.zhaopin.report.util.JsonUtil;




public class EasyUIPageBean {
	
	private static final String SUCCESS = "success";
	private static final String FAILURE = "failure";
	public static final EasyUIPageBean NONE = new EasyUIPageBean(0L, new ArrayList<Object>());

	private Long total;
	private List<? extends Object> rows;
	private List<? extends Object> footer;
	private String result;
	private String message;
	
	public List<? extends Object> getFooter() {
		return footer;
	}

	public void setFooter(List<? extends Object> footer) {
		this.footer = footer;
	}

	public static final EasyUIPageBean createEasyUIPageBean(Long total, List<? extends Object> rows) {
		return new EasyUIPageBean(total, rows);
	}
	
	public static final EasyUIPageBean createEasyUIPageBean(Long total,
			List<? extends Object> rows, List<? extends Object> footer) {
		return new EasyUIPageBean(total, rows, footer);
	}
	public static final EasyUIPageBean createFailureEasyUIPageBean(String message) {
		return new EasyUIPageBean(message);
	}
	
	private EasyUIPageBean(Long total, List<? extends Object> rows) {
		this.total = total;
		this.rows = rows;
		this.result = SUCCESS;
	}
	private EasyUIPageBean(String message) {
		this.total = 0L;
		this.rows = new ArrayList<Object>(0); 
		this.result = FAILURE;
		this.message = message;
	}
	private EasyUIPageBean(Long total, List<? extends Object> rows,
			List<? extends Object> footer) {
		this.total = total;
		this.rows = rows;
		this.footer = footer;
		this.result = SUCCESS;
	}
	public String toJsonString() {
		return JsonUtil.toJsonString(this);
	}
	public Long getTotal() {
		return total;
	}
	public List<? extends Object> getRows() {
		return rows;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String getResult() {
		return result;
	}
	
}
