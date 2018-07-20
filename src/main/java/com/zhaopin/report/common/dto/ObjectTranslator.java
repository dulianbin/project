/*
 * Sunyard.com Inc.
 * Copyright (c) $year-2018 All Rights Reserved.
 */

package com.zhaopin.report.common.dto;


/**
 * DTO 通用接口
 * <p/>
 * User: sys53
 * Date: 14-12-10 上午10:42
 * version $Id: ObjectTranslator.java, v 0.1 Exp $
 */
public interface ObjectTranslator<S,T> {

    /**
     * 源对象 转化为 目标对象
     * @param t 目标对象
     * @param s 源对象
     * @return
     */
     T transfer (T t, S s);
}
