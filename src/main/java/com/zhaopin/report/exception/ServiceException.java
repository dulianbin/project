package com.zhaopin.report.exception;

/**
 * 
 * 
 * 业务处理层异常处理类
 * @date Sep 15, 2015
 * @time 3:50:04 PM
 * @author Wusongti
 */
public class ServiceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

  
	private String code;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String code, String message, Throwable cause) {
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
