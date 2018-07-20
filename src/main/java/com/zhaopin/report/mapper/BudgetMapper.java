package com.zhaopin.report.mapper;

import java.util.List;
import java.util.Map;

import com.zhaopin.report.common.dao.BaseMapper;
import com.zhaopin.report.common.vo.PageData;
import com.zhaopin.report.mapper.model.Budget;

public interface BudgetMapper extends BaseMapper<Budget>{

    int saveBugget(Budget record);

    Budget queryOneById(Integer id);

    int updateButgetById(Budget record);
    
    List<Budget> queryBudgetListPage(PageData pd);
    
    List<Budget> queryAllBudgetList(PageData pd);
    
    Map<String,Object> calBudgetFeeSum(PageData pd);

    List<Map<String,Object>> queryYearBudgetFeeList(PageData pd);
    
    
}