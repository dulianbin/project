package com.zhaopin.report.init;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zhaopin.report.common.constant.WebConstants;


public class ContextLisenter implements ServletContextListener {
	public void contextInitialized(ServletContextEvent event) {
		WebApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(event.getServletContext());
		ContextUtil.setCrasContext(ctx);

		// 初始化application
		this.initApplication(event);
	}

	public void contextDestroyed(ServletContextEvent event) {
	}

	/**
	 * 初始化application环境.<br/>
	 * 
	 * @param event
	 *            servlet上下文事件对象
	 */
	private void initApplication(ServletContextEvent event) {
		if (null != event) {
			ServletContext sc = event.getServletContext();

			/* 初始化application级别的变量。 */
			sc.setAttribute("mainServer", WebConstants.MAIN_SERVER);
			sc.setAttribute("cssFileServer", WebConstants.CSS_FILE_SERVER);
			sc.setAttribute("jsFileServer", WebConstants.JS_FILE_SERVER);
			sc.setAttribute("imgFileServer", WebConstants.IMG_FILE_SERVER);
			// 后台服务请求路径
			sc.setAttribute("backServer", WebConstants.BACK_SERVER);
			sc.setAttribute("mallBackServer", WebConstants.MALL_BACK_SERVER);

			// 前端页面资源路径
			sc.setAttribute("resource_path", WebConstants.RESOURCE_PATH);
			//微信前端页面资源路径
			sc.setAttribute("weixin_resource_path", WebConstants.WEIXIN_RESOURCE_PATH);
			// 后台 资源文件路径
			sc.setAttribute("uploadFilePath", WebConstants.UPLOAD_FILE_PATH);
			// 存储的图片相对于与uploadFilePath路径
			sc.setAttribute("storeImgPath", WebConstants.STORE_IMG_PATH);
			// 存储的静态hmltPath路径
			sc.setAttribute("htmlAddressPath", WebConstants.HTML_ADDRESS_PATH);
			// 广告图片文件访问虚拟路径
			sc.setAttribute("adverImageFilePath", WebConstants.ADVER_IMAGE_FILE_PATH);
			// 广告图片文件访问虚拟路径
			sc.setAttribute("taskQrImgPath", WebConstants.WEIXIN_QRIMG_PATH);
			String webRootPath = event.getServletContext().getRealPath("/");
			String classesPath = event.getServletContext().getRealPath(
					"WEB-INF/classes");
			
			//原图片路劲(临时目录保存)
			String oriFilePath = WebConstants.UPLOAD_FILE_PATH + File.separator + WebConstants.UPLOAD_IMG_PATH_SAVE;
			//压缩后图片路径
			String newFilePath = WebConstants.UPLOAD_FILE_PATH +File.separator+ WebConstants.STORE_IMG_PATH + File.separator;

			File oriFile = new File(oriFilePath);
			File newFile = new File(newFilePath);
			
			if(!oriFile.exists()){
				oriFile.mkdirs();
			}
			if(!newFile.exists()){
				newFile.mkdirs();
			}
			AppServerUtil.setClassesPath(classesPath);
			AppServerUtil.setWebRootPath(webRootPath);
		}
	}

}
