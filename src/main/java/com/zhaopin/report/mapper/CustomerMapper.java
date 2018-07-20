package com.zhaopin.report.mapper;

import java.util.List;

import com.zhaopin.report.common.dao.BaseMapper;
import com.zhaopin.report.mapper.model.Customer;

public interface CustomerMapper extends BaseMapper<Customer>{

	List<Customer> selectByCustormerId(Integer customerId);
	
	Customer selectByMobile(String mobile);
	
	int deleteCustomerBycustomerId(Customer customer);
}