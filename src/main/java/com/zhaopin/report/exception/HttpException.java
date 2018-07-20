package com.zhaopin.report.exception;

public class HttpException extends Exception
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

  
    private String code;

    public HttpException() {
        super();
    }

    public HttpException(String message) {
        super(message);
    }

    public HttpException(String code, String message) {
        super(message);
        this.code = code;
    }

    public HttpException(Throwable cause) {
        super(cause);
    }

    public HttpException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpException(String code, String message, Throwable cause) {
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
