package com.zhaopin.report.service;

import java.util.List;

import com.zhaopin.report.common.service.BaseService;
import com.zhaopin.report.common.vo.PageData;
import com.zhaopin.report.mapper.model.City;
import com.zhaopin.report.mapper.model.User;

public interface AdminService  extends BaseService<User>{

	User login(PageData pd);
	
	List<City> queryCityList(PageData pd);
}
