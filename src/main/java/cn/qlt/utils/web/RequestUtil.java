package cn.qlt.utils.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

public class RequestUtil {

	public static Map<String, String> getParams(HttpServletRequest request){
		Map<String, String> params = new HashMap<String,String>();
		Map<String, String[]> parameterMap = request.getParameterMap();
		for(Map.Entry<String, String[]> entry:parameterMap.entrySet()){
			params.put(entry.getKey(), StringUtils.arrayToCommaDelimitedString(entry.getValue()));
		}
		return params;
	}
}
