package com.zhaopin.report.common.bean;




public class Page {
	/**
	 * 每页包含最大记录数
	 */
	private Integer pageSize;
	/**
	 * 总记录数
	 */
	private Long total;
	/**
	 * 页索引，从1开始
	 */
	private Integer pageIndex;
	
	public Page(){
		this.pageSize = 10;
		this.pageIndex = 1;
		this.total = 0L;
	}
	public Page(Integer pageSize, Long total, Integer pageIndex) {
		if(pageSize != null && pageSize > 0){
			this.pageSize = pageSize;
		}else{
			this.pageSize = 10;
		}
		if(pageIndex != null && pageIndex > 0){
			this.pageIndex = pageIndex;
		}else{
			this.pageIndex = 1;
		}
		if(total != null && total >0){
			this.total = total;
		}else {
			this.total = 0L;
		}
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	
}
