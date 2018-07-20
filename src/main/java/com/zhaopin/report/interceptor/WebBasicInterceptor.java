package com.zhaopin.report.interceptor;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zhaopin.report.common.constant.WebConstants;
import com.zhaopin.report.mapper.model.User;


/**
 * @spring.bean id="webBasicInterceptor"
 * 
 * @author ltp
 * @version 1.0
 */
public class WebBasicInterceptor extends HandlerInterceptorAdapter {
   // @Autowired
    //private RedisService redisService;
    
	public WebBasicInterceptor() {

	}

	/**
	 * LOG.
	 */
	private static final Logger LOG = Logger.getLogger(WebBasicInterceptor.class);

	private List<String> noFilterUrls;

	public List<String> getNoFilterUrls() {
		return noFilterUrls;
	}

	public void setNoFilterUrls(List<String> noFilterUrls) {
		this.noFilterUrls = noFilterUrls;
	}


	/**
	 * 可以进行编码、安全控制等处理.
	 *
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param handler Object
	 * @return boolean
	 * @throws Exception if has Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response, Object handler) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		ServletContext sc = request.getSession().getServletContext();
		String mainserver=(String) sc.getAttribute("mainServer");
		User loginInfo=(User) request.getSession().getAttribute("logininfo");
		LOG.info("当前请求request:"+request.getRequestURI());
		if(loginInfo==null){
			PrintWriter out = response.getWriter();
			StringBuilder builder = new StringBuilder();
			builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");
			builder.append("window.top.location.href=\"");
			builder.append(mainserver+"/tologin");
			builder.append("\"</script>");
			out.print(builder.toString());
			out.close();
			return false;
		}
		return super.preHandle(request, response, handler);
	}

	/**
	 * 验证session是否在线.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return boolean
	 */
	public boolean validateSession(HttpServletRequest request, HttpServletResponse response) {
		String loginInfo = (String) request.getSession().getAttribute(WebConstants.SESSION_ADMIN_USER);
		if (null != loginInfo && !"".equals(loginInfo)) {
			return true;
		} else {
			String headerStr = request.getHeader("x-requested-with");
			if (null != headerStr && "XMLHttpRequest".equalsIgnoreCase(headerStr)) {
				response.setHeader("sessionstatus", "timeout");
				return false;
			}
		}
		return true;
	}

	// 在业务处理器处理请求执行完成后,生成视图之前执行的动作
	@Override
	public void postHandle(HttpServletRequest request,
						   HttpServletResponse response, Object handler,
						   ModelAndView modelAndView) throws Exception {
//		log.debug("==============执行顺序: 2、postHandle================");
	}

	/**
	 * 可以根据ex是否为null判断是否发生了异常，进行日志记录.
	 *
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception
	 */
	public void afterCompletion(HttpServletRequest request,
								HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
