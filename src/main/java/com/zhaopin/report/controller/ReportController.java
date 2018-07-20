package com.zhaopin.report.controller;

import java.util.ArrayList;
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
import com.zhaopin.report.common.vo.PageData;
import com.zhaopin.report.mapper.model.ApplyCost;
import com.zhaopin.report.mapper.model.User;
import com.zhaopin.report.service.ApplyCostService;
import com.zhaopin.report.util.DateUtil;
import com.zhaopin.report.util.ExcelReportUtil;
import com.zhaopin.report.util.PageView;
import com.zhaopin.report.util.StringUtil;

@Controller
@RequestMapping("/admin/report")
public class ReportController extends AbstractWebController {

	private final static Logger logger = Logger.getLogger(IndexController.class);
	@Autowired
	private ApplyCostService applyCostService;
	
	@RequestMapping("/reportList")
	public ModelAndView costApplyList(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView=new ModelAndView("admin/report_list");
		User loginInfo=(User) request.getSession().getAttribute("logininfo");
		modelAndView.getModel().put("loginInfo", loginInfo);
		return modelAndView;
	}
	
	@RequestMapping(value="calCityCostListPage",method={RequestMethod.POST},produces = "text/html;charset=UTF-8")
	@ResponseBody
	public void queryAccountListPage(HttpServletRequest request, HttpServletResponse response){
		
		User loginInfo=(User) request.getSession().getAttribute("logininfo");
		
		PageData pd=this.getPageData();
		
		PageView pageView = (PageView) pd.get("pageView");
		pageView.setPageSize(20);
		
		List<Map<String, Object>> list=applyCostService.calCityCostListPage(pd,loginInfo);
		outEasyUIDataToJson(pd, list, response);
	}
	
	
	@RequestMapping(value="queryQuarterAllFee",method={RequestMethod.POST},produces = "text/html;charset=UTF-8")
	@ResponseBody
	public void queryQuarterAllFee(HttpServletRequest request, HttpServletResponse response){
		try{
			
			PageData pd=this.getPageData();
			String city=request.getParameter("city");
			String quarter=request.getParameter("quarter");
			pd.put("quarter", quarter);
	        if(StringUtil.isNotEmpty(city)){
				pd.put("city", new String(city.getBytes("iso8859-1"),"UTF-8"));
	        }
			
			List<Map<String, Object>> list=applyCostService.queryQuarterAllFee(pd);
			outEasyUIDataToJson(pd, list, response);
		}catch(Exception e){
			logger.error("系统异常，请联系管理员");
		}
		
	}
	
	
	
	@RequestMapping(value="queryYearBudgetFeeList",method={RequestMethod.POST},produces = "text/html;charset=UTF-8")
	@ResponseBody
	public void queryYearBudgetFeeList(HttpServletRequest request, HttpServletResponse response){
		try{
			
			PageData pd=this.getPageData();
			String year=request.getParameter("year");
			if(StringUtil.isEmpty(year)){
				pd.put("year", "FY19-Q");
				year="FY19-Q";
			}
			pd.put("year", year);
			
			List<Map<String, Object>> list=applyCostService.queryYearBudgetFeeList(pd);
			List<Map<String, Object>> footerList=new ArrayList<Map<String, Object>>();
			Map<String, Object> map_17=list.get(16);
			map_17.put("city", "年度实际剩余");
			Map<String, Object> map_21=list.get(20);
			map_21.put("city", "年度预算剩余");
			footerList.add(map_17);
			footerList.add(map_21);
			outEasyUIDataToJson(pd, list,footerList, response);
		}catch(Exception e){
			logger.error("系统异常，请联系管理员");
		}
	}
	
	
	@RequestMapping("/downloadYearBudgetFeeList")
	@ResponseBody
	public void downloadYearBudgetFeeList(HttpServletRequest request,HttpServletResponse response){
		try {
			PageData pd=this.getPageData();
			String year=request.getParameter("year");
			if(StringUtil.isEmpty(year)){
				pd.put("year", "FY19-Q");
				year="FY19-Q";
			}
			pd.put("year", year);
			
			List<Map<String, Object>> list=applyCostService.queryYearBudgetFeeList(pd);
			Map<String, Object> map_17=list.get(16);
			map_17.put("city", "年度实际剩余");
			Map<String, Object> map_21=list.get(20);
			map_21.put("city", "年度预算剩余");
			list.add(map_17);
			list.add(map_21);
			
			String fileName = "download"+DateUtil.getCurrentDateString("yyyyMMddHHmmss");
			
		    String headers[][] = {{"city","城市"},{"apply_quarter","季度 "},{"sz","深圳"},
		    		{"gz","广州"},{"cs","长沙"},{"fz","福州"},{"nc","南昌"},
		    		{"fs","佛山"},{"xm","厦门"} ,{"dg","东莞"} ,{"qyyy","区域运营"},{"zh","珠海"},{"hk","海口"}};
			ExcelReportUtil.exportYearBudgetFeeExcel(fileName, headers, list, response);
		} catch (Exception e) {
			logger.error("导出费用报表出现异常，"+e);
			e.printStackTrace();
		}
	}
	
	
	
	
	@RequestMapping("/downloadCityCost")
	@ResponseBody
	public void downloadCityCost(HttpServletRequest request,HttpServletResponse response){
		try {
			User loginInfo=(User) request.getSession().getAttribute("logininfo");
			
			//PageData pd=this.getPageData();
			
			String search=request.getParameter("search");
	        PageData pd=new PageData();
	        if(StringUtil.isNotEmpty(search)){
				pd.put("search", new String(search.getBytes("iso8859-1"),"UTF-8"));
	        }
			
			List<Map<String, Object>> list=applyCostService.calCityCostAllList(pd,loginInfo);
			String fileName = "download"+DateUtil.getCurrentDateString("yyyyMMddHHmmss");
			
		    String headers[][] = {{"apply_quarter","申请季度 "},{"apply_area","区域"},{"city","城市"},
		    		{"budget_fee1","预算额度"},{"total_fee_sum","已审金额"},{"real_fee_sum","实际使用金额"},{"can_apply_fee","可申请余额"},
		    		{"real_shengyu_fee","实际余额"}};
			ExcelReportUtil.exportCityCostApplyExcel(fileName, headers, list, response);
		} catch (Exception e) {
			logger.error("导出费用报表出现异常，"+e);
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/downloadQuarterAllFee")
	@ResponseBody
	public void downloadQuarterAllFee(HttpServletRequest request,HttpServletResponse response){
		try {
			PageData pd=this.getPageData();
			String city=request.getParameter("city");
			String quarter=request.getParameter("quarter");
			pd.put("quarter", quarter);
	        if(StringUtil.isNotEmpty(city)){
				pd.put("city", new String(city.getBytes("iso8859-1"),"UTF-8"));
	        }
			
			List<Map<String, Object>> list=applyCostService.queryQuarterAllFee(pd);
			String fileName = "download"+DateUtil.getCurrentDateString("yyyyMMddHHmmss");
			
		    String headers[][] = {{"city","城市"},{"dept_name","部门"},{quarter+"1",quarter+"1"},
		    		{quarter+"2",quarter+"2"},{quarter+"3",quarter+"3"},{quarter+"4",quarter+"4"},{"apply_allfee","申请总费用"},
		    		{"budget_fee_sum","年度总预算"},{"fee_rante","申请费用占比"}};
			ExcelReportUtil.exportQuarterAllFeeExcel(fileName, headers, list, response);
		} catch (Exception e) {
			logger.error("导出费用报表出现异常，"+e);
			e.printStackTrace();
		}
	}
	
}
