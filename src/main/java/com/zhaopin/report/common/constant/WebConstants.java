package com.zhaopin.report.common.constant;

import java.io.File;
import java.util.ResourceBundle;

import com.zhaopin.report.init.AppServerUtil;


public class WebConstants {
	
	public WebConstants() {
		super();
	}
	
	public static final String WX_APPID = "wxd9ccdfc0f3b1fb8f";
	public static final String WX_SECRET = "77d1792b26153c19f926c609175fac42";
	
	/**
	 * 微信appid
	 */
	//public static final String WX_APPID = "wx4a284f17f8a0c041";
	//public static final String WX_APPID = "wx4a284f17f8a0c041";
	public static final String WX_MCH_ID="1392597002";
	/**
	 * 微信 secret
	 */
	//public static final String WX_SECRET = "40bac320c0839552303eb2d41f7bbe3c";
	//public static final String WX_SECRET = "40bac320c0839552303eb2d41f7bbe3c";
	
	/**
	 * 微信网页appid
	 */
	public static final String WX_WEB_APPID = "wxabe8c0397e0a8243";

	/**
	 * 微信网页secret
	 */
	public static final String WX_WEB_SECRET = "b136eb98962886b81df806f549432cbf";
	
	
	/**
	 * token 公众平台appid
	 */
	public static final String WXOPEN_APPID = "wxe4c5e03ae55742de";

	/**
	 * 公众平台的AppSecret
	 */
	public static final String WXOPEN_APPSECRET = "c023da7bce18376fdcfbe3416f658365";
	
	/**
	 * encodingAesKey 公众平台上，公众号消息加解密Key
	 */
	public static final String WXOPEN_encodingAesKey = "xDi6PSUPscwxBJ9UH4zYppP6S2QOhw9LR23awwlMe1O";
	
	/**
	 * Token 公众平台上，公众号消息校验Token
	 */
	public static final String WXOPEN_TOKEN = "Qd7BF0I9K9h3OT4aETpAk0sfaqnuM3lR";
	
	/**
	 * 微信网页授权获取code地址
	 */
	public static final String WX_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";

	public static final String WX_PAY_NOTIFY = "http://www.yunjianjr.com/tsinghuahelp/payment/notify";
	
	public static final String WX_EXTENSION_PAY_NOTIFY = "http://www.yunjianjr.com/tsinghuahelp/extensionpPayment/notify";

	public static final String WX_CERTFILEPATH = "/home/kingleadsw/sutuizs/";
	
	/**
	 * 微信公众平台，获取第三方平台component_access_token接口地址</br>
	 * 详见：2、获取第三方平台component_access_token
	 */
	public static final String WXOPEN_COMPONENT_ACCESSTOKEN = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";
	
	/**
	 * 微信公众平台，第三方平台方获取预授权码（pre_auth_code）</br>
	 * 该网址中第三方平台方需要提供第三方平台方appid、预授权码和回调URI</br>
	 * 详见：授权流程技术说明，步骤1
	 * https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1453779503&token=&lang=zh_CN
	 */
	public static final String WXOPEN_PRE_AUTH_CODE = "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token=%1s";
	
	/**
	 * 该API用于使用授权码换取授权公众号的授权信息，并换取authorizer_access_token和authorizer_refresh_token。
	 */
	public static final String WXOPEN_API_QUERY_AUTH = "https://api.weixin.qq.com/cgi-bin/component/api_query_auth?component_access_token=%1s";
	
	/**
	 * 微信公众平台，引导公众号运营者进入授权页的网址</br>
	 * 需要格式话的参数：component_appid，pre_auth_code，redirect_uri
	 * 该网址中第三方平台方需要提供第三方平台方appid、预授权码和回调URI</br>
	 * 详见：授权流程技术说明，步骤2
	 * https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1453779503&token=&lang=zh_CN
	 */
	public static final String WXOPEN_COMPONENT_LOGINPAGE = "https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid=%1s&pre_auth_code=%2s&redirect_uri=%3s";
	
	/**
	 * 获取（刷新）授权公众号的接口调用凭据（令牌）地址
	 */
	public static final String WXOPEN_API_AUTHORIZER_TOKEN = "https:// api.weixin.qq.com /cgi-bin/component/api_authorizer_token?component_access_token=%1s";
	
	/**
	 * 获取授权方的公众号帐号基本信息
	 */
	public static final String WXOPEN_API_GET_AUTHORIZER_INFO = "https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_info?component_access_token=%1s";

    /**
     * 模板后缀
     */
    public static final String TPL_SUFFIX = ".html";

    /**
     * 资源路径
     */
    public static final String RES_PATH = "/ftl";

    /**
     * 模板路径
     */
    public static final String TPL_BASE = "/WEB-INF/ftl";
    
    public static final String SESSION_SECURITY_CODE = "sessionSecCode";
	public static final String SESSION_ADMIN_USER = "sessionAdmin";
	public static final String SESSION_WEB_USER = "sessionWebUser";
	public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
	
	/**
     * WEB登录地址.
     */
    public static String WEB_LOGIN_URL = "/web/login.htm";

    /**
     * 资源配置信息.
     */
    public static ResourceBundle config = ResourceBundle
            .getBundle("web-constants");

    /**
     * 项目启动检查是否liunx系统 flase 不是liunx 系统 true 是liunx系统.
     */
    public static final boolean SYSTEM_ISLIUNX = false;

    /**
     * 生成数据库导入SQL文件地址.
     */
    public static final String IMPORT_SQL_PATH = "";

    /**
     * 主server名，即首页.
     */
    public static final String MAIN_SERVER = config
            .getString("com.beautyfitness.main.server");
    
    /**
     * 静态html地址 
     */
    public static final String HTML_ADDRESS_PATH = config
            .getString("com.beautyfitness.static.html.address");
    
    /**
     * 后台请求地址
     */
    public static final String BACK_SERVER = config
            .getString("com.beautyfitness.back.server");
    
    public static final String MALL_BACK_SERVER = config
            .getString("com.beautyfitness.mall.back.server");
    
    /**
     * 前端页面资源路径
     */
    public static final String WEIXIN_RESOURCE_PATH = config
            .getString("com.beautyfitness.weixin_resource_path");
    
    /**
     * 微信前端页面资源路径
     */
    public static final String RESOURCE_PATH = config
            .getString("com.beautyfitness.resource_path");

    /**
     * CSS文件server名.
     */
    public static final String CSS_FILE_SERVER = config
            .getString("com.beautyfitness.css.file.server");

    /**
     * JS文件server名.
     */
    public static final String JS_FILE_SERVER = config
            .getString("com.beautyfitness.js.file.server");

    /**
     * 超过图片大小值压缩
     */
    public static final String IMAGE_THAN_SIZE_TO_COMPRESS = config.getString("image_than_size_to_compress");
    /**
     * 图片文件server名.
     */
    public static final String IMG_FILE_SERVER = config
            .getString("com.beautyfitness.img.file.server");
    
    /**
     * 静态图片资源访问虚拟路径
     */
    public static final String STATIC_FILE_SERVER = config
            .getString("com.beautyfitness.static.file.server");  
    
    /**
     * 广告图片上传路径.
     */
    public static final String UPLOAD_FILE_PATH = config.getString("upload_file_path");
    
    /**
     * 广告图片文件访问虚拟路径
     */
    public static final String ADVER_IMAGE_FILE_PATH = config.getString("adver_file_path");

    /**
     * 服务器图片存放相对于与UPLOAD_FILE_PATH位置.
     */
    public static final String STORE_IMG_PATH = config
            .getString("upload_image_path");
    
    /**
     * 上传图片在服务器存放路径
     */
    public static final String UPLOAD_IMG_PATH_SAVE = AppServerUtil.getWebRoot()
            + "images" + File.separator + "upload" + File.separator;
    
    /**
     * 上传图片在服务器存放路径
     */
    public static final String UPLOAD_IMG_PATH_TEMP = AppServerUtil.getWebRoot()
            + "images" + File.separator + "temp" + File.separator;
    
    /**
     * 上传图片预览路径
     */
    public static final String UPLOAD_IMG_PATH_VIEW = "images/temp/";
    
    /**
     * 上传图片预览路径
     */
    public static final String UPLOAD_IMG_PATH_DB = "images/upload/";
    
    /**
     * 任务二维码图片存放路径(服务器上传根路径+任务类型+时间戳)
     */
    public static final String WEIXIN_QRIMG_PATH = UPLOAD_FILE_PATH + File.separatorChar + 
    		config.getString("weixin_qrimg_path");
    
    /**
     * 金币充值二维码图片路径
     */
    public static final String WEIXIN_QRIMG_GOLD = WEIXIN_QRIMG_PATH + File.separatorChar + "gold";
    

    /**
     * admin控制器访问路径
     */
    public static final String ADMIN_PATH = "/admin";
    
    /**
     * token时间
     */
    public static final String TOKEN_TIME=config.getString("token_time");
    
    /**
	 * 是否启用默认的用户
	 */
	public static final String ISMEMBER = config.getString("isMember");
	
	/**
	 * 免费推广账户，每个账户推广时只需要支付一分钱
	 */
	public static final String FREE_MEMBER = config.getString("free_member");


    /**
     * 7牛账号密码
     */
    public final static String QINIU_ACCESS_KEY = config.getString("qiniu_access_key");
    public final static String QINIU_SECRET_KEY = config.getString("qiniu_secret_key");
    public final static String QINIU_CLOUD_KEY = config.getString("qiniu_cloud_key");
    public final static String QINIU_DEFAULT_LINK = config.getString("qiniu_decault_link");

    /**签到领取的分数*/
    public final static int QIANDAO_SCORE=10;
    
	/** 微信客户端调用微信支付的指定包名 */
	public static final String WEIXIN_PREPAY_PACKAGE = "Sign=WXPay";
	
	/** 微信交易类型 APP支付 */
	public static final String WEIXIN_PREPAY_TRADE_TYPE_APP = "APP";

	/** 微信交易类型 网页支付 */
	public static final String WEIXIN_PREPAY_TRADE_TYPE_JSAPI = "JSAPI";
	
	/** 微信交易类型 微信扫码支付 */
	public static final String WEIXIN_PREPAY_TRADE_TYPE_NATIVE = "NATIVE";
	
	/*** 系统文件上传根路劲 */
    public final static String UPLOAT_ROOT_PATH = config.getString("upload_file_path");
	
}
