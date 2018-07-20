package com.zhaopin.report.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zhaopin.report.common.service.BaseService;
import com.zhaopin.report.common.vo.PageData;
import com.zhaopin.report.mapper.model.Budget;
import com.zhaopin.report.mapper.model.User;

public interface BudgetService  extends BaseService<Budget>{

	Map<String,Object> saveBugget(HttpServletRequest httpRequest,User user);

    Budget queryOneById(Integer id);

    Map<String,Object> updateButgetById(HttpServletRequest httpRequest,User user);
    
    List<Budget> queryBudgetListPage(PageData pd,User user);
    
    List<Budget> queryAllBudgetList(PageData pd,User user);

}
