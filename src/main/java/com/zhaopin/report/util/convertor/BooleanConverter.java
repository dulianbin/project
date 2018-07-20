/*
 * Sunyard.com Inc.
 * Copyright (c) $year-2018 All Rights Reserved.
 */

package com.zhaopin.report.util.convertor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * boolean类型转换器
 * <p/>
 * User: sys53
 * Date: 14-12-10 上午10:07
 * version $Id: BooleanConverter.java, v 0.1 Exp $
 */
public class BooleanConverter extends AbstractDataTypeConverter {

    /** log object */
    private static final Logger LOG = LoggerFactory.getLogger (BooleanConverter.class);

    /**
     * Convert the input object into an output object of the
     * specified type.
     * <p/>
     * Typical implementations will provide a minimum of
     * <code>String --> type</code> conversion.
     *
     * @param targetClass Data type to which this value should be converted.
     * @param value       The input value to be converted.
     * @return The converted value.
     */
    @Override
    protected Object convertToType(Class<?> targetClass, Object value) {

        if (value == null || value instanceof Boolean) {
            return value;
        }

        try {

            return Boolean.valueOf(value.toString());

        } catch (Exception e) {
            LOG.warn("布尔类型对象转换失败", e);
            throw new RuntimeException(e);
        }
    }
}
