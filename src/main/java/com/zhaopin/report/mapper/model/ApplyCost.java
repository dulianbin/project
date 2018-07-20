package com.zhaopin.report.mapper.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.zhaopin.report.util.DateUtil;

public class ApplyCost implements Serializable{
	
	private static final long serialVersionUID = 2636125839689563709L;

	private Integer costId;

    private String costName;  //费用名称

    private String applyQuarter; //申请季度

    private String city;  //申请城市

    private String deptId; //部门id

    private String deptName; //部门名称

    private Integer userId;  //申请人id

    private String username; //申请人
    
    private String realName;//真实姓名

    private Integer costTypeId; //费用类型id

    private String costTypeName; //费用类型名称

    private BigDecimal totalFee; //预算金额

    private BigDecimal realFee;  //实际使用金额

    private BigDecimal shengyuFee;  //剩余金额

    private String applyArea;  //区域
    
    private Date applyTime;
    
    private String applyTimeStr;
    
    private Integer costStatus;  //状态: 1正常，2已删除
    
    private Integer cityId;
    
    public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getCostStatus() {
		return costStatus;
	}

	public void setCostStatus(Integer costStatus) {
		this.costStatus = costStatus;
	}

	public String getApplyTimeStr() {
    	if(applyTime!=null){
    		return DateUtil.getFormatDateTime(applyTime, "yyyy/MM/dd");
    	}
		return applyTimeStr;
	}

	public void setApplyTimeStr(String applyTimeStr) {
		this.applyTimeStr = applyTimeStr;
	}

	private String remark;  //备注
    
    
    
    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Integer getCostId() {
        return costId;
    }

    public void setCostId(Integer costId) {
        this.costId = costId;
    }

    public String getCostName() {
        return costName;
    }

    public void setCostName(String costName) {
        this.costName = costName == null ? null : costName.trim();
    }

    public String getApplyQuarter() {
        return applyQuarter;
    }

    public void setApplyQuarter(String applyQuarter) {
        this.applyQuarter = applyQuarter == null ? null : applyQuarter.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getCostTypeId() {
        return costTypeId;
    }

    public void setCostTypeId(Integer costTypeId) {
        this.costTypeId = costTypeId;
    }

    public String getCostTypeName() {
        return costTypeName;
    }

    public void setCostTypeName(String costTypeName) {
        this.costTypeName = costTypeName == null ? null : costTypeName.trim();
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public BigDecimal getRealFee() {
        return realFee;
    }

    public void setRealFee(BigDecimal realFee) {
        this.realFee = realFee;
    }

    public BigDecimal getShengyuFee() {
        return shengyuFee;
    }

    public void setShengyuFee(BigDecimal shengyuFee) {
        this.shengyuFee = shengyuFee;
    }

    public String getApplyArea() {
        return applyArea;
    }

    public void setApplyArea(String applyArea) {
        this.applyArea = applyArea == null ? null : applyArea.trim();
    }
}