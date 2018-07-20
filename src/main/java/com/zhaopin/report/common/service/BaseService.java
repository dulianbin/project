package com.zhaopin.report.common.service;



import java.util.List;

import com.zhaopin.report.common.vo.PageData;

public interface BaseService <T>{
    /**
     * 查询页面列表数据 包含分页
     * @param pd
     * @return
     */
    List<T> queryListPage(PageData pd);
    /**
     * 查询列表
     * @param pd
     * @return
     */
    List<T> queryList(PageData pd);

    /**
     * 查询单个记录
     * @param pd
     * @return
     */
    T queryOne(PageData pd);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    T selectByPrimaryKey(Integer id);

    /**
     * 删除
     *
     * @param pd
     * @return
     */
    int delete(PageData pd);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 修改
     */
    int updateByPrimaryKey(T record);

    /**
     * 按条件修改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * 插入
     * @param pd
     * @return
     */
    int insertPageData(PageData pd);

    /**
     * 插入数据
     * @param record
     * @return
     */
    int insert(T t);
    
    int insertSelective(T record);

}