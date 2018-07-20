package com.zhaopin.report.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhaopin.report.common.constant.CityListConstants;
import com.zhaopin.report.common.dao.BaseMapper;
import com.zhaopin.report.common.service.BaseServiceImpl;
import com.zhaopin.report.common.vo.PageData;
import com.zhaopin.report.mapper.BudgetMapper;
import com.zhaopin.report.mapper.model.Budget;
import com.zhaopin.report.mapper.model.User;
import com.zhaopin.report.service.BudgetService;
import com.zhaopin.report.util.CallBackConstant;
import com.zhaopin.report.util.StringUtil;

@Service("budgetServiceImpl")
public class BudgetServiceImpl extends BaseServiceImpl<Budget> implements  BudgetService {

	private final static Logger logger = Logger.getLogger(BudgetServiceImpl.class);
	
	@Autowired
	private BudgetMapper budgetMapper;
	
	public Map<String,Object> saveBugget(HttpServletRequest httpRequest,User user) {
		
		try{
			if(StringUtil.isEmpty(httpRequest.getParameter("applyQuarter"))){
				return CallBackConstant.FAILED.callback("申请季度不能为空!");
			}
			if(StringUtil.isEmpty(httpRequest.getParameter("budgetFee"))){
				return CallBackConstant.FAILED.callback("费用预算不能为空!");
			}
			
			String cityIdStr=httpRequest.getParameter("cityId");
			if(StringUtil.isEmpty(cityIdStr)){
				return CallBackConstant.FAILED.callback("城市不能为空!");
			}
			
			int cityId=Integer.parseInt(cityIdStr);
			
			
			PageData pd=new PageData();
			pd.put("applyQuarter", httpRequest.getParameter("applyQuarter"));
			pd.put("city", "'"+CityListConstants.getCityByCityId(cityId)+"'");
			List<Budget> list=budgetMapper.queryAllBudgetList(pd);
			if(list!=null && list.size()>0){
				return CallBackConstant.FAILED.callback("该城市当前季度已经存在预算，不能重复添加!");
			}
			
			Budget record=new Budget();
			record.setApplyArea(httpRequest.getParameter("applyArea"));
			record.setApplyQuarter(httpRequest.getParameter("applyQuarter"));
			record.setBudgetFee(new BigDecimal(httpRequest.getParameter("budgetFee")));
			record.setCityId(cityId);
			record.setCity(CityListConstants.getCityByCityId(cityId));
			record.setOperator(user.getRealname());
			record.setOperatorNo(user.getUserno());
			record.setOperatorTime(new Date());
			int result=budgetMapper.saveBugget(record);
			if(result>0){
				return CallBackConstant.SUCCESS.callback("保存成功!");
			}else{
				return CallBackConstant.FAILED.callback("保存失败!");
			}
		}catch(Exception e){
			logger.error("系统异常，"+e);
			return CallBackConstant.FAILED.callback("系统异常，请联系管理员!");
		}
	}

	public Budget queryOneById(Integer id) {
		return budgetMapper.queryOneById(id);
	}

	public Map<String,Object> updateButgetById(HttpServletRequest httpRequest,User user) {
		try {
	        String id=httpRequest.getParameter("id");
	        if(StringUtil.isEmpty(id)){
	        	return CallBackConstant.FAILED.callback("id不能为空");
	        }
	        
	        Budget budget= budgetMapper.queryOneById(Integer.parseInt(id));
	        if(budget==null){
	        	return CallBackConstant.FAILED.callback("记录不存在");
	        }
	        
	        String cityIdStr=httpRequest.getParameter("cityId");
			if(StringUtil.isEmpty(cityIdStr)){
				return CallBackConstant.FAILED.callback("城市不能为空!");
			}
			
			int cityId=Integer.parseInt(cityIdStr);
	        budget.setApplyQuarter(httpRequest.getParameter("applyQuarter"));
	        budget.setBudgetFee(new BigDecimal(httpRequest.getParameter("budgetFee")));
	        budget.setCityId(cityId);
	        budget.setCity(CityListConstants.getCityByCityId(cityId));
			
			int result=budgetMapper.updateButgetById(budget);
			if(result>0){
				return CallBackConstant.SUCCESS.callback("保存成功!");
			}else{
				return CallBackConstant.FAILED.callback("保存失败!");
			}
		} catch (Exception e) {
			logger.error("导出费用报表出现异常，"+e);
			e.printStackTrace();
			return CallBackConstant.FAILED.callback("系统异常，请联系管理员！");
		}
	}

	public List<Budget> queryBudgetListPage(PageData pd,User user) {
		Integer roleId=user.getRole_id();
		if(roleId==2){ //分公司财务
			pd.put("city", user.getCanSeeCity());
		}
		return budgetMapper.queryBudgetListPage(pd);
	}

	public List<Budget> queryAllBudgetList(PageData pd,User user) {
		Integer roleId=user.getRole_id();
		if(roleId==2){ //分公司财务
			pd.put("city", user.getCanSeeCity());
		}
		return budgetMapper.queryBudgetListPage(pd);
	}

	protected BaseMapper<Budget> getBaseMapper() {
		return budgetMapper;
	}
	
}
