/*
 * Sunyard.com Inc.
 * Copyright (c) $year-2018 All Rights Reserved.
 */

package com.zhaopin.report.util.convertor;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 枚举类型转换器
 * <p/>
 * User: sys53
 * Date: 14-12-10 上午10:07
 * version $Id: EnumDefinitionConverter.java, v 0.1 Exp $
 */
public class DefinitionEnumConverter extends AbstractDataTypeConverter {

    /** log object */
    private static final Logger LOG             = LoggerFactory
                                                    .getLogger (DefinitionEnumConverter.class);

    /** 获取枚举方法名 */
    private static final String GET_TARGET_ENUM = "getTargetEnum";

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

        Object result = null;
        try {

            Method method = targetClass.getMethod(GET_TARGET_ENUM, String.class);

            Object[] enumConstants = targetClass.getEnumConstants();

            if (enumConstants.length > 0) {

                result = method.invoke(enumConstants[0], value);
            }

            return result;

        } catch (Exception e) {
            LOG.warn("枚举对象转换失败", e);
            throw new RuntimeException(e);
        }
    }
}
