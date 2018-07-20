package com.zhaopin.report.mapper.model;

import java.io.Serializable;

public class City implements Serializable{

	
	private static final long serialVersionUID = 1882962748520323229L;
	
	private Integer city_id;
	private String city;
	
	public Integer getCity_id() {
		return city_id;
	}
	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
}
