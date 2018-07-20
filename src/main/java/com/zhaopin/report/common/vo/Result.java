package com.zhaopin.report.common.vo;

/**
 * 前后台数据交互类
 * @author Administrator
 *
 */
public class Result {
	private boolean flag; //标志位
	private String msg; //描述
	private Object data; //数据
	private String code;
	
	public static final Integer SUCCESS = 1010;
	
	public Result(){
		flag = true;
	}
	
	public Result(Integer code){
		if(code == SUCCESS){
			flag = true;
			this.code = "1010";
			msg = "操作成功";
		}
	}
	
	public Result(String msg,boolean flag,String code,Object o){
		this.flag = flag;
		this.msg=msg;
		this.code=code;
		o=(o==null)?"":o;
		this.data=o;
	}
	public Result(String msg,boolean flag,String code){
		this.flag = flag;
		this.msg=msg;
		this.code=code;
	}

	public static Result SUCCESS(Object o){
		Result  result=new Result("操作成功",true,"1010",o);
		return result;
	}
	public static Result SUCCESS(){
		Result  result=new Result("操作成功",true,"1010");
		return result;
	}
	public static Result FAIL(Object o){
		Result  result=new Result("操作失败",false,"1011",o);
		return result;
	}
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
