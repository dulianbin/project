package com.zhaopin.report.exception;

/**
 * 
 * 
 * 数据访问层异常处理类
 * @date Sep 15, 2015
 * @time 3:49:34 PM
 * @author Wusongti
 */
public class DaoException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;

    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String code, String message) {
        super(message);
        this.code = code;
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
