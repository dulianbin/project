package com.zhaopin.report.common.vo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.zhaopin.report.util.PageView;
import com.zhaopin.report.util.StringUtil;

public class PageData extends HashMap implements Map {
	
private static final long serialVersionUID = 1L;
	
	Map map = null;
	HttpServletRequest request;
	PageView pv;
	
	public PageData(HttpServletRequest request){
		this.request = request;
		request.getParameterMap();
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap(); 
		Iterator entries = properties.entrySet().iterator(); 
		Map.Entry entry; 
		String name = "";  
		String value = "";  
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next(); 
			name = (String) entry.getKey(); 
			Object valueObj = entry.getValue(); 
			if(null == valueObj){ 
				value = ""; 
			}else if(valueObj instanceof String[]){ 
				String[] values = (String[])valueObj;
				for(int i=0;i<values.length;i++){ 
					 value = values[i] + ",";
				}
				//若请求参数中带有%，则会出现错误
				/*try {
					value = URLDecoder.decode(value, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}*/
				value = value.substring(0, value.length()-1); 
				
			}else{
				value = valueObj.toString(); 
			}
			
			if ("rows".equals(name)) {
				if (pv == null) {
					pv = new PageView();
				}
				if (StringUtil.isLong(value)) {
					pv.setPageSize(Integer.valueOf(value));
				} else {
					pv.setPageSize(10);
				}
				continue;
			}
			if ("page".equals(name)) {
				if (pv == null) {
					pv = new PageView();
				}
				if (StringUtil.isLong(value)) {
					pv.setPageNow(Integer.valueOf(value));
				} else {
					pv.setPageNow(1);
				}
				continue;
			}
			if ("pageIndex".equals(name)) {
				if (pv == null) {
					pv = new PageView();
				}
				if (StringUtil.isLong(value)) {
					pv.setPageNow(Integer.valueOf(value));
				} else {
					pv.setPageNow(1);
				}
				continue;
			}
			returnMap.put(name, value);
		}
		
		if (pv != null) {
			returnMap.put("pageView", pv);	
		}
		map = returnMap;
	}
	
	public PageData() {
		map = new HashMap();
	}
	
	@Override
	public Object get(Object key) {
		Object obj = null;
		if(map.get(key) instanceof Object[]) {
			Object[] arr = (Object[])map.get(key);
			obj = request == null ? arr:(request.getParameter((String)key) == null ? arr:arr[0]);
		} else {
			obj = map.get(key);
		}
		return obj;
	}
	
	public Integer getInteger(Object key)throws RuntimeException {
		Integer number=null;
		Object o=get(key);
		if(o == null){
			number=null;
			return number;
		}
		if(o instanceof Integer){
			return (Integer)o;
		}else if(o instanceof String){
			try{
				number=Integer.parseInt(getString(key));
			}catch(RuntimeException e){
				throw e;
			}
		}else{
			throw new RuntimeException();
		}
	
		return number;
	}
	
	public String getString(Object key) {
		if(get(key) == null){
			return "";
		}
		return (String)get(key);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object key, Object value) {
		return map.put(key, value);
	}
	
	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	public void clear() {
		map.clear();
	}

	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return map.containsValue(value);
	}

	public Set entrySet() {
		// TODO Auto-generated method stub
		return map.entrySet();
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return map.isEmpty();
	}

	public Set keySet() {
		// TODO Auto-generated method stub
		return map.keySet();
	}

	@SuppressWarnings("unchecked")
	public void putAll(Map t) {
		// TODO Auto-generated method stub
		map.putAll(t);
	}

	public int size() {
		// TODO Auto-generated method stub
		return map.size();
	}

	public Collection values() {
		// TODO Auto-generated method stub
		return map.values();
	}
	
}
