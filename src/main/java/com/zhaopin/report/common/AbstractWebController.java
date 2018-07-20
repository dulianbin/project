package com.zhaopin.report.common;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.zhaopin.report.common.bean.EasyUIPageBean;
import com.zhaopin.report.common.vo.PageData;
import com.zhaopin.report.redis.RedisService;
import com.zhaopin.report.util.CookieUtil;
import com.zhaopin.report.util.JsonDateValueProcessor;
import com.zhaopin.report.util.JsonUtil;
import com.zhaopin.report.util.PageView;
import com.zhaopin.report.util.StringUtil;
import com.zhaopin.report.util.freemarker.FreeMarkerMgr;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonBeanProcessor;

public abstract class AbstractWebController extends MultiActionController implements
		WebController {
	
	@Autowired
	private RedisService redisService;
	// freemarker模板管理
	protected FreeMarkerMgr freeMarkerMgr = FreeMarkerMgr.getInstance();
	
	/**
	 * 
	 * init:(初始化方法).
	 * 
	 * @see com.kingleadsw.fes.commons.controller.ipcs.common.controller.WebController#init()
	 */
	public void init() {

	}

	
	/*public Customer getCustomer(){
		String trainerStr=redisService.getString(getCookieValue("weixin_customer_token"));
		if(StringUtil.isEmpty(trainerStr)){
			return null;
		}
		Customer trainer=JacksonUtil.deserializeJsonToObject(trainerStr,Customer.class);
		return trainer;
	}*/
	
	public String getCookieValue(String cookieName){
		Cookie[] cookies = getRequest().getCookies();
		String cookieValue = CookieUtil.getCookie(cookies, cookieName);
		return cookieValue;
	}
	
	/**
	     * 
	     */
	public void onFinishedProcessContext() {

	}

	/**
	 * 
	 * onRefreshContext:(此方法在创建或刷新WebApplicationContext时被调用).
	 * 
	 * @throws BeansException
	 * @see com.kingleadsw.fes.commons.controller.ipcs.common.controller.WebController#onRefreshContext()
	 */
	public void onRefreshContext() throws BeansException {
		initWebConfiguration();
	}

	/**
	 * 描述.
	 */
	private void initWebConfiguration() {
		
	}
	
	/**
	 * 得到PageData
	 */
	public PageData getPageData(){
		return new PageData(this.getRequest());
	}
	
	/**
	 * 得到ModelAndView
	 */
	public ModelAndView getModelAndView(){
		return new ModelAndView();
	}
	
	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}

	/**
	 * 
	 * initBinder:(初始化binder的回调函数).
	 * 
	 * @param request
	 * @param binder
	 * @throws Exception
	 * @see org.springframework.web.servlet.mvc.multiaction.MultiActionController
	 *      #initBinder(javax.servlet.http.HttpServletRequest,
	 *      org.springframework.web.bind.ServletRequestDataBinder)
	 */
	@Override
	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		super.initBinder(request, binder);
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(Short.class, new CustomNumberEditor(
				Short.class, true));
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(
				Integer.class, true));
		binder.registerCustomEditor(Long.class, new CustomNumberEditor(
				Long.class, true));
		binder.registerCustomEditor(Float.class, new CustomNumberEditor(
				Float.class, true));
		binder.registerCustomEditor(Double.class, new CustomNumberEditor(
				Double.class, true));
		binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(
				BigDecimal.class, true));
		binder.registerCustomEditor(BigInteger.class, new CustomNumberEditor(
				BigInteger.class, true));
		// java.util.Date需要根据具体使用的格式不同在子类做不同的bind。
	}

	/**
	 * 将对象转换成JSON字符串，并响应回前台.
	 * 
	 * @param object
	 *            需要返回的参数
	 * @param response
	 * 
	 */
	/*public void writeJson(Object object, HttpServletResponse response) {
		try {
			String json = JSON.toJSONStringWithDateFormat(object,
					StringUtil.DEFAULT_TIEMSTAMP_PATTERN);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * object 类型 转化为 json 输出.
	 * 
	 * @param obj
	 * @param response
	 * 
	 */
	protected void outObjectToJson(Object obj, HttpServletResponse response) {
		try {
			String str = JSONObject.fromObject(obj).toString();
			// outString(str,response);
			outJson(str, response);
		} catch (Exception e) {
			// log.error("outObjectToJson:" + e);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 将easyui需要的List数据分页并输出
	 * @param pd request封装后的参数对象Map
	 * @param dataList
	 * @param footer 表footer数据
	 * @param response
	 */
	public void outEasyUIDataToJson(PageData pd, List dataList, List footer, HttpServletResponse response) {
		try {
			PageView pageView = (PageView) pd.get("pageView");
			if(pageView == null){
				pageView = new PageView();
			}
			String dataJson = EasyUIPageBean.createEasyUIPageBean(pageView.getRowCount(), dataList, footer).toJsonString();
			logger.info("返回的数据:"+dataJson);
			outJson(dataJson, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 将easyui需要的List数据分页并输出
	 * @param PageData request封装后的参数对象Map
	 * @param dataList 分页数据
	 * @param response
	 * 
	 */
	public void outEasyUIDataToJson(PageData pd, List dataList, HttpServletResponse response) {
		try {
			PageView pageView = (PageView) pd.get("pageView");
			if(pageView == null){
				pageView = new PageView();
			}
			String dataJson = EasyUIPageBean.createEasyUIPageBean(pageView.getRowCount(), dataList).toJsonString();
			outJson(dataJson, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Object 转换json并输出
	 * 时间默认转换为： yyyy-MM-dd HH:mm:ss
	 * @param obj
	 * @param response
	 * 
	 */
	public void outToJson(Object obj, HttpServletResponse response) {
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonBeanProcessor(java.sql.Date.class, new DateJsonValueProcessor());
			jsonConfig.registerJsonValueProcessor(java.util.Date.class,new JsonDateValueProcessor());

			if (obj instanceof List) {
				JSONArray arr = JSONArray.fromObject(obj, jsonConfig);
				outJson(arr.toString(), response);
			} else {
				JSONObject abc = JSONObject.fromObject(obj, jsonConfig);
				outJson(abc.toString(), response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Object 转换json ,带日期格式的.
	 * 
	 * @param obj
	 * @param response
	 * @param format
	 *            yyyy-MM-dd HH:mm:ss or yyyy-MM-dd
	 * 
	 */
	public void outToJson(Object obj, HttpServletResponse response,
			final String format) {
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonBeanProcessor(java.sql.Date.class, new DateJsonValueProcessor());
			jsonConfig.registerJsonValueProcessor(java.util.Date.class,new JsonDateValueProcessor());
			if (obj instanceof List) {
				JSONArray arr = JSONArray.fromObject(obj, jsonConfig);
				outJson(arr.toString(), response);
			} else {
				JSONObject abc = JSONObject.fromObject(obj, jsonConfig);
				outJson(abc.toString(), response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 输出json.
	 * 
	 * @param str
	 * @param response
	 * 
	 */
	public void outString(String str, HttpServletResponse response) {
		try {
			if (response != null) {
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.write(str);
				// out.close();
			}
		} catch (IOException e) {
			// log.error("outString" + e);
		}
	}

	/**
	 * 输出json.
	 * @param str json字符串
	 * @param response
	 */
	protected void outJson(String str, HttpServletResponse response) {
		try {
			if (response != null) {
				response.setContentType("application/json;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.write(str);
				// out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
	/**
	 * 
	 * Description: 写出Image.
	 * 
	 * @param imPath
	 *            图片地址
	 * @param response
	 *            响应
	 * @throws Exception
	 *             e
	 * 
	 */
	public void writeImage(String imPath, HttpServletResponse response)
			throws Exception {
		if (!StringUtil.isEmpty(imPath)) {
			// 输出图片
			File f = null;
			String path = this.getClass().getResource("/").toString()
					.replace("WEB-INF/classes/", "").replace("file:/", "");
			if (!"".equals(path))
				// path += File.separator + "images" + File.separator +
				// "nologo.png";
				if ("/".equals(File.separator)) {
					path = File.separator + path;
				}
			if (!"".equals(imPath) && null != imPath) {
				f = new File(imPath);
				// f = new File(WebConstants.CREATE_CATEGORYFILE_PATH+imPath);
				if (!f.exists()) {
					f = new File(path);
				}
			} else {
				f = new File(path);
			}
			FileInputStream is = new FileInputStream(f);
			int i = is.available();
			byte data[] = new byte[i];
			is.read(data);
			is.close();
			// response.reset();
			response.setContentType("image/*");
			OutputStream out = response.getOutputStream();
			out.write(data);
			out.flush();
			out.close();
		}
	}
    /**
	 * form表单提交 Date类型数据绑定
	 * <功能详细描述>
	 * @param binder
	 * @see [类、类#方法、类#成员]
	 */
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    dateFormat.setLenient(false);  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
	}
	
	class DateJsonValueProcessor implements JsonBeanProcessor { 
		public JSONObject processBean(Object bean, JsonConfig arg1) { 
		JSONObject jsonObject = null; 
		        if( bean instanceof java.sql.Date ){ 
		             bean = new Date( ((java.sql.Date) bean).getTime() ); 
		        } 
		        if( bean instanceof java.sql.Timestamp ){ 
		            bean = new Date( ((java.sql.Timestamp) bean).getTime() ); 
		        } 
		        if( bean instanceof Date ){ 
		             jsonObject = new JSONObject(); 
		             jsonObject.element("time", ( (Date) bean ).getTime()); 
		        }else{ 
		             jsonObject = new JSONObject( true ); 
		        } 
		          return jsonObject; 
		       } 
		}
	
	/**
	 * 兼容浏览器处理easyui请求返回的json数据处理
	 * szx
	 * 2016年7月27日 下午3:19:56
	 * @param obj
	 * @param response
	 * void
	 */
	public void responseWrite(Object obj,HttpServletResponse response){
		try {
			if (response != null) {
				response.setContentType("text/htm;charset=UTF-8");
				PrintWriter out = response.getWriter();
				//out.write("<body><pre>"+JsonUtil.objToJson(obj)+"</pre></body>");
				out.write(JsonUtil.objToJson(obj));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 压缩图片
	 * szx
	 * 2016年7月27日 上午9:46:48
	 * @param imgsrc 源文件路径
	 * @param imgdist 压缩后文件路劲
	 * @param widthdist 像素宽
	 * @param heightdist 像素高
	 * @return
	 * String
	 */
	public String reduceImg(String imgsrc, String imgdist, Integer widthdist,      
	        Integer heightdist) {
		widthdist = widthdist == null ? 1010 : widthdist;
		heightdist = heightdist == null ? 600 : heightdist;
	    try {      
			
	        File srcfile = new File(imgsrc);      
	        if (!srcfile.exists()) {      
	            return "";      
	        }    
	        if(imgsrc.toLowerCase().endsWith("png")){
	        	BufferedImage bufferedImage = ImageIO.read(new File(imgsrc));
	 	        BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),
	 	        bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
	 	        newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
	 	        ImageIO.write(newBufferedImage, "jpg", new File(imgdist));
	 	        
	 	        imgsrc = imgdist;
	 	        
	        }
	        
	        Image src = javax.imageio.ImageIO.read(srcfile); 
	        BufferedImage tag= new BufferedImage((int) widthdist, (int) heightdist,      
	                BufferedImage.TYPE_INT_RGB);      
	     
	        tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist,  Image.SCALE_SMOOTH), 0, 0,  null);      
	              
	        FileOutputStream out = new FileOutputStream(imgdist);      
	        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
	        JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);  
            // 压缩质量  
	        jep.setQuality(0.8f, true);  
            encoder.encode(tag, jep); 
	        
	        out.close();      
	     
	        return imgdist;
	    } catch (Exception ex) {      
	        ex.printStackTrace(); 
	        logger.error("图片转换压缩异常",ex);
	        return "";
	    }      
	} 
}
