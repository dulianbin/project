package com.zhaopin.report.common.service;



import java.util.List;

import com.zhaopin.report.common.dao.BaseMapper;
import com.zhaopin.report.common.vo.PageData;

/**
 * Created by Administrator on 2016-08-08.
 */
public abstract class BaseServiceImpl<T> implements BaseService <T>{

    private BaseMapper<T> baseMapper;

    /**
     * 建议在子类中重写该方法
     * @return
     */
    protected  abstract BaseMapper<T> getBaseMapper();

    public List<T> queryListPage(PageData pd) {
        return getBaseMapper().queryListPage(pd);
    }

    public List<T> queryList(PageData pd) {
        return getBaseMapper().queryList(pd);
    }

    public T queryOne(PageData pd) {
        return getBaseMapper().queryOne(pd);
    }

    public T selectByPrimaryKey(Integer id) {
        return getBaseMapper().selectByPrimaryKey(id);
    }

    public int delete(PageData pd) {
        return getBaseMapper().delete(pd);
    }

    public int deleteByPrimaryKey(Integer id) {
        return getBaseMapper().deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKey(T record) {
        return getBaseMapper().updateByPrimaryKey(record);
    }

    public int updateByPrimaryKeySelective(T record) {
        return getBaseMapper().updateByPrimaryKeySelective(record);
    }

    public int insertPageData(PageData pd) {
        return getBaseMapper().insertPageData(pd);
    }

    public int insert(T record) {
        return getBaseMapper().insert(record);
    }
    
    public int insertSelective(T record){
    	return getBaseMapper().insertSelective(record);
    }


 
}
