package com.zhaopin.report.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhaopin.report.common.dao.BaseMapper;
import com.zhaopin.report.common.service.BaseServiceImpl;
import com.zhaopin.report.common.vo.PageData;
import com.zhaopin.report.mapper.UserMapper;
import com.zhaopin.report.mapper.model.City;
import com.zhaopin.report.mapper.model.User;
import com.zhaopin.report.service.AdminService;
import com.zhaopin.report.util.HttpClientTool;
import com.zhaopin.report.util.MD5Util;
import com.zhaopin.report.util.StringUtil;
import com.zhaopin.report.util.XMLUtil;

import net.sf.json.JSONObject;



@Service("adminServiceImpl")
public class AdminServiceImpl  extends BaseServiceImpl<User> implements AdminService{

	private static Logger logger = Logger.getLogger(AdminServiceImpl.class);
	@Autowired
	private UserMapper userMaper;
	
	@Override
	protected BaseMapper<User> getBaseMapper() {
		return userMaper;
	}
	
	
	public User login(PageData pd) {
		try {
			User user=new User();
			String loginName=pd.getString("loginname");
			String password=pd.getString("password");
			logger.info("loginname:"+loginName+",password:"+password+"的用户进行登录操作");
			
			Map<String,String> data=new HashMap<String,String>();
			data.put("username", loginName);
			data.put("password", password);
			
			data.put("code", MD5Util.MD5(loginName+password+"martin_got_an_ipad"));
			
			String result=HttpClientTool.postData("http://nw.zhaopin.com/cas/login_interface.jsp", data, "UTF-8");
			logger.info("调用单点登录结果:"+result);
			
			if(StringUtil.isEmpty(result)|| "null".equals(result)){
				logger.info("loginname:"+loginName+",password:"+password+"的用户不存在");
				return null;
			}
			
			Map employeeStatusMap=XMLUtil.doXMLParse(result);
			
			if( !(employeeStatusMap!= null && employeeStatusMap.containsKey("status") && Integer.parseInt(employeeStatusMap.get("status").toString())==1)){
				logger.info("loginname:"+loginName+",password:"+password+"的用户不存在");
				return null;
			}
			user.setUserId(Integer.parseInt(employeeStatusMap.get("id").toString()));
			user.setDeptId(employeeStatusMap.get("deptId").toString());
			user.setBranchCompanyMngDeptId(employeeStatusMap.get("branchCompanyMngDeptId").toString());
			
			//查询用户详细信息
			String json=HttpClientTool.get("http://kaoqin.zhaopin.com/common/getUserinfo.jsp?crmId="+loginName);
			JSONObject jsonObject=JSONObject.fromObject(json);
			String city="";
			if(jsonObject!=null){
				user.setUsername(loginName);
				city=jsonObject.getString("city");
				user.setCity(city);
				user.setRealname(jsonObject.getString("userName"));
				user.setDeptName(jsonObject.getString("deptName"));
				user.setUserno(jsonObject.getString("userNo"));
				user.setUserMobile(jsonObject.getString("userMobile"));
			}
			PageData pd2=new PageData();
			pd2.put("username", loginName);
			User existUser=userMaper.queryOneByUsername(pd2);
			//如果查询存在该用户，表示是财务角色的用户，不存在则是普通用户
			if(existUser!=null){
				user.setCanSeeCity(existUser.getCity());
				user.setRole_id(existUser.getRole_id());
			}else{
				user.setCanSeeCity(city);
				user.setRole_id(1);
			}
			
			return user;
		} catch (Exception e) {
			logger.error("出现异常:"+e);
			e.printStackTrace();
		}
		return null;
	}


	public List<City> queryCityList(PageData pd) {
		return userMaper.queryCityList(pd);
	}


	


}
