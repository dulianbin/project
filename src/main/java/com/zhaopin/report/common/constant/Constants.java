package  com.zhaopin.report.common.constant;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.zhaopin.report.mapper.model.Customer;
import com.zhaopin.report.util.DateUtil;



public final class Constants {
	
	protected static final Logger logger = Logger.getLogger(Constants.class);
	
	public static final String SESSION_USER = "session_user";

	private static final String SESSION_VALIDATECODE = "session_validatecode";//验证码
	
	private static final String SESSION_SMSCODE = "session_smscode";//验证码
	
	public static final String SESSION_CUSTOMER = "session_customer";
	
	private static final String SESSION_ACCESS_URLS = "session_access_urls"; //系统能够访问的URL
	
	
	private static final String SESSION_MENUBTN_MAP = "session_menubtn_map"; //系统菜单按钮

	
	/**
	  * 设置session的值
	  * @param request
	  * @param key
	  * @param value
	  */
	 public static void setAttr(HttpServletRequest request,String key,Object value){
		 request.getSession(true).setAttribute(key, value);
	 }
	 
	 
	 /**
	  * 获取session的值
	  * @param request
	  * @param key
	  * @param value
	  */
	 public static Object getAttr(HttpServletRequest request,String key){
		 return request.getSession(true).getAttribute(key);
	 }
	 
	 /**
	  * 删除Session值
	  * @param request
	  * @param key
	  */
	 public static void removeAttr(HttpServletRequest request,String key){
		 request.getSession(true).removeAttribute(key);
	 }
 
	 
	 /**
	  * 从session中获取用户信息
	  * @param request
	  * @return SysUser
	  */
	 public static void removeUser(HttpServletRequest request){
		removeAttr(request, SESSION_USER);
	 }
	 
	 
	 /**
	  * 设置验证码 到session
	  * @param request
	  * @param user
	  */
	 public static void setValidateCode(HttpServletRequest request,String validateCode){
		 request.getSession(true).setAttribute(SESSION_VALIDATECODE, validateCode);
	 }
	 
	 
	 /**
	  * 从session中获取验证码
	  * @param request
	  * @return SysUser
	  */
	 public static String getValidateCode(HttpServletRequest request){
		return (String)request.getSession(true).getAttribute(SESSION_VALIDATECODE);
	 }
	 
	 
	 /**
	  * 从session中获删除验证码
	  * @param request
	  * @return SysUser
	  */
	 public static void removeValidateCode(HttpServletRequest request){
		removeAttr(request, SESSION_VALIDATECODE);
	 }
	 
	 
	 
	 
	 /**
	  * 判断当前登录用户是否超级管理员
	  * @param request
	  * @return
	  */
	 public static void setAccessUrl(HttpServletRequest request,List<String> accessUrls){ //判断登录用户是否超级管理员
		 setAttr(request, SESSION_ACCESS_URLS, accessUrls);
	 }
	 
	 
	 
	 /**
	  * 判断URL是否可访问
	  * @param request
	  * @return
	  */
	 public static boolean isAccessUrl(HttpServletRequest request,String url){ 
		 List<String> accessUrls = (List)getAttr(request, SESSION_ACCESS_URLS);
		 if(accessUrls == null ||accessUrls.isEmpty() || !accessUrls.contains(url)){
			 return false;
		 }
		 return true;
	 }
	 
	 
	 /**
	  * 设置菜单按钮
	  * @param request
	  * @param btnMap
	  */
	 public static void setMemuBtnMap(HttpServletRequest request,Map<String,List> btnMap){ //判断登录用户是否超级管理员
		 setAttr(request, SESSION_MENUBTN_MAP, btnMap);
	 }
	 
	 /**
	  * 获取菜单按钮
	  * @param request
	  * @param btnMap
	  */
	 public static List<String> getMemuBtnListVal(HttpServletRequest request,String menuUri){ //判断登录用户是否超级管理员
		 Map btnMap  = (Map)getAttr(request, SESSION_MENUBTN_MAP);
		 if(btnMap == null || btnMap.isEmpty()){
			 return null;
		 }
		 return (List<String>)btnMap.get(menuUri);
	 }
	 
	 
	 /**
	  * 设置验证码 到session
	  * @param request
	  * @param user
	  */
	 public static void setSmsCode(HttpServletRequest request,String smsCode){
		HttpSession session= request.getSession(true);
		 session.setMaxInactiveInterval(90);
		 session.setAttribute(SESSION_SMSCODE, smsCode);
	 }
	 
	 
	 /**
	  * 从session中获取验证码
	  * @param request
	  * @return SysUser
	  */
	 public static String getSmsCode(HttpServletRequest request){
		return (String)request.getSession(true).getAttribute(SESSION_SMSCODE);
	 }
	 
	 
	 /**
	  * 设置用户信息 到session
	  * @param request
	  * @param Customer
	  */
	 public static void setCustomer(HttpServletRequest request,Customer customer){
		 request.getSession(true).setAttribute(SESSION_CUSTOMER, customer);
	 }
	 
	 /**
	  * 设置web用户信息 到session
	  * @param request
	  * @param Customer
	  */
	 public static void setWebCustomer(HttpServletRequest request,Customer customer){
		 request.getSession(true).setAttribute(WebConstants.SESSION_WEB_USER, customer);
	 }
	 
	 /**
	  * 从session中获取web用户信息
	  * @param request
	  * @return Customer
	  */
	 public static Customer getWebCustomer(HttpServletRequest request){
		 if("true".equals(WebConstants.ISMEMBER)){
			 return (Customer)request.getSession(true).getAttribute(WebConstants.SESSION_WEB_USER);
	     }else{
	    	 Customer customer = (Customer)request.getSession(true).getAttribute(WebConstants.SESSION_WEB_USER);
	    	 if (customer != null) {
	    		 return customer;
	    	 }
	    	 customer = new Customer();
	    	 Date vipDate = DateUtil.getDateByAddDays(new Date(), 10);
	         customer.setCustomerId(95);
	         customer.setNickname("南城旧梦，初年已逝！");
	         customer.setSubscribe(1);
	         customer.setHeadUrl("http://wx.qlogo.cn/mmopen/09fq7TG5iaMAMn3vqwD9DPzFSEFYGR3Jk1jlC4EobQ6QgyaFDtMgH77eZXsCjTRIycN7J46Dia6e9m5vPIexK0UM5MRdGyLkKO/0");
	         customer.setOpenid("oF4kgxFlHKyxeoiF4wguVdyx2bGs");
	         customer.setOpenid("ovUKrxPQnGiXKScqrT60Hhzy2sno");
	         customer.setMobile("13076946007");
	         return customer;
	     }
	 }
	 
	 /**
	  * 从session中获取用户信息
	  * @param request
	  * @return Customer
	  */
	 public static Customer getCustomer(HttpServletRequest request){
	     
	     if("true".equals(WebConstants.ISMEMBER)){
	         return (Customer)request.getSession(true).getAttribute(SESSION_CUSTOMER);
	     }else{
	    	 Customer customer = (Customer)request.getSession(true).getAttribute(SESSION_CUSTOMER);
	    	 if (customer != null) {
	    		 return customer;
	    	 }
	    	 customer = new Customer();
	         customer.setCustomerId(95);
	         customer.setNickname("南城旧梦，初年已逝！");
	         customer.setSubscribe(1);
	         customer.setHeadUrl("http://wx.qlogo.cn/mmopen/JTWa8vpxlSTT9uZRSbiavLqeic5Wqp921woJ36s7icvic0IIXahsTV33gU7Tmzg0vT7DVrGmJQ6gQ8ictNwiaVjvwBhPlsFoUYdKvf/0");
	         customer.setOpenid("oeddluMXSI2pwaE73U5Hun5bx1II");
	         customer.setMobile("13076946007");
	         return customer;
	     }
	 }
}
