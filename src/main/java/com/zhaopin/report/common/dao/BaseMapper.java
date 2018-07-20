package com.zhaopin.report.common.dao;

import java.util.List;

import com.zhaopin.report.common.vo.PageData;


public interface BaseMapper<T> {
	/**
	 * 查询列表
	 * 
	 * @param pd
	 * @return
	 */
	List<T> queryList(PageData pd);

	/**
	 * 查询单个记录
	 * 
	 * @param pd
	 * @return
	 */
	T queryOne(PageData pd);

	/**
	 * 删除
	 * 
	 * @param pd
	 * @return
	 */
	int delete(PageData pd);

	/**
	 * 更新
	 * 
	 * @param pd
	 * @return
	 */
	int update(PageData pd);

	/**
	 * 插入
	 * 
	 * @param t
	 * @return
	 */
	int insertPageData(PageData pd);

	int deleteByPrimaryKey(Integer id);

	int insert(T record);

	int insertSelective(T record);

	T selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(T record);

	int updateByPrimaryKeyWithBLOBs(T record);

	int updateByPrimaryKey(T record);
	
	List<T> queryListPage(PageData pd);
}
