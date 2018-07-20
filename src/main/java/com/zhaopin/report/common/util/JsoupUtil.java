package com.zhaopin.report.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.zhaopin.report.common.constant.WebConstants;

public class JsoupUtil {

	private final static String URI = "http://mp.weixin.qq.com/s?src=3&timestamp=1471516926&ver=1&signature=rNI*1Q4AMTGVge80ReFqvXEAwBQKaamKM2bzjc9bfcPqEAtuoFqoXZx6w*2deSDys6M5ubbdtQNb8mA2-8FsK5hQV39bcuP3FLnSZSlOFqUSEUa27KfhIxcsEg*j4vn8TPlefI24hxH6UyZGY6t4txBYfpvKpbGB7OTg0qQtLm4=";
	private final static String CODE = "UTF-8";
	private final static String IDER = "pc_0_subd";
	private static List<Map<String, String>> list = new ArrayList<Map<String,String>>();
	private static URL uri;
	private static URLConnection connection;
	private static String ROOTPATH = WebConstants.UPLOAD_FILE_PATH+"/html";
	
	public static InputStream getInputStream(){
		try {
			uri = new java.net.URL(URI);
			connection = uri.openConnection();
			return connection.getInputStream();
			 
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static OutputStream getOnputStream(){
		try {
			uri = new java.net.URL(URI);
			connection = uri.openConnection();
			return connection.getOutputStream();
			 
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<Map<String, String>> getContent(){
		try {
			Document doc = Jsoup.parse(getInputStream(), CODE, URI);
			Element element = doc.getElementById(IDER);
			Elements eleList = element.children();
			for(int i = 0;i<eleList.size();i++){
				Map<String, String> articleMap = new HashMap<String, String>();
				Element title = eleList.get(i).child(2).child(0);
				Elements titlelinks = title.getElementsByTag("a");
				articleMap.put("title", titlelinks.get(0).text()) ;//文章标题
				articleMap.put("href", titlelinks.get(0).attr("href")); //文章链接
				
				Element content = element.child(i).child(2).child(1);
				Elements contentlinks = content.getElementsByTag("a");
				articleMap.put("content", contentlinks.get(0).text()); //文章简介
				
				Element image = eleList.get(i);
				Elements imglinks = image.getElementsByTag("img");
				articleMap.put("image", imglinks.get(0).attr("src")); //图片路径
				
				list.add(articleMap);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void upLoadHtml(String path){
		File file = new File(ROOTPATH);
		if(!file .exists()  && !file .isDirectory()){
			file.mkdirs();
		}
		String filepath = UUID.randomUUID().toString()+".html";
        InputStream in = getInputStream();
        FileOutputStream fo;
		try {
		fo = new FileOutputStream(new File(ROOTPATH+"/"+filepath));
		
        byte[] buf = new byte[1024];  
        int length = 0;  
        System.out.println("开始下载:" + filepath);  
        while ((length = in.read(buf, 0, buf.length)) != -1) {  
            fo.write(buf, 0, length);  
        }
        in.close();  
        fo.close(); 
		}catch (IOException e) {
			e.printStackTrace();
		}
        
        System.out.println(filepath + "下载完成");
	}
	
	public static void main(String[] args) {
		upLoadHtml("");
	}
}
