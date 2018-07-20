package com.zhaopin.report.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhaopin.report.common.AbstractWebController;
import com.zhaopin.report.common.vo.PageData;
import com.zhaopin.report.mapper.model.Menu;
import com.zhaopin.report.mapper.model.User;
import com.zhaopin.report.service.AdminService;
import com.zhaopin.report.util.CallBackConstant;
import com.zhaopin.report.util.StringUtil;

@Controller
public class IndexController extends AbstractWebController {
	private final static Logger logger = Logger.getLogger(IndexController.class);

	@Autowired
	private AdminService adminService;


	
	@RequestMapping("/main")
	public ModelAndView main(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView=new ModelAndView("admin/main");
		logger.info("进入admin/main方法");
		
		try {
			
			User loginInfo=(User) request.getSession().getAttribute("logininfo");
			if(loginInfo==null){
				return new ModelAndView("admin/admin_login");
			}
			
			Map<String,List<Menu>> map=new LinkedHashMap<String,List<Menu>>();
			
			if(loginInfo!=null){
				Integer role_id=loginInfo.getRole_id();
				if(role_id==1){  //个人数据
					List<Menu> menus=new ArrayList<Menu>();
					Menu menu2=new Menu();
					menu2.setLink("/admin/costApply/costApplyList");
					menu2.setMenuCode("33334");
					menu2.setMenuId(2);
					menu2.setName("报销费用管理");
					menu2.setStatus(1);
					menus.add(menu2);
					map.put("报销费用管理", menus);
				}else if(role_id==2 || role_id==3){  //分公司财务
					List<Menu> menus=new ArrayList<Menu>();
					
					Menu menu1=new Menu();
					menu1.setLink("/admin/costApply/costApplyList");
					menu1.setMenuCode("33334");
					menu1.setMenuId(2);
					menu1.setName("报销费用管理");
					menu1.setStatus(1);
					menus.add(menu1);
					map.put("报销费用管理", menus);
					
					
					List<Menu> menus2=new ArrayList<Menu>();
					Menu menu3=new Menu();  //公司预算管理
					menu3.setLink("/admin/budget/budgetList");
					menu3.setMenuCode("33336");
					menu3.setMenuId(4);
					menu3.setName("费用预算管理");
					menu3.setStatus(1);
					menus2.add(menu3);
					
					
					Menu menu2=new Menu();
					menu2.setLink("/admin/report/reportList");
					menu2.setMenuCode("33335");
					menu2.setMenuId(3);
					menu2.setName("预算统计管理");
					menu2.setStatus(1);
					menus2.add(menu2);
					map.put("财务预算管理", menus2);
				}
				
			}
			
			
			
			
			
			modelAndView.getModel().put("menus", map);
			modelAndView.getModel().put("username", loginInfo.getRealname());
			
			modelAndView.getModel().put("roleid", loginInfo.getRole_id());
			
			return modelAndView;
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	@RequestMapping("/tologin")
	public ModelAndView tologin(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView=new ModelAndView("admin/admin_login");
		
		return modelAndView;
	}
	
	@RequestMapping("/")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView=new ModelAndView("admin/admin_login");
		
		return modelAndView;
	}
	
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView=new ModelAndView("admin/admin_login");
		request.getSession().removeAttribute("logininfo");//清空session信息
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/checklogin",method={RequestMethod.POST })
	@ResponseBody
	public Map<String,Object> checklogin(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		Map<String,Object> json = new HashMap<String, Object>();
		String msg = "checklogin";
		logger.debug("/admin/ "+msg+" begin");
		PageData pd = this.getPageData();
		
		try{
		
			String loginName=pd.getString("loginname");
			String password=pd.getString("password");
			if(StringUtil.isEmpty(loginName)){
				json.put("code", 1);
				json.put("errormsg", "loginName不能为空");
				return json;
			}
			
			if(StringUtil.isEmpty(password)){
				json.put("code", 1);
				json.put("errormsg", "password不能为空");
				return json;
			}
			
			
			User loginInfo=adminService.login(pd);
	        if(loginInfo==null){
	        	json.put("code", 1);
				json.put("errormsg", "用户名或密码错误");
				return json;
	        }   
	        
	        HttpSession newSession = httpRequest.getSession();
	        newSession.setAttribute("logininfo", loginInfo);
	        
	        json.put("code", 0);
	        json.put("msg", "操作成功");
	        json.put("data", loginInfo);
	        return CallBackConstant.SUCCESS.callback(loginInfo);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("管理员登陆出现异常:"+e.getCause());
			json.put("code", 1);
			json.put("errormsg", "系统异常，请联系管理员");
			return json;
		}
		
	}
	
}
