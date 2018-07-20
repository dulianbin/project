package com.zhaopin.report.util.freemarker;

public class TemplateInfo
{
    
    /**
     *  布局
     */
    private String layout;
    
    /**
     * 模板名称
     */
    private String templateName;
    
    /**
     * html文件名称
     */
    private String fileName;
    
    /**
     * 是否创建html
     */
    private boolean isCreateHtml;
    
    /**
     * 静态化输出目录
     */
    private String staticPath;
    
    
    public String getLayout()
    {
        return layout;
    }

    public void setLayout(String layout)
    {
        this.layout = layout;
    }

    public String getTemplateName()
    {
        return templateName;
    }

    public void setTemplateName(String templateName)
    {
        this.templateName = templateName;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public boolean isCreateHtml()
    {
        return isCreateHtml;
    }

    public void setCreateHtml(boolean isCreateHtml)
    {
        this.isCreateHtml = isCreateHtml;
    }

    public String getStaticPath()
    {
        return staticPath;
    }

    public void setStaticPath(String staticPath)
    {
        this.staticPath = staticPath;
    }
    
    
}
