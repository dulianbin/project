package com.zhaopin.report.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.zhaopin.report.mapper.ApplyCostMapper;
import com.zhaopin.report.mapper.BudgetMapper;
import com.zhaopin.report.mapper.model.ApplyCost;
import com.zhaopin.report.mapper.model.User;
import com.zhaopin.report.service.ApplyCostService;
import com.zhaopin.report.util.CallBackConstant;
import com.zhaopin.report.util.StringUtil;


@Service("applyCostServiceImpl")
public class ApplyCostServiceImpl  extends BaseServiceImpl<ApplyCost> implements ApplyCostService{

	private final static Logger logger = Logger.getLogger(ApplyCostServiceImpl.class);
	@Autowired
	private ApplyCostMapper applyCostMapper;
	@Autowired
	private BudgetMapper budgetMapper;
	
	@Override
	protected BaseMapper<ApplyCost> getBaseMapper() {
		return applyCostMapper;
	}

	public Map<String,Object> saveApplyCost(HttpServletRequest httpRequest, User loginInfo) {
		
		ApplyCost applyCost=new ApplyCost();
		applyCost.setApplyQuarter(httpRequest.getParameter("applyQuarter"));
		
		String cityIdStr=httpRequest.getParameter("cityId");
		if(StringUtil.isEmpty(cityIdStr)){
			return CallBackConstant.FAILED.callback("城市不能为空!");
		}
		
		int cityId=Integer.parseInt(cityIdStr);
		applyCost.setCityId(cityId);
		applyCost.setCity(CityListConstants.getCityByCityId(cityId));
		applyCost.setDeptId(loginInfo.getDeptId());
		applyCost.setDeptName(httpRequest.getParameter("deptName"));
		applyCost.setApplyTime(new Date());
		applyCost.setCostTypeName(httpRequest.getParameter("costTypeName"));
		applyCost.setUserId(loginInfo.getUserId());
		applyCost.setUsername(loginInfo.getUsername());
		applyCost.setRealName(loginInfo.getRealname());
		BigDecimal totalFeeBigDecimal=new BigDecimal(httpRequest.getParameter("totalFee"));
		BigDecimal realFeeBigDecimal=new BigDecimal(httpRequest.getParameter("realFee"));
		
		applyCost.setTotalFee(totalFeeBigDecimal);
		applyCost.setRealFee(realFeeBigDecimal);
		applyCost.setShengyuFee(totalFeeBigDecimal.subtract(realFeeBigDecimal));
		applyCost.setCostStatus(1);
		applyCost.setApplyArea(httpRequest.getParameter("applyArea"));
		applyCost.setRemark(httpRequest.getParameter("remark"));
		
		int result=applyCostMapper.saveApplyCost(applyCost);
		if(result>0){
			return CallBackConstant.SUCCESS.callback("保存成功!");
		}else{
			return CallBackConstant.FAILED.callback("保存失败!");
		}
	}

	public ApplyCost queryOneById(Integer costId) {
		return null;
	}

	public List<ApplyCost> queryApplyCostListPage(PageData pd,User loginInfo) {
		
		if(loginInfo==null){
			return null;
		}
		int roleId=loginInfo.getRole_id();
		
		if(roleId==1){  //个人财务
			pd.put("username", loginInfo.getUsername());
		}else if(roleId==2){ //分公司财务
			pd.put("city", loginInfo.getCanSeeCity());
		}else if(roleId==3){ //区域财务
		}
		
		pd.put("costStatus", 1);
		return applyCostMapper.queryApplyCostListPage(pd);
	}

	public List<ApplyCost> queryAllApplyCostList(PageData pd,User loginInfo) {
		if(loginInfo==null){
			return null;
		}
		int roleId=loginInfo.getRole_id();
		
		if(roleId==1){  //个人财务
			pd.put("username", loginInfo.getUsername());
		}else if(roleId==2){ //分公司财务
			pd.put("city", loginInfo.getCanSeeCity());
		}else if(roleId==3){ //区域财务
		}
		pd.put("costStatus", 1);
		return applyCostMapper.queryAllApplyCostList(pd);
	}

	public Map<String,Object> updateCostApplyStatusById(PageData pd) {
		try{
			if(pd.get("costId")==null || StringUtil.isEmpty(pd.get("costId").toString())){
				return CallBackConstant.FAILED.callback("请选择要删除的记录！");
			}
			ApplyCost record=new ApplyCost();
			record.setCostId(Integer.parseInt(pd.get("costId").toString()));
			record.setCostStatus(2);
			int result=applyCostMapper.updateById(record);
			if(result>0){
				return CallBackConstant.SUCCESS.callback("删除成功！");
			}else{
				return CallBackConstant.FAILED.callback("记录不存在！");
			}
		}catch(Exception e){
			logger.error("系统异常，"+e);
			return CallBackConstant.FAILED.callback("系统异常，请联系管理员！！");
		}
		
	}

	public List<Map<String,Object>> calCityCostListPage(PageData pd, User loginInfo) {
		try{
			if(loginInfo==null){
				return null;
			}
			int roleId=loginInfo.getRole_id();
			if(roleId==2){ //分公司财务
				pd.put("city", loginInfo.getCanSeeCity());
			}
			return applyCostMapper.calCityCostListPage(pd);
		}catch(Exception e){
			logger.error("系统异常，"+e);
			return null;
		}
	}
	
	public List<Map<String,Object>> calCityCostAllList(PageData pd, User loginInfo) {
		try{
			if(loginInfo==null){
				return null;
			}
			int roleId=loginInfo.getRole_id();
			if(roleId==2){ //分公司财务
				pd.put("city", loginInfo.getCanSeeCity());
			}
			return applyCostMapper.calCityCostAllList(pd);
		}catch(Exception e){
			logger.error("系统异常，"+e);
			return null;
		}
	}
	

	public List<Map<String, Object>> queryQuarterAllFee(PageData pd) {
		try{
			pd.put("quarter_1", pd.get("quarter")+"1");
			pd.put("quarter_2", pd.get("quarter")+"2");
			pd.put("quarter_3", pd.get("quarter")+"3");
			pd.put("quarter_4", pd.get("quarter")+"4");
			List<Map<String, Object>> tempList=applyCostMapper.queryQuarterAllFee(pd);
			
			PageData pd2=new PageData();
			pd2.put("city", pd.get("city"));
			String quarter_list="'"+pd.get("quarter")+"1"+"', '"+pd.get("quarter")+"2"+"', '"+pd.get("quarter")+"3"+"', '"+pd.get("quarter")+"4'";
			pd2.put("quarter_list", quarter_list);
			Map<String, Object> budgetFeeSumMap=budgetMapper.calBudgetFeeSum(pd2);
			Object budget_fee_sum=null;
			if(budgetFeeSumMap!=null){
				budget_fee_sum=budgetFeeSumMap.get("budget_fee_sum");
			}
			
			BigDecimal jishu=new BigDecimal(100);
			
			DecimalFormat df2 =new DecimalFormat("0.00");
			if(tempList!=null){
				for(Map map:tempList){
					map.put("city", pd.get("city"));
					BigDecimal applyCostSum=calSum(map);
					map.put("apply_allfee", calSum(map));
					if(budget_fee_sum!=null){
						map.put("budget_fee_sum", budget_fee_sum);
						BigDecimal budget_fee_sum_bd=new BigDecimal(budget_fee_sum.toString());
						if(budget_fee_sum_bd.floatValue()>0){
							map.put("fee_rante", df2.format(applyCostSum.divide(budget_fee_sum_bd,4,RoundingMode.HALF_DOWN).multiply(jishu))+"%");
						}else{
							map.put("fee_rante", "年度预算费用缺失");
						}
						
					}else{
						map.put("budget_fee_sum", "年度预算费用缺失");
						map.put("fee_rante", "年度预算费用缺失");
					}
					
				}
			}
			
			
			return tempList;
		}catch(Exception e){
			logger.error("系统异常，"+e);
			return null;
		}
	}
	
	private BigDecimal calSum(Map map){
		BigDecimal quarter_1=new BigDecimal(map.get("quarter_1").toString());
		BigDecimal quarter_2=new BigDecimal(map.get("quarter_2").toString());
		BigDecimal quarter_3=new BigDecimal(map.get("quarter_3").toString());
		BigDecimal quarter_4=new BigDecimal(map.get("quarter_4").toString());
		return quarter_1.add(quarter_2).add(quarter_3).add(quarter_4);
	}

	public List<Map<String, Object>> queryYearBudgetFeeList(PageData pd) {
		try{
			List<Map<String,Object>> allList=new ArrayList<Map<String,Object>>();
			Object year=pd.get("year");
			String yearStr="'"+year+"1', '"+year+"2', '"+year+"3', '"+year+"4'";
			pd.put("apply_quarter", yearStr);
			
			//计算年度费用预算
			List<Map<String,Object>>  yearBudgetFeeList=budgetMapper.queryYearBudgetFeeList(pd);
			if(yearBudgetFeeList!=null){
				int yearBudgetFeeListLength=yearBudgetFeeList.size();
				allList.add(calTotalBudGet(yearBudgetFeeList,yearBudgetFeeListLength,year.toString().substring(0, year.toString().length()-1)+"总预算"));
			    for(int i=1;i<=yearBudgetFeeListLength;i++){
			    	yearBudgetFeeList.get(i-1).put("city", "预算费用");
			    	yearBudgetFeeList.get(i-1).put("apply_quarter", year+""+i);
			    	allList.add(yearBudgetFeeList.get(i-1));
			    }
			    if(4-yearBudgetFeeListLength>0){
			    	for(int i=yearBudgetFeeListLength+1;i<=4;i++){
			    		Map<String,Object> map=new HashMap<String,Object>();
			    		map.put("sz", "0");
			    		map.put("gz", "0");
			    		map.put("cs", "0");
			    		map.put("fz", "0");
			    		map.put("nc", "0");
			    		map.put("fs", "0");
			    		map.put("xm", "0");
			    		map.put("dg", "0");
			    		map.put("qyyy", "0");
			    		map.put("zh", "0");
			    		map.put("hk", "0");
			    		map.put("city", "预算费用");
			    		map.put("apply_quarter", year+""+i);
			    		allList.add(map);
			    	}
			    }
			}
			
			//计算年度实际支出费用
			List<Map<String,Object>>  yearCityRealFeeList=applyCostMapper.queryYearCityRealFeeList(pd);
			if(yearCityRealFeeList!=null){
				int yearBudgetFeeListLength=yearCityRealFeeList.size();
				
				for(int i=1;i<=yearBudgetFeeListLength;i++){
					yearCityRealFeeList.get(i-1).put("city", "实际支出");
					yearCityRealFeeList.get(i-1).put("apply_quarter", year+""+i);
			    	allList.add(yearCityRealFeeList.get(i-1));
			    }
			    if(4-yearBudgetFeeListLength>0){
			    	for(int i=yearBudgetFeeListLength+1;i<=4;i++){
			    		Map<String,Object> map=new HashMap<String,Object>();
			    		map.put("sz", "0");
			    		map.put("gz", "0");
			    		map.put("cs", "0");
			    		map.put("fz", "0");
			    		map.put("nc", "0");
			    		map.put("fs", "0");
			    		map.put("xm", "0");
			    		map.put("dg", "0");
			    		map.put("qyyy", "0");
			    		map.put("zh", "0");
			    		map.put("hk", "0");
			    		map.put("city", "实际支出");
			    		map.put("apply_quarter", year+""+i);
			    		allList.add(map);
			    	}
			    }
			}
			
			//计算年度预算支出费用
			List<Map<String,Object>> yearCityTotalFeeList=applyCostMapper.queryYearCitytotalFeeList(pd);
			if(yearCityTotalFeeList!=null){
				int yearBudgetFeeListLength=yearCityTotalFeeList.size();
				
				for(int i=1;i<=yearBudgetFeeListLength;i++){
					yearCityTotalFeeList.get(i-1).put("city", "预算支出");
					yearCityTotalFeeList.get(i-1).put("apply_quarter", year+""+i);
			    	allList.add(yearCityTotalFeeList.get(i-1));
			    }
			    if(4-yearBudgetFeeListLength>0){
			    	for(int i=yearBudgetFeeListLength+1;i<=4;i++){
			    		Map<String,Object> map=new HashMap<String,Object>();
			    		map.put("sz", "0");
			    		map.put("gz", "0");
			    		map.put("cs", "0");
			    		map.put("fz", "0");
			    		map.put("nc", "0");
			    		map.put("fs", "0");
			    		map.put("xm", "0");
			    		map.put("dg", "0");
			    		map.put("qyyy", "0");
			    		map.put("zh", "0");
			    		map.put("hk", "0");
			    		map.put("city", "预算支出");
			    		map.put("apply_quarter", year+""+i);
			    		allList.add(map);
			    	}
			    }
			}
			
			//计算年度实际剩余费用
			calshijiShengyufee(allList,year.toString());
			
			
			
			//计算年度预算剩余费用
			calYuSuanShengyufee(allList,year.toString());
			
			
			return allList;
		}catch(Exception e){
			logger.error("系统异常，"+e);
			e.printStackTrace();
			return null;
		}
	}
	
	private void calshijiShengyufee(List<Map<String,Object>> allList,String year){
		//第一季度实际剩余
		Map<String,Object> tempMap_1=new HashMap<String ,Object>();
		BigDecimal szSum_1=new BigDecimal(allList.get(1).get("sz").toString()).subtract(new BigDecimal(allList.get(5).get("sz").toString()));  //深圳
		BigDecimal gzSum_1=new BigDecimal(allList.get(1).get("gz").toString()).subtract(new BigDecimal(allList.get(5).get("gz").toString()));  //广州
		BigDecimal csSum_1=new BigDecimal(allList.get(1).get("cs").toString()).subtract(new BigDecimal(allList.get(5).get("cs").toString()));  //长沙
		BigDecimal fzSum_1=new BigDecimal(allList.get(1).get("fz").toString()).subtract(new BigDecimal(allList.get(5).get("fz").toString()));  //福州
		BigDecimal ncSum_1=new BigDecimal(allList.get(1).get("nc").toString()).subtract(new BigDecimal(allList.get(5).get("nc").toString()));  //南昌
		BigDecimal fsSum_1=new BigDecimal(allList.get(1).get("fs").toString()).subtract(new BigDecimal(allList.get(5).get("fs").toString()));  //长沙
		BigDecimal xmSum_1=new BigDecimal(allList.get(1).get("xm").toString()).subtract(new BigDecimal(allList.get(5).get("xm").toString()));  //长沙
		BigDecimal dgSum_1=new BigDecimal(allList.get(1).get("dg").toString()).subtract(new BigDecimal(allList.get(5).get("dg").toString()));  //长沙
		BigDecimal qyyySum_1=new BigDecimal(allList.get(1).get("qyyy").toString()).subtract(new BigDecimal(allList.get(5).get("qyyy").toString()));  //长沙
		BigDecimal zhSum_1=new BigDecimal(allList.get(1).get("zh").toString()).subtract(new BigDecimal(allList.get(5).get("zh").toString())); //长沙
		BigDecimal hkSum_1=new BigDecimal(allList.get(1).get("hk").toString()).subtract(new BigDecimal(allList.get(5).get("hk").toString()));  //长沙
		tempMap_1.put("sz", szSum_1);
		tempMap_1.put("gz", gzSum_1);
		tempMap_1.put("cs", csSum_1);
		tempMap_1.put("fz", fzSum_1);
		tempMap_1.put("nc", ncSum_1);
		tempMap_1.put("fs", fsSum_1);
		tempMap_1.put("xm", xmSum_1);
		tempMap_1.put("dg", dgSum_1);
		tempMap_1.put("qyyy", qyyySum_1);
		tempMap_1.put("zh", zhSum_1);
		tempMap_1.put("hk", hkSum_1);
		tempMap_1.put("city", "实际剩余");
		tempMap_1.put("apply_quarter", year+"1");
		allList.add(tempMap_1);
		
		//第二季度实际剩余
		Map<String,Object> tempMap_2=new HashMap<String ,Object>();
		BigDecimal szSum_2=new BigDecimal(allList.get(2).get("sz").toString()).subtract(new BigDecimal(allList.get(6).get("sz").toString()));  //深圳
		BigDecimal gzSum_2=new BigDecimal(allList.get(2).get("gz").toString()).subtract(new BigDecimal(allList.get(6).get("gz").toString()));  //广州
		BigDecimal csSum_2=new BigDecimal(allList.get(2).get("cs").toString()).subtract(new BigDecimal(allList.get(6).get("cs").toString()));  //长沙
		BigDecimal fzSum_2=new BigDecimal(allList.get(2).get("fz").toString()).subtract(new BigDecimal(allList.get(6).get("fz").toString()));  //福州
		BigDecimal ncSum_2=new BigDecimal(allList.get(2).get("nc").toString()).subtract(new BigDecimal(allList.get(6).get("nc").toString()));  //南昌
		BigDecimal fsSum_2=new BigDecimal(allList.get(2).get("fs").toString()).subtract(new BigDecimal(allList.get(6).get("fs").toString()));  //长沙
		BigDecimal xmSum_2=new BigDecimal(allList.get(2).get("xm").toString()).subtract(new BigDecimal(allList.get(6).get("xm").toString()));  //长沙
		BigDecimal dgSum_2=new BigDecimal(allList.get(2).get("dg").toString()).subtract(new BigDecimal(allList.get(6).get("dg").toString()));  //长沙
		BigDecimal qyyySum_2=new BigDecimal(allList.get(2).get("qyyy").toString()).subtract(new BigDecimal(allList.get(6).get("qyyy").toString()));  //长沙
		BigDecimal zhSum_2=new BigDecimal(allList.get(2).get("zh").toString()).subtract(new BigDecimal(allList.get(6).get("zh").toString())); //长沙
		BigDecimal hkSum_2=new BigDecimal(allList.get(2).get("hk").toString()).subtract(new BigDecimal(allList.get(6).get("hk").toString()));  //长沙
		
		//累计统计实际剩余
		szSum_2=szSum_1.add(szSum_2);
		gzSum_2=gzSum_1.add(gzSum_2);
		csSum_2=csSum_1.add(csSum_2);
		fzSum_2=fzSum_1.add(fzSum_2);
		ncSum_2=ncSum_1.add(ncSum_2);
		fsSum_2=fsSum_1.add(fsSum_2);
		xmSum_2=xmSum_1.add(xmSum_2);
		dgSum_2=dgSum_1.add(dgSum_2);
		qyyySum_2=qyyySum_1.add(qyyySum_2);
		zhSum_2=zhSum_1.add(zhSum_2);
		hkSum_2=hkSum_1.add(hkSum_2);
		
		
		tempMap_2.put("sz", szSum_2);
		tempMap_2.put("gz", gzSum_2);
		tempMap_2.put("cs", csSum_2);
		tempMap_2.put("fz", fzSum_2);
		tempMap_2.put("nc", ncSum_2);
		tempMap_2.put("fs", fsSum_2);
		tempMap_2.put("xm", xmSum_2);
		tempMap_2.put("dg", dgSum_2);
		tempMap_2.put("qyyy", qyyySum_2);
		tempMap_2.put("zh", zhSum_2);
		tempMap_2.put("hk", hkSum_2);
		tempMap_2.put("city", "实际剩余");
		tempMap_2.put("apply_quarter", year+"2");
		allList.add(tempMap_2);
		
		
		
		//第三季度实际剩余
		Map<String,Object> tempMap_3=new HashMap<String ,Object>();
		BigDecimal szSum_3=new BigDecimal(allList.get(3).get("sz")==null?"0":allList.get(3).get("sz").toString()).subtract(new BigDecimal(allList.get(7).get("sz")==null?"0":allList.get(7).get("sz").toString()));  //深圳
		BigDecimal gzSum_3=new BigDecimal(allList.get(3).get("gz")==null?"0":allList.get(3).get("gz").toString()).subtract(new BigDecimal(allList.get(7).get("gz").toString()));  //广州
		BigDecimal csSum_3=new BigDecimal(allList.get(3).get("cs").toString()).subtract(new BigDecimal(allList.get(7).get("cs").toString()));  //长沙
		BigDecimal fzSum_3=new BigDecimal(allList.get(3).get("fz").toString()).subtract(new BigDecimal(allList.get(7).get("fz").toString()));  //福州
		BigDecimal ncSum_3=new BigDecimal(allList.get(3).get("nc").toString()).subtract(new BigDecimal(allList.get(7).get("nc").toString()));  //南昌
		BigDecimal fsSum_3=new BigDecimal(allList.get(3).get("fs").toString()).subtract(new BigDecimal(allList.get(7).get("fs").toString()));  //长沙
		BigDecimal xmSum_3=new BigDecimal(allList.get(3).get("xm").toString()).subtract(new BigDecimal(allList.get(7).get("xm").toString()));  //长沙
		BigDecimal dgSum_3=new BigDecimal(allList.get(3).get("dg").toString()).subtract(new BigDecimal(allList.get(7).get("dg").toString()));  //长沙
		BigDecimal qyyySum_3=new BigDecimal(allList.get(3).get("qyyy").toString()).subtract(new BigDecimal(allList.get(7).get("qyyy").toString()));  //长沙
		BigDecimal zhSum_3=new BigDecimal(allList.get(3).get("zh").toString()).subtract(new BigDecimal(allList.get(7).get("zh").toString())); //长沙
		BigDecimal hkSum_3=new BigDecimal(allList.get(3).get("hk").toString()).subtract(new BigDecimal(allList.get(7).get("hk").toString()));  //长沙
		
		//累计统计实际剩余
		szSum_3=szSum_3.add(szSum_2);
		gzSum_3=gzSum_3.add(gzSum_2);
		csSum_3=csSum_3.add(csSum_2);
		fzSum_3=fzSum_3.add(fzSum_2);
		ncSum_3=ncSum_3.add(ncSum_2);
		fsSum_3=fsSum_3.add(fsSum_2);
		xmSum_3=xmSum_3.add(xmSum_2);
		dgSum_3=dgSum_3.add(dgSum_2);
		qyyySum_3=qyyySum_3.add(qyyySum_2);
		zhSum_3=zhSum_3.add(zhSum_2);
		hkSum_3=hkSum_3.add(hkSum_2);
		
		
		tempMap_3.put("sz", szSum_3);
		tempMap_3.put("gz", gzSum_3);
		tempMap_3.put("cs", csSum_3);
		tempMap_3.put("fz", fzSum_3);
		tempMap_3.put("nc", ncSum_3);
		tempMap_3.put("fs", fsSum_3);
		tempMap_3.put("xm", xmSum_3);
		tempMap_3.put("dg", dgSum_3);
		tempMap_3.put("qyyy", qyyySum_3);
		tempMap_3.put("zh", zhSum_3);
		tempMap_3.put("hk", hkSum_3);
		tempMap_3.put("city", "实际剩余");
		tempMap_3.put("apply_quarter", year+"3");
		allList.add(tempMap_3);
		
		
		
		//第四季度实际剩余
		Map<String,Object> tempMap_4=new HashMap<String ,Object>();
		BigDecimal szSum_4=new BigDecimal(allList.get(4).get("sz")==null?"0":allList.get(4).get("sz").toString()).subtract(new BigDecimal(allList.get(8).get("sz")==null?"0":allList.get(8).get("sz").toString()));  //深圳
		BigDecimal gzSum_4=new BigDecimal(allList.get(4).get("gz")==null?"0":allList.get(4).get("gz").toString()).subtract(new BigDecimal(allList.get(8).get("gz")==null?"0":allList.get(8).get("gz").toString()));  //广州
		BigDecimal csSum_4=new BigDecimal(allList.get(4).get("cs")==null?"0":allList.get(4).get("cs").toString()).subtract(new BigDecimal(allList.get(8).get("cs")==null?"0":allList.get(8).get("cs").toString()));  //长沙
		BigDecimal fzSum_4=new BigDecimal(allList.get(4).get("fz")==null?"0":allList.get(4).get("fz").toString()).subtract(new BigDecimal(allList.get(8).get("fz")==null?"0":allList.get(8).get("fz").toString()));  //福州
		BigDecimal ncSum_4=new BigDecimal(allList.get(4).get("nc")==null?"0":allList.get(4).get("nc").toString()).subtract(new BigDecimal(allList.get(8).get("nc")==null?"0":allList.get(8).get("nc").toString()));  //南昌
		BigDecimal fsSum_4=new BigDecimal(allList.get(4).get("fs")==null?"0":allList.get(4).get("fs").toString()).subtract(new BigDecimal(allList.get(8).get("fs")==null?"0":allList.get(8).get("fs").toString()));  //长沙
		BigDecimal xmSum_4=new BigDecimal(allList.get(4).get("xm")==null?"0":allList.get(4).get("xm").toString()).subtract(new BigDecimal(allList.get(8).get("xm")==null?"0":allList.get(8).get("xm").toString()));  //长沙
		BigDecimal dgSum_4=new BigDecimal(allList.get(4).get("dg")==null?"0":allList.get(4).get("dg").toString()).subtract(new BigDecimal(allList.get(8).get("dg")==null?"0":allList.get(8).get("dg").toString()));  //长沙
		BigDecimal qyyySum_4=new BigDecimal(allList.get(4).get("qyyy")==null?"0":allList.get(4).get("qyyy").toString()).subtract(new BigDecimal(allList.get(8).get("qyyy")==null?"0":allList.get(8).get("qyyy").toString()));  //长沙
		BigDecimal zhSum_4=new BigDecimal(allList.get(4).get("zh")==null?"0":allList.get(4).get("zh").toString()).subtract(new BigDecimal(allList.get(8).get("zh")==null?"0":allList.get(8).get("zh").toString())); //长沙
		BigDecimal hkSum_4=new BigDecimal(allList.get(4).get("hk")==null?"0":allList.get(4).get("hk").toString()).subtract(new BigDecimal(allList.get(8).get("hk")==null?"0":allList.get(8).get("hk").toString()));  //长沙
		
		//累计统计实际剩余
		szSum_4=szSum_3.add(szSum_4);
		gzSum_4=gzSum_3.add(gzSum_4);
		csSum_4=csSum_3.add(csSum_4);
		fzSum_4=fzSum_3.add(fzSum_4);
		ncSum_4=ncSum_3.add(ncSum_4);
		fsSum_4=fsSum_3.add(fsSum_4);
		xmSum_4=xmSum_3.add(xmSum_4);
		dgSum_4=dgSum_3.add(dgSum_4);
		qyyySum_4=qyyySum_3.add(qyyySum_4);
		zhSum_4=zhSum_3.add(zhSum_4);
		hkSum_4=hkSum_3.add(hkSum_4);
		
		
		tempMap_4.put("sz", szSum_4);
		tempMap_4.put("gz", gzSum_4);
		tempMap_4.put("cs", csSum_4);
		tempMap_4.put("fz", fzSum_4);
		tempMap_4.put("nc", ncSum_4);
		tempMap_4.put("fs", fsSum_4);
		tempMap_4.put("xm", xmSum_4);
		tempMap_4.put("dg", dgSum_4);
		tempMap_4.put("qyyy", qyyySum_4);
		tempMap_4.put("zh", zhSum_4);
		tempMap_4.put("hk", hkSum_4);
		tempMap_4.put("city", "实际剩余");
		tempMap_4.put("apply_quarter", year+"4");
		allList.add(tempMap_4);
		
		
		
		
	}
	
	
	
	private void calYuSuanShengyufee(List<Map<String,Object>> allList,String year){
		//第一季度实际剩余
		Map<String,Object> tempMap_1=new HashMap<String ,Object>();
		BigDecimal szSum_1=new BigDecimal(allList.get(1).get("sz").toString()).subtract(new BigDecimal(allList.get(9).get("sz").toString()));  //深圳
		BigDecimal gzSum_1=new BigDecimal(allList.get(1).get("gz").toString()).subtract(new BigDecimal(allList.get(9).get("gz").toString()));  //广州
		BigDecimal csSum_1=new BigDecimal(allList.get(1).get("cs").toString()).subtract(new BigDecimal(allList.get(9).get("cs").toString()));  //长沙
		BigDecimal fzSum_1=new BigDecimal(allList.get(1).get("fz").toString()).subtract(new BigDecimal(allList.get(9).get("fz").toString()));  //福州
		BigDecimal ncSum_1=new BigDecimal(allList.get(1).get("nc").toString()).subtract(new BigDecimal(allList.get(9).get("nc").toString()));  //南昌
		BigDecimal fsSum_1=new BigDecimal(allList.get(1).get("fs").toString()).subtract(new BigDecimal(allList.get(9).get("fs").toString()));  //长沙
		BigDecimal xmSum_1=new BigDecimal(allList.get(1).get("xm").toString()).subtract(new BigDecimal(allList.get(9).get("xm").toString()));  //长沙
		BigDecimal dgSum_1=new BigDecimal(allList.get(1).get("dg").toString()).subtract(new BigDecimal(allList.get(9).get("dg").toString()));  //长沙
		BigDecimal qyyySum_1=new BigDecimal(allList.get(1).get("qyyy").toString()).subtract(new BigDecimal(allList.get(9).get("qyyy").toString()));  //长沙
		BigDecimal zhSum_1=new BigDecimal(allList.get(1).get("zh").toString()).subtract(new BigDecimal(allList.get(9).get("zh").toString())); //长沙
		BigDecimal hkSum_1=new BigDecimal(allList.get(1).get("hk").toString()).subtract(new BigDecimal(allList.get(9).get("hk").toString()));  //长沙
		tempMap_1.put("sz", szSum_1);
		tempMap_1.put("gz", gzSum_1);
		tempMap_1.put("cs", csSum_1);
		tempMap_1.put("fz", fzSum_1);
		tempMap_1.put("nc", ncSum_1);
		tempMap_1.put("fs", fsSum_1);
		tempMap_1.put("xm", xmSum_1);
		tempMap_1.put("dg", dgSum_1);
		tempMap_1.put("qyyy", qyyySum_1);
		tempMap_1.put("zh", zhSum_1);
		tempMap_1.put("hk", hkSum_1);
		tempMap_1.put("city", "预算剩余");
		tempMap_1.put("apply_quarter", year+"1");
		allList.add(tempMap_1);
		
		//第二季度实际剩余
		Map<String,Object> tempMap_2=new HashMap<String ,Object>();
		BigDecimal szSum_2=new BigDecimal(allList.get(2).get("sz").toString()).subtract(new BigDecimal(allList.get(10).get("sz").toString()));  //深圳
		BigDecimal gzSum_2=new BigDecimal(allList.get(2).get("gz").toString()).subtract(new BigDecimal(allList.get(10).get("gz").toString()));  //广州
		BigDecimal csSum_2=new BigDecimal(allList.get(2).get("cs").toString()).subtract(new BigDecimal(allList.get(10).get("cs").toString()));  //长沙
		BigDecimal fzSum_2=new BigDecimal(allList.get(2).get("fz").toString()).subtract(new BigDecimal(allList.get(10).get("fz").toString()));  //福州
		BigDecimal ncSum_2=new BigDecimal(allList.get(2).get("nc").toString()).subtract(new BigDecimal(allList.get(10).get("nc").toString()));  //南昌
		BigDecimal fsSum_2=new BigDecimal(allList.get(2).get("fs").toString()).subtract(new BigDecimal(allList.get(10).get("fs").toString()));  //长沙
		BigDecimal xmSum_2=new BigDecimal(allList.get(2).get("xm").toString()).subtract(new BigDecimal(allList.get(10).get("xm").toString()));  //长沙
		BigDecimal dgSum_2=new BigDecimal(allList.get(2).get("dg").toString()).subtract(new BigDecimal(allList.get(10).get("dg").toString()));  //长沙
		BigDecimal qyyySum_2=new BigDecimal(allList.get(2).get("qyyy").toString()).subtract(new BigDecimal(allList.get(10).get("qyyy").toString()));  //长沙
		BigDecimal zhSum_2=new BigDecimal(allList.get(2).get("zh").toString()).subtract(new BigDecimal(allList.get(10).get("zh").toString())); //长沙
		BigDecimal hkSum_2=new BigDecimal(allList.get(2).get("hk").toString()).subtract(new BigDecimal(allList.get(10).get("hk").toString()));  //长沙
		
		//累计统计实际剩余
		szSum_2=szSum_1.add(szSum_2);
		gzSum_2=gzSum_1.add(gzSum_2);
		csSum_2=csSum_1.add(csSum_2);
		fzSum_2=fzSum_1.add(fzSum_2);
		ncSum_2=ncSum_1.add(ncSum_2);
		fsSum_2=fsSum_1.add(fsSum_2);
		xmSum_2=xmSum_1.add(xmSum_2);
		dgSum_2=dgSum_1.add(dgSum_2);
		qyyySum_2=qyyySum_1.add(qyyySum_2);
		zhSum_2=zhSum_1.add(zhSum_2);
		hkSum_2=hkSum_1.add(hkSum_2);
		
		
		tempMap_2.put("sz", szSum_2);
		tempMap_2.put("gz", gzSum_2);
		tempMap_2.put("cs", csSum_2);
		tempMap_2.put("fz", fzSum_2);
		tempMap_2.put("nc", ncSum_2);
		tempMap_2.put("fs", fsSum_2);
		tempMap_2.put("xm", xmSum_2);
		tempMap_2.put("dg", dgSum_2);
		tempMap_2.put("qyyy", qyyySum_2);
		tempMap_2.put("zh", zhSum_2);
		tempMap_2.put("hk", hkSum_2);
		tempMap_2.put("city", "预算剩余");
		tempMap_2.put("apply_quarter", year+"2");
		allList.add(tempMap_2);
		
		
		
		//第三季度实际剩余
		Map<String,Object> tempMap_3=new HashMap<String ,Object>();
		BigDecimal szSum_3=new BigDecimal(allList.get(3).get("sz")==null?"0":allList.get(3).get("sz").toString()).subtract(new BigDecimal(allList.get(11).get("sz")==null?"0":allList.get(11).get("sz").toString()));  //深圳
		BigDecimal gzSum_3=new BigDecimal(allList.get(3).get("gz")==null?"0":allList.get(3).get("gz").toString()).subtract(new BigDecimal(allList.get(11).get("gz").toString()));  //广州
		BigDecimal csSum_3=new BigDecimal(allList.get(3).get("cs").toString()).subtract(new BigDecimal(allList.get(11).get("cs").toString()));  //长沙
		BigDecimal fzSum_3=new BigDecimal(allList.get(3).get("fz").toString()).subtract(new BigDecimal(allList.get(11).get("fz").toString()));  //福州
		BigDecimal ncSum_3=new BigDecimal(allList.get(3).get("nc").toString()).subtract(new BigDecimal(allList.get(11).get("nc").toString()));  //南昌
		BigDecimal fsSum_3=new BigDecimal(allList.get(3).get("fs").toString()).subtract(new BigDecimal(allList.get(11).get("fs").toString()));  //长沙
		BigDecimal xmSum_3=new BigDecimal(allList.get(3).get("xm").toString()).subtract(new BigDecimal(allList.get(11).get("xm").toString()));  //长沙
		BigDecimal dgSum_3=new BigDecimal(allList.get(3).get("dg").toString()).subtract(new BigDecimal(allList.get(11).get("dg").toString()));  //长沙
		BigDecimal qyyySum_3=new BigDecimal(allList.get(3).get("qyyy").toString()).subtract(new BigDecimal(allList.get(11).get("qyyy").toString()));  //长沙
		BigDecimal zhSum_3=new BigDecimal(allList.get(3).get("zh").toString()).subtract(new BigDecimal(allList.get(11).get("zh").toString())); //长沙
		BigDecimal hkSum_3=new BigDecimal(allList.get(3).get("hk").toString()).subtract(new BigDecimal(allList.get(11).get("hk").toString()));  //长沙
		
		//累计统计实际剩余
		szSum_3=szSum_3.add(szSum_2);
		gzSum_3=gzSum_3.add(gzSum_2);
		csSum_3=csSum_3.add(csSum_2);
		fzSum_3=fzSum_3.add(fzSum_2);
		ncSum_3=ncSum_3.add(ncSum_2);
		fsSum_3=fsSum_3.add(fsSum_2);
		xmSum_3=xmSum_3.add(xmSum_2);
		dgSum_3=dgSum_3.add(dgSum_2);
		qyyySum_3=qyyySum_3.add(qyyySum_2);
		zhSum_3=zhSum_3.add(zhSum_2);
		hkSum_3=hkSum_3.add(hkSum_2);
		
		
		tempMap_3.put("sz", szSum_3);
		tempMap_3.put("gz", gzSum_3);
		tempMap_3.put("cs", csSum_3);
		tempMap_3.put("fz", fzSum_3);
		tempMap_3.put("nc", ncSum_3);
		tempMap_3.put("fs", fsSum_3);
		tempMap_3.put("xm", xmSum_3);
		tempMap_3.put("dg", dgSum_3);
		tempMap_3.put("qyyy", qyyySum_3);
		tempMap_3.put("zh", zhSum_3);
		tempMap_3.put("hk", hkSum_3);
		tempMap_3.put("city", "预算剩余");
		tempMap_3.put("apply_quarter", year+"3");
		allList.add(tempMap_3);
		
		
		
		//第四季度实际剩余
		Map<String,Object> tempMap_4=new HashMap<String ,Object>();
		BigDecimal szSum_4=new BigDecimal(allList.get(4).get("sz")==null?"0":allList.get(4).get("sz").toString()).subtract(new BigDecimal(allList.get(12).get("sz")==null?"0":allList.get(12).get("sz").toString()));  //深圳
		BigDecimal gzSum_4=new BigDecimal(allList.get(4).get("gz")==null?"0":allList.get(4).get("gz").toString()).subtract(new BigDecimal(allList.get(12).get("gz")==null?"0":allList.get(12).get("gz").toString()));  //广州
		BigDecimal csSum_4=new BigDecimal(allList.get(4).get("cs")==null?"0":allList.get(4).get("cs").toString()).subtract(new BigDecimal(allList.get(12).get("cs")==null?"0":allList.get(12).get("cs").toString()));  //长沙
		BigDecimal fzSum_4=new BigDecimal(allList.get(4).get("fz")==null?"0":allList.get(4).get("fz").toString()).subtract(new BigDecimal(allList.get(12).get("fz")==null?"0":allList.get(12).get("fz").toString()));  //福州
		BigDecimal ncSum_4=new BigDecimal(allList.get(4).get("nc")==null?"0":allList.get(4).get("nc").toString()).subtract(new BigDecimal(allList.get(12).get("nc")==null?"0":allList.get(12).get("nc").toString()));  //南昌
		BigDecimal fsSum_4=new BigDecimal(allList.get(4).get("fs")==null?"0":allList.get(4).get("fs").toString()).subtract(new BigDecimal(allList.get(12).get("fs")==null?"0":allList.get(12).get("fs").toString()));  //长沙
		BigDecimal xmSum_4=new BigDecimal(allList.get(4).get("xm")==null?"0":allList.get(4).get("xm").toString()).subtract(new BigDecimal(allList.get(12).get("xm")==null?"0":allList.get(12).get("xm").toString()));  //长沙
		BigDecimal dgSum_4=new BigDecimal(allList.get(4).get("dg")==null?"0":allList.get(4).get("dg").toString()).subtract(new BigDecimal(allList.get(12).get("dg")==null?"0":allList.get(12).get("dg").toString()));  //长沙
		BigDecimal qyyySum_4=new BigDecimal(allList.get(4).get("qyyy")==null?"0":allList.get(4).get("qyyy").toString()).subtract(new BigDecimal(allList.get(12).get("qyyy")==null?"0":allList.get(12).get("qyyy").toString()));  //长沙
		BigDecimal zhSum_4=new BigDecimal(allList.get(4).get("zh")==null?"0":allList.get(4).get("zh").toString()).subtract(new BigDecimal(allList.get(12).get("zh")==null?"0":allList.get(12).get("zh").toString())); //长沙
		BigDecimal hkSum_4=new BigDecimal(allList.get(4).get("hk")==null?"0":allList.get(4).get("hk").toString()).subtract(new BigDecimal(allList.get(12).get("hk")==null?"0":allList.get(12).get("hk").toString()));  //长沙
		
		//累计统计实际剩余
		szSum_4=szSum_3.add(szSum_4);
		gzSum_4=gzSum_3.add(gzSum_4);
		csSum_4=csSum_3.add(csSum_4);
		fzSum_4=fzSum_3.add(fzSum_4);
		ncSum_4=ncSum_3.add(ncSum_4);
		fsSum_4=fsSum_3.add(fsSum_4);
		xmSum_4=xmSum_3.add(xmSum_4);
		dgSum_4=dgSum_3.add(dgSum_4);
		qyyySum_4=qyyySum_3.add(qyyySum_4);
		zhSum_4=zhSum_3.add(zhSum_4);
		hkSum_4=hkSum_3.add(hkSum_4);
		
		
		tempMap_4.put("sz", szSum_4);
		tempMap_4.put("gz", gzSum_4);
		tempMap_4.put("cs", csSum_4);
		tempMap_4.put("fz", fzSum_4);
		tempMap_4.put("nc", ncSum_4);
		tempMap_4.put("fs", fsSum_4);
		tempMap_4.put("xm", xmSum_4);
		tempMap_4.put("dg", dgSum_4);
		tempMap_4.put("qyyy", qyyySum_4);
		tempMap_4.put("zh", zhSum_4);
		tempMap_4.put("hk", hkSum_4);
		tempMap_4.put("city", "预算剩余");
		tempMap_4.put("apply_quarter", year+"4");
		allList.add(tempMap_4);
		
		
		
		
	}
	
	private Map<String,Object> calTotalBudGet(List<Map<String,Object>>  yearBudgetFeeList,int yearBudgetFeeListLength,String city){
		Map<String,Object> tempMap=new HashMap<String ,Object>();
		BigDecimal szSum=new BigDecimal(0);  //深圳
		BigDecimal gzSum=new BigDecimal(0);  //广州
		BigDecimal csSum=new BigDecimal(0);  //长沙
		BigDecimal fzSum=new BigDecimal(0);  //福州
		BigDecimal ncSum=new BigDecimal(0);  //南昌
		BigDecimal fsSum=new BigDecimal(0);  //长沙
		BigDecimal xmSum=new BigDecimal(0);  //长沙
		BigDecimal dgSum=new BigDecimal(0);  //长沙
		BigDecimal qyyySum=new BigDecimal(0);  //长沙
		BigDecimal zhSum=new BigDecimal(0);  //长沙
		BigDecimal hkSum=new BigDecimal(0);  //长沙
		
		for(int i=0;i<yearBudgetFeeListLength;i++){
			szSum=szSum.add(new BigDecimal(yearBudgetFeeList.get(i).get("sz").toString()));
			gzSum=gzSum.add(new BigDecimal(yearBudgetFeeList.get(i).get("gz").toString()));
			csSum=csSum.add(new BigDecimal(yearBudgetFeeList.get(i).get("cs").toString()));
			fzSum=fzSum.add(new BigDecimal(yearBudgetFeeList.get(i).get("fz").toString()));
			ncSum=ncSum.add(new BigDecimal(yearBudgetFeeList.get(i).get("nc").toString()));
			fsSum=fsSum.add(new BigDecimal(yearBudgetFeeList.get(i).get("fs").toString()));
			xmSum=xmSum.add(new BigDecimal(yearBudgetFeeList.get(i).get("xm").toString()));
			dgSum=dgSum.add(new BigDecimal(yearBudgetFeeList.get(i).get("dg").toString()));
			qyyySum=qyyySum.add(new BigDecimal(yearBudgetFeeList.get(i).get("qyyy").toString()));
			zhSum=zhSum.add(new BigDecimal(yearBudgetFeeList.get(i).get("zh").toString()));
			hkSum=hkSum.add(new BigDecimal(yearBudgetFeeList.get(i).get("hk").toString()));
			
		}
		tempMap.put("sz", szSum);
		tempMap.put("gz", gzSum);
		tempMap.put("cs", csSum);
		tempMap.put("fz", fzSum);
		tempMap.put("nc", ncSum);
		tempMap.put("fs", fsSum);
		tempMap.put("xm", xmSum);
		tempMap.put("dg", dgSum);
		tempMap.put("qyyy", qyyySum);
		tempMap.put("zh", zhSum);
		tempMap.put("hk", hkSum);
		tempMap.put("city", city);
		return tempMap;
	}

	
}
