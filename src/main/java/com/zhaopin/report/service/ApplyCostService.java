package com.zhaopin.report.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zhaopin.report.common.service.BaseService;
import com.zhaopin.report.common.vo.PageData;
import com.zhaopin.report.mapper.model.ApplyCost;
import com.zhaopin.report.mapper.model.User;

public interface ApplyCostService  extends BaseService<ApplyCost>{

    
	Map<String,Object> saveApplyCost(HttpServletRequest httpRequest,User loginInfo);

    ApplyCost queryOneById(Integer costId);

    List<ApplyCost> queryApplyCostListPage(PageData pd,User loginInfo);
	
    List<ApplyCost> queryAllApplyCostList(PageData pd,User loginInfo);
    
    Map<String,Object> updateCostApplyStatusById(PageData pd);
    
    List<Map<String,Object>> calCityCostListPage(PageData pd,User loginInfo);
    
    List<Map<String,Object>> calCityCostAllList(PageData pd,User loginInfo);
    
    List<Map<String,Object>> queryQuarterAllFee(PageData pd);
    
    List<Map<String,Object>> queryYearBudgetFeeList(PageData pd);
    
}
