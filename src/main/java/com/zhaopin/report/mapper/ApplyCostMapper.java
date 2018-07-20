package com.zhaopin.report.mapper;

import java.util.List;
import java.util.Map;

import com.zhaopin.report.common.dao.BaseMapper;
import com.zhaopin.report.common.vo.PageData;
import com.zhaopin.report.mapper.model.ApplyCost;

public interface ApplyCostMapper extends BaseMapper<ApplyCost>{
    int deleteById(Integer costId);
    
    int saveApplyCost(ApplyCost record);

    ApplyCost queryOneById(Integer costId);

    int updateById(ApplyCost record);
    
    List<ApplyCost> queryApplyCostListPage(PageData pd);
    
    List<ApplyCost> queryAllApplyCostList(PageData pd);
    
    List<Map<String,Object>> calCityCostListPage(PageData pd);
    
    List<Map<String,Object>> calCityCostAllList(PageData pd);
    
    List<Map<String,Object>> queryQuarterAllFee(PageData pd);
    
    List<Map<String,Object>> queryYearCityRealFeeList(PageData pd);
    
    List<Map<String,Object>> queryYearCitytotalFeeList(PageData pd);
    
}