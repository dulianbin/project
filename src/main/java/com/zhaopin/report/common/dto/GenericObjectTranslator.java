package com.zhaopin.report.common.dto;

import com.zhaopin.report.util.convertor.BeanConverter;

/**
 * 通用象转化
 * <p/>
 * User: sys53
 * Date: 14-12-10 下午12:01
 * version $Id: GenericObjectTranslator.java, v 0.1 Exp $
 */
public class GenericObjectTranslator<S, T> implements ObjectTranslator<S, T> {
    public T transfer(T t, S s) {
        return BeanConverter.convert (t, s);
    }
}
