package com.zhaopin.report.common;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.zhaopin.report.common.constant.WebConstants;
import com.zhaopin.report.util.CallBackConstant;
import com.zhaopin.report.util.FileDownload;

/**
 * 
 * class Name [CommonController]. description [公共控制器]
 * 
 * @author ltp
 * @version 1.0
 * @since JDK 1.7
 */
@Controller
@RequestMapping("/common")
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class CommonController extends AbstractWebController {

	Logger logger = Logger.getLogger(CommonController.class);

	public CommonController() {
		super();
	}

	/**
	 * 
	 * 描述 获取硬盘图片.
	 * 
	 * @param request
	 * @param response
	 * @param imgPath
	 */
	@RequestMapping(value = { "/findImg" }, method = { RequestMethod.GET })
	public void findStoreImg(HttpServletRequest request,
			HttpServletResponse response, String imgPath) {
		try {
			// 下面的两个方法都可以获取图片到页面
			FileDownload.download(request, response, imgPath);
			// writeImage(imgPath, response);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	/**
	 * 
	 * 描述 通过名字获得图片.
	 * 
	 * @param request
	 * @param response
	 * @param imgPath
	 */
	@RequestMapping(value = { "/findDynamicImg" }, method = { RequestMethod.GET })
	public void findDynamicStoreImg(HttpServletRequest request,
			HttpServletResponse response) {
		String imgName = request.getParameter("imgName");
		try {
			String imgPath = WebConstants.UPLOAD_FILE_PATH + File.separator
					+ imgName;
			// 下面的两个方法都可以获取图片到页面
			FileDownload.download(request, response, imgPath);
			// writeImage(imgPath, response);
		} catch (Exception e) {
			logger.error("没有找到图片:" + imgName);
		}
	}

	/**
	 * 多文件上传，如果一个文件，会直接返回一个路径，多个会返回一个字符串数组（json)
	 * 
	 * @param files
	 * @param request
	 * @param response
	 */
	@RequestMapping("/fileUpload")
	public void fileUpload(@RequestParam("file") CommonsMultipartFile[] files,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			List<String> lists = new ArrayList<String>();

			for (int i = 0; i < files.length; i++) {
				if (!files[i].isEmpty()) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(new Date());
					String fileName = calendar.get(Calendar.YEAR)
							+ File.separator + calendar.get(Calendar.MONTH)
							+ File.separator
							+ calendar.get(Calendar.DAY_OF_MONTH)
							+ File.separator + System.currentTimeMillis()
							+ System.currentTimeMillis() + "_"
							+ files[i].getName();

					// 文件存放路径， 年月日
					File f = new File(WebConstants.UPLOAD_FILE_PATH
							+ File.separator + fileName);
					if (!f.exists()) {
						f.mkdirs();
					}
					files[i].transferTo(f);
					String rqUrl = "/common/findDynamicImg?imgName=" + fileName;
					lists.add(rqUrl);
				}
			}
			this.outObjectToJson(CallBackConstant.SUCCESS.callback(lists),
					response);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			this.outObjectToJson(CallBackConstant.FAILED.callback(), response);
		}
	}

	/**
	 * 根据img=图片路径获取图片流并响应给response
	 * @param request
	 * @param response
	 */
	@RequestMapping("/img")
	public void imgShow(HttpServletRequest request, HttpServletResponse response) {
		try {
			OutputStream os = response.getOutputStream();
			String picPath = request.getQueryString();
			picPath = picPath.substring(4, picPath.length());
			URLConnection u = new URL(picPath).openConnection();
			InputStream in = u.getInputStream();
			if (null != in) {
				int len;
				byte[] b = new byte[1024];
				while ((len = in.read(b)) != -1) { // 循环读取
					os.write(b, 0, len); // 写入到输出流
				}
				os.flush();
			}
			in.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 公共信息提示弹出框.
	 * 
	 * @param operateWay
	 *            操作方式
	 * @param dataObject
	 *            数据对象
	 * @param dataCount
	 *            数据数量
	 * @param model
	 *            Model
	 * @return String
	 */
	@RequestMapping(value = { "/messageDialog" })
	public String messageDialog(Model model, String operateWay,
			String dataObject, int dataCount) {
		try {
			String way = java.net.URLDecoder.decode(operateWay, "UTF-8");
			String obj = java.net.URLDecoder.decode(dataObject, "UTF-8");
			obj = obj.replaceAll("<", "&lt;").replaceAll("<", "&gt;");
			model.addAttribute("operateWay", way);
			model.addAttribute("dataObject", obj);
			model.addAttribute("dataCount", dataCount);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString(), e);
		}
		return "messagedialog_jsp";
	}
}
