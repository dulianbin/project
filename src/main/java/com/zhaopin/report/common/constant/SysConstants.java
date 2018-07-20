package com.zhaopin.report.common.constant;

/**
 * 系统常量类
 * @author Administrator
 *
 */
public class SysConstants {
	
	/**
     * 默认的时间格式话字符串
     */
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * 文章状态；-1：已删除
     */
    public static final int ARTICLE_DELETED = -1;
    
    /**
     * 文章状态；1：启用
     */
    public static final int ARTICLE_ENABLE = 1;
    
    /**
     * 文章状态；2：禁用
     */
    public static final int ARTICLE_DISABLE = 2;
    
    /**
     * 通用状态标示：1：启用、有效
     */
    public static final int STATUS_ENABLE = 1;
    
    /**
     * 通用状态标示：2：禁用
     */
    public static final int STATUS_DISABLE = 2;
    
    /**
     * 通用状态标示：-1：禁用
     */
    public static final int STATUS_DELETED = -1;
    
    /**
     * 订单状态，1：未支付
     */
    public static final int PAY_STATUS_NO = 1;
    
    /**
     * 订单状态，2：已支付
     */
    public static final int PAY_STATUS_DONE = 2;
    
    /**
     * 金币订单状态，0：未支付
     */
    public static final int GOLD_PAY_STATUS_NO = 0;
    
    /**
     * 金币订单状态，1：支付成功
     */
    public static final int GOLD_PAY_STATUS_DONE = 1;
    
    /**
     * 粉丝关注状态，0：未知状态
     */
    public static final int SUBSCRIBE_NOTSURE = 0;
    
    /**
     * 粉丝关注状态，1：已关注
     */
    public static final int SUBSCRIBE_YES = 1;
    
    /**
     * 粉丝关注状态，2：取消关注
     */
    public static final int SUBSCRIBE_NO = 2;
    
    /**
     * 阅读任务状态，1：上线
     */
    public static final int READ_TASK_ONLINE = 1;
    
    /**
     * 阅读任务状态， 2：下线
     */
    public static final int READ_TASK_OFFLINE = 2;
   
    
    /**
     * 不合法的OpenID</br>
     * 详细的返回码，参见：
     * https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1419318634&token=&lang=zh_CN
     */
    public static final String INVALID_OPENID = "40003";
    
    /**
     * 微信公众平台接口异常：1000
     */
    public static final String WXOPEN_INTERFACE_ERR = "1000";
    
    
}
