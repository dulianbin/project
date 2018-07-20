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

    @Override
    public List<T> queryListPage(PageData pd) {
        return getBaseMapper().queryListPage(pd);
    }

    @Override
    public List<T> queryList(PageData pd) {
        return getBaseMapper().queryList(pd);
    }

    @Override
    public T queryOne(PageData pd) {
        return getBaseMapper().queryOne(pd);
    }

    @Override
    public T selectByPrimaryKey(Integer id) {
        return getBaseMapper().selectByPrimaryKey(id);
    }

    @Override
    public int delete(PageData pd) {
        return getBaseMapper().delete(pd);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return getBaseMapper().deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(T record) {
        return getBaseMapper().updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(T record) {
        return getBaseMapper().updateByPrimaryKeySelective(record);
    }

    @Override
    public int insertPageData(PageData pd) {
        return getBaseMapper().insertPageData(pd);
    }

    @Override
    public int insert(T record) {
        return getBaseMapper().insert(record);
    }
    @Override
    public int insertSelective(T record){
    	return getBaseMapper().insertSelective(record);
    }


 
}
