package com.zhaopin.report.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhaopin.report.common.AbstractWebController;
import com.zhaopin.report.common.constant.CityListConstants;
import com.zhaopin.report.common.constant.YearQuarterConstants;
import com.zhaopin.report.common.vo.PageData;
import com.zhaopin.report.mapper.model.ApplyCost;
import com.zhaopin.report.mapper.model.Budget;
import com.zhaopin.report.mapper.model.User;
import com.zhaopin.report.service.ApplyCostService;
import com.zhaopin.report.service.BudgetService;
import com.zhaopin.report.util.CallBackConstant;
import com.zhaopin.report.util.DateUtil;
import com.zhaopin.report.util.ExcelReportUtil;
import com.zhaopin.report.util.PageView;
import com.zhaopin.report.util.StringUtil;

@Controller
@RequestMapping("/admin/budget")
public class BudgetController  extends AbstractWebController {

	private final static Logger logger = Logger.getLogger(IndexController.class);
	
	@Autowired
	private ApplyCostService applyCostService;
	
	@Autowired
	private BudgetService budgetService;
	
	@RequestMapping("/budgetList")
	public ModelAndView budgetList(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView=new ModelAndView("admin/budget_list");
		User loginInfo=(User) request.getSession().getAttribute("logininfo");
		modelAndView.getModel().put("loginInfo", loginInfo);
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/savebudget",method={RequestMethod.POST })
	@ResponseBody
	public Map<String,Object> savebudget(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		Map<String,Object> json = new HashMap<String, Object>();
		try{
			
			User loginInfo=(User) httpRequest.getSession().getAttribute("logininfo");
			if(loginInfo==null){
				json.put("code", 1000);
				json.put("errormsg", "用户登录信息失效，请重新登录！");
				return json;
			}
			
			return budgetService.saveBugget(httpRequest,loginInfo);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("保存费用申请出现异常，"+e.getCause());
			json.put("code", 1);
			json.put("errormsg", "系统异常，请联系管理员");
			return json;
		}
		
	} 
	
	
	@RequestMapping(value="queryBudgetListPage",method={RequestMethod.POST},produces = "text/html;charset=UTF-8")
	@ResponseBody
	public void queryBudgetListPage(HttpServletRequest request, HttpServletResponse response){
		
		User loginInfo=(User) request.getSession().getAttribute("logininfo");
		
		PageData pd=this.getPageData();
		
		PageView pageView = (PageView) pd.get("pageView");
		pageView.setPageSize(20);
		
		/*if(StringUtil.isNoNull(pd.getString("starttime"))){
			pd.put("starttime", pd.getString("starttime")+" "+"00:00:00");
		}
		
		if(StringUtil.isNoNull(pd.getString("endtime"))){
			pd.put("endtime", pd.getString("endtime")+" "+"23:59:59");
		}*/
		
		List<Budget> list=budgetService.queryBudgetListPage(pd,loginInfo);
		
		//outEasyUIDataToJson(pd, list,footerList, response);
		outEasyUIDataToJson(pd, list, response);
	}
	
	
	@RequestMapping("/downloadCostApply")
	@ResponseBody
	public void excelReportDemo(HttpServletRequest request,HttpServletResponse response){
		try {
	        User loginInfo=(User) request.getSession().getAttribute("logininfo");
	        String realName=request.getParameter("realName");
	        PageData pd=new PageData();
	        if(StringUtil.isNotEmpty(realName)){
	        	
				pd.put("realName", new String(realName.getBytes("iso8859-1"),"UTF-8"));
	        }
			
			List<ApplyCost> list=applyCostService.queryAllApplyCostList(pd,loginInfo);
			String fileName = "download"+DateUtil.getCurrentDateString("yyyyMMddHHmmss");
			
		    String headers[][] = {{"applyQuarter","申请季度 "},{"applyTimeStr","申请时间 "},{"applyArea","区域"},
		    		{"city","城市"},{"deptName","部门"},{"realName","姓名"},{"costTypeName","费用类型"},
		    		{"totalFee","预算金额"},{"realFee","实际使用金额"},{"shengyuFee","剩余金额"},{"remark","备注"}};
			ExcelReportUtil.exportExcel(fileName, headers, list, response);
		} catch (Exception e) {
			logger.error("导出费用报表出现异常，"+e);
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping("/queryById")
	@ResponseBody
	public Map<String,Object> queryById(HttpServletRequest request,HttpServletResponse response){
		try {
	        String id=request.getParameter("id");
	        if(StringUtil.isEmpty(id)){
	        	return CallBackConstant.FAILED.callback("id不能为空");
	        }
	        
	        Budget budget= budgetService.queryOneById(Integer.parseInt(id));
	        if(budget==null){
	        	return CallBackConstant.FAILED.callback("记录不存在");
	        }
	        budget.setCityList(CityListConstants.getCityList());
	        budget.setQuarterList(YearQuarterConstants.getQuarterList());
	        return  CallBackConstant.SUCCESS.callback(budget);
		} catch (Exception e) {
			logger.error("导出费用报表出现异常，"+e);
			e.printStackTrace();
			return CallBackConstant.FAILED.callback("系统异常，请联系管理员！");
		}
	}
	
	
	@RequestMapping("/updateBudget")
	@ResponseBody
	public Map<String,Object> updateBudget(HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String,Object> json = new HashMap<String, Object>();
			User loginInfo=(User) request.getSession().getAttribute("logininfo");
			if(loginInfo==null){
				json.put("code", 1000);
				json.put("errormsg", "用户登录信息失效，请重新登录！");
				return json;
			}
			return budgetService.updateButgetById(request,loginInfo);
		} catch (Exception e) {
			logger.error("导出费用报表出现异常，"+e);
			e.printStackTrace();
			return CallBackConstant.FAILED.callback("系统异常，请联系管理员！");
		}
	} 
}
