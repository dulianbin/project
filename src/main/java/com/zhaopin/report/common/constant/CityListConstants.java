package com.zhaopin.report.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 城市列表常量类
 * @author lianbin.du
 *
 */
public class CityListConstants {

	public static final Map<Integer,String> cityList=new HashMap<Integer,String>();
	
	static{
		cityList.put(1, "深圳");
		cityList.put(2, "广州");
		cityList.put(3, "长沙");
		cityList.put(4, "福州");
		cityList.put(5, "南昌");
		cityList.put(6, "佛山");
		cityList.put(7, "厦门");
		cityList.put(8, "东莞");
		cityList.put(9, "区域运营");
		cityList.put(10, "珠海");
		cityList.put(11, "海口");
	}
	
	public static Map<Integer,String> getCityList(){
		return cityList;
	}
	
	public static String getCityByCityId(Integer cityId){
		return cityList.get(cityId);
	}
	
	
}
