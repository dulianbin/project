package com.zhaopin.report.mapper.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable{
   
	private static final long serialVersionUID = -2186048414949039317L;

	private Integer userId;

    private String username;//账号

    private String realname;//真实名
    
    private String userno; //用户编号
    
    private String city; //用户城市
    
    private Integer role_id;//用户角色id
    
    
    //不持久字段
    private String userMobile; //用户手机号码
    
    private String deptName; //部门名称
    
    private String email;//邮箱

    private Integer isLeader;//是否领导
    
    private String branchCompanyMngDeptId; //用户所属分公司编码
    
    private String extension;//用户分机号码
    
    private String mngDeptId;//用户所属部门的区域机构编码
    
    private String deptId;//用户所属部门编码
    
    private String empRole;//用户帐号类型
    
    private Date ctime;//创建时间
    
    private List<Menu> menus;
    
    private String roleName;//角色名称
    
    private String shopName;//门店名称
    
    private String canSeeCity;//能看到的城市权限
    
    
    
	public String getCanSeeCity() {
		return canSeeCity;
	}

	public void setCanSeeCity(String canSeeCity) {
		this.canSeeCity = canSeeCity;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getUserno() {
		return userno;
	}

	public void setUserno(String userno) {
		this.userno = userno;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(Integer isLeader) {
		this.isLeader = isLeader;
	}

	public String getBranchCompanyMngDeptId() {
		return branchCompanyMngDeptId;
	}

	public void setBranchCompanyMngDeptId(String branchCompanyMngDeptId) {
		this.branchCompanyMngDeptId = branchCompanyMngDeptId;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getMngDeptId() {
		return mngDeptId;
	}

	public void setMngDeptId(String mngDeptId) {
		this.mngDeptId = mngDeptId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getEmpRole() {
		return empRole;
	}

	public void setEmpRole(String empRole) {
		this.empRole = empRole;
	}



	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
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

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

}