package com.zhaopin.report.util.freemarker;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.zhaopin.report.init.SpringContextHolder;
import com.zhaopin.report.util.StringUtil;

import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.utility.DeepUnwrap;

public class FreeMarkerMgr {
	
	private static final Logger logger = Logger.getLogger(FreeMarkerMgr.class);
	
    /** ConvertUtilsBean */
    private static final ConvertUtilsBean convertUtils;
    
    
    static {
        convertUtils = new ConvertUtilsBean();
    }
    
    // 静态内部类，创建单例对象
    private static class SingletonHolder
    {
        private static final FreeMarkerMgr INSTANCE = new FreeMarkerMgr();
    }

    private FreeMarkerMgr()
    {
    }

    // 获取单例
    public static final FreeMarkerMgr getInstance()
    {
        // 当调此处，系统将会自动加载静态内部类，创建静态变量
        return SingletonHolder.INSTANCE;
    }

    // templatePath模板文件存放路径
    // templateName 模板文件名称
    // filename 生成的文件名称
    public void analysisTemplate(HttpServletRequest request,
            HttpServletResponse response, TemplateInfo templateInfo,
            Map<?, ?> model) {
    	try {
    		Template template = getTemplate(request,"UTF-8", templateInfo);
    		if (templateInfo.isCreateHtml()) {
    			createHTML(template, model, templateInfo, request, response);
    		} else {
    			processTemplate(template, model, templateInfo.getFileName(), response);
    		}
    	} catch (IOException ioe) {
    		logger.error("解析freemarker模板出错，模板路径：" + templateInfo.getTemplateName());
    		logger.error("静态化目录：" + templateInfo.getStaticPath());
    		logger.error(ioe);
    	} catch (TemplateException te) {
    		logger.error("解析freemarker模板出错，模板路径：" + templateInfo.getTemplateName());
    		logger.error("静态化目录：" + templateInfo.getStaticPath());
    		logger.error(te);
    	} catch (ServletException se) {
    		logger.error("解析freemarker模板出错，模板路径：" + templateInfo.getTemplateName());
    		logger.error("静态化目录：" + templateInfo.getStaticPath());
    		logger.error(se);
    	}
    	
    }

    public Template getTemplate(HttpServletRequest request,String locale, TemplateInfo templateInfo)
            throws IOException
    {
        Configuration config = null;
        ApplicationContext applicationContext = SpringContextHolder.getApplicationContext();
        
        if (applicationContext != null) {
                                                                                     
            FreeMarkerConfigurer freeMarkerConfigurer = SpringContextHolder.getBean("freeMarkerConfigurer");
            if (freeMarkerConfigurer != null) {
                config = freeMarkerConfigurer.getConfiguration();
            }
        }
        
/*        String path=request.getSession().getServletContext()
                .getRealPath("/")+TEMPLATEPATH;*/
        
       // 设置要解析的模板所在的目录，并加载模板文件
       // config.setDirectoryForTemplateLoading(new File(path));

       // 设置包装器，并将对象包装为数据模型
       // config.setObjectWrapper(new DefaultObjectWrapper());
        
        String tpl=templateInfo.getLayout()+"/"+templateInfo.getTemplateName();
        
        // 获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
        // 否则会出现乱码
        Template template = config.getTemplate(tpl,
                "UTF-8");
        return template;
    }
    
    public void processTemplate(Template template, Map<?, ?> model,
            String fileName, HttpServletResponse respone)
            throws TemplateException, IOException
    {
        
        // 合并数据模型与模板
        FileOutputStream fos = new FileOutputStream(fileName);
        Writer out = new OutputStreamWriter(fos, "UTF-8");
        template.process(model, out);
        out.flush();
        out.close();
    }

    public void createHTML(Template template, Map<?, ?> model,TemplateInfo templateInfo,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException, TemplateException, ServletException
    {
        // 站点根目录的绝对路径
        String basePath = request.getSession().getServletContext().getRealPath("/");
        String requestHTML = this.getRequestHTML(request);
        
        // 静态页面绝对路径
        String htmlPath = "";
        //将请求转发到生成的htm文件路径 
        String requestPath = requestHTML;
        if (StringUtil.isNotEmpty(templateInfo.getStaticPath())) {
        	String fileName = requestHTML.substring(requestHTML.lastIndexOf("/") + 1, requestHTML.length());
        	htmlPath = basePath + templateInfo.getStaticPath()+ fileName;
        	requestPath = "/" + templateInfo.getStaticPath()+ fileName;
        } else {
        	htmlPath = basePath + requestHTML;
        }

        htmlPath = htmlPath.replace("/", File.separator);
        File htmlFile = new File(htmlPath);
        if (!htmlFile.getParentFile().exists())
        {
            htmlFile.getParentFile().mkdirs();
        }
        if (!htmlFile.exists())
        {
            htmlFile.createNewFile();
        }
        Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(htmlFile), "UTF-8"));
        // 处理模版
        template.process(model, out);
        out.flush();
        out.close();
        request.getRequestDispatcher(requestPath).forward(request, response);
    }

    /**
     * 计算要生成的静态文件相对路径 因为大家在调试的时候一般在Tomcat的webapps下面新建站点目录的，
     * 但在实际应用时直接布署到ROOT目录里面,这里要保证路径的一致性。
     * 
     * @param request
     *            HttpServletRequest
     * @return /目录/*.htm
     */
    private String getRequestHTML(HttpServletRequest request)
    {
        // web应用名称,部署在ROOT目录时为空
        String contextPath = request.getContextPath();
        
        // web应用/目录/文件.do
        String requestURI = request.getRequestURI();
        
        // basePath里面已经有了web应用名称，所以直接把它replace掉，以免重复
        requestURI = requestURI.replaceFirst(contextPath, "");
        
        // 将.do改为.htm,稍后将请求转发到此htm文件
        requestURI = requestURI.substring(0, requestURI.indexOf(".")) + ".html";
        return requestURI;
    }
    
    /**
     * 获取参数
     * 
     * @param name
     *            名称
     * @param type
     *            类型
     * @param params
     *            参数
     * @return 参数,若不存在则返回null
     */
    public static <T> T getParameter(String name, Class<T> type, Map<String, TemplateModel> params) throws TemplateModelException {
        Assert.hasText(name);
        Assert.notNull(type);
        Assert.notNull(params);
        TemplateModel templateModel = params.get(name);
        if (templateModel == null) {
            return null;
        }
        Object value = DeepUnwrap.unwrap(templateModel);
        return (T) convertUtils.convert(value, type);
    }
    
    
    /**
     * 获取变量
     * 
     * @param name
     *            名称
     * @param env
     *            Environment
     * @return 变量
     */
    public static TemplateModel getVariable(String name, Environment env) throws TemplateModelException {
        Assert.hasText(name);
        Assert.notNull(env);
        return env.getVariable(name);
    }

    /**
     * 设置变量
     * 
     * @param name
     *            名称
     * @param value
     *            变量值
     * @param env
     *            Environment
     */
    public static void setVariable(String name, Object value, Environment env) throws TemplateException {
        Assert.hasText(name);
        Assert.notNull(env);
        if (value instanceof TemplateModel) {
            env.setVariable(name, (TemplateModel) value);
        } else {
            env.setVariable(name, ObjectWrapper.BEANS_WRAPPER.wrap(value));
        }
    }
    
    /**
     * 设置变量
     * 
     * @param variables
     *            变量
     * @param env
     *            Environment
     */
    public static void setVariables(Map<String, Object> variables, Environment env) throws TemplateException {
        Assert.notNull(variables);
        Assert.notNull(env);
        for (Entry<String, Object> entry : variables.entrySet()) {
            String name = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof TemplateModel) {
                env.setVariable(name, (TemplateModel) value);
            } else {
                env.setVariable(name, ObjectWrapper.BEANS_WRAPPER.wrap(value));
            }
        }
    }
}
