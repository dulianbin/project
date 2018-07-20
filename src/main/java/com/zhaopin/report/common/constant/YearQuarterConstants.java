package com.zhaopin.report.common.constant;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 年度季度常量类
 */
public class YearQuarterConstants {


public static final Map<String,String> quarterList=new LinkedHashMap<String,String>();
	
	static{
		
		quarterList.put("FY19-Q1", "FY19-Q1");
		quarterList.put("FY19-Q2", "FY19-Q2");
		quarterList.put("FY19-Q3", "FY19-Q3");
		quarterList.put("FY19-Q4", "FY19-Q4");
		
		quarterList.put("FY20-Q1", "FY20-Q1");
		quarterList.put("FY20-Q2", "FY20-Q2");
		quarterList.put("FY20-Q3", "FY20-Q3");
		quarterList.put("FY20-Q4", "FY20-Q4");
		
		
		quarterList.put("FY21-Q1", "FY21-Q1");
		quarterList.put("FY21-Q2", "FY21-Q2");
		quarterList.put("FY21-Q3", "FY21-Q3");
		quarterList.put("FY21-Q4", "FY21-Q4");
		
		quarterList.put("FY22-Q1", "FY22-Q1");
		quarterList.put("FY22-Q2", "FY22-Q2");
		quarterList.put("FY22-Q3", "FY22-Q3");
		quarterList.put("FY22-Q4", "FY22-Q4");
		
		quarterList.put("FY23-Q1", "FY23-Q1");
		quarterList.put("FY23-Q2", "FY23-Q2");
		quarterList.put("FY23-Q3", "FY23-Q3");
		quarterList.put("FY23-Q4", "FY23-Q4");
	}
	
	public static Map<String,String> getQuarterList(){
		return quarterList;
	}
	
	public static String getQuarterById(String quarter){
		return quarterList.get(quarter);
	}
}
