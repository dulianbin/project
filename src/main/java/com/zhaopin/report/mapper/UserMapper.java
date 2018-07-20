package com.zhaopin.report.mapper;

import java.util.List;

import com.zhaopin.report.common.dao.BaseMapper;
import com.zhaopin.report.common.vo.PageData;
import com.zhaopin.report.mapper.model.City;
import com.zhaopin.report.mapper.model.User;

public interface UserMapper extends BaseMapper<User>{
	User queryOneByUsername(PageData pd);
	
	List<City> queryCityList(PageData pd);
	
}