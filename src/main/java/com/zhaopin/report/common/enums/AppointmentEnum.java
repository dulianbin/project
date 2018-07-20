package com.zhaopin.report.common.enums;


public enum AppointmentEnum {
	
	
	APPOINTMENT_TO_CLASS("待上课","toclass",0),
	APPOINTMENT_HAVE_CLASS("已上课","haveclass",1),
	APPOINTMENT_CANCELLED_CLASS("已取消","cancelled",2);
	
	private String name;
    private String code;
    private Integer value;
	
    private AppointmentEnum(String name,String code,Integer value){
    	this.name = name;
    	this.code = code;
    	this.value = value;
    }
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public Integer getValue() {
		return value;
	}
	
	public void setValue(Integer value) {
		this.value = value;
	}
    
	
}
