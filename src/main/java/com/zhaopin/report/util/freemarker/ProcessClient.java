package com.zhaopin.report.util.freemarker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class ProcessClient
{

    public static void processBody(HttpServletRequest request, String filename)
            throws UnsupportedEncodingException, FileNotFoundException
    {
        String htmlPath = request.getSession().getServletContext()
                .getRealPath("/WEB/templates/html");
        
        File path = new File(htmlPath);
        
        String[] indexfileList = path.list(new DirectoryFilter(filename));

        String templatesPath = "D:/MyEclipse/workspaces/freeMaker/templates";
        // 下面是模板的名称
        String templateFile = "/user.ftl";
        
        // 下面是静态页面输出路径
        String htmlFile = htmlPath + filename;

        // 如果文件目录为空则
        if (indexfileList.length == 0)
        {
            Writer out = new OutputStreamWriter(new FileOutputStream(htmlPath
                    + "/" + filename), "UTF-8");

            Map<String, Object> root = new HashMap<String, Object>();
            //FreeMarkertUtil.analysisTemplate(templatesPath, templateFile,
                   // htmlFile, root);
        }
    }

}
