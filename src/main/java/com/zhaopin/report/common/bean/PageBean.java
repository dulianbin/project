package com.zhaopin.report.common.bean;



public class PageBean {

	private Object list;
	private Page page;
	
	public PageBean(Object list, Page page) {
		super();
		this.list = list;
		this.page = page;
	}

	public Object getList() {
		return list;
	}

	public Page getPage() {
		return page;
	}
	
}
