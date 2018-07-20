package com.zhaopin.report.mapper.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.zhaopin.report.util.DateUtil;

public class Budget implements Serializable{
    
	private static final long serialVersionUID = 963500690966444975L;

	private Integer id;

    private String applyArea;

    private Integer cityId;

    private String city;

    private String applyQuarter;

    private BigDecimal budgetFee;

    private String operator;

    private String operatorNo;

    private Date operatorTime;
    
    private String operatorTimeStr;
    
    private Map<Integer,String> cityList;
    
    private Map<String,String> quarterList;
    
    
    public Map<String, String> getQuarterList() {
		return quarterList;
	}

	public void setQuarterList(Map<String, String> quarterList) {
		this.quarterList = quarterList;
	}

	public Map<Integer, String> getCityList() {
		return cityList;
	}

	public void setCityList(Map<Integer, String> cityList) {
		this.cityList = cityList;
	}

	public String getOperatorTimeStr() {
    	
    	if(operatorTime!=null){
    		return DateUtil.getFormatDateTime(operatorTime, "yyyy-MM-dd HH:mm");
    	}
		return operatorTimeStr;
	}

	public void setOperatorTimeStr(String operatorTimeStr) {
		this.operatorTimeStr = operatorTimeStr;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApplyArea() {
        return applyArea;
    }

    public void setApplyArea(String applyArea) {
        this.applyArea = applyArea == null ? null : applyArea.trim();
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getApplyQuarter() {
        return applyQuarter;
    }

    public void setApplyQuarter(String applyQuarter) {
        this.applyQuarter = applyQuarter == null ? null : applyQuarter.trim();
    }

    public BigDecimal getBudgetFee() {
        return budgetFee;
    }

    public void setBudgetFee(BigDecimal budgetFee) {
        this.budgetFee = budgetFee;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getOperatorNo() {
        return operatorNo;
    }

    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }
}