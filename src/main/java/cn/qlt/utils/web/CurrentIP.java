package cn.qlt.utils.web;

import javax.servlet.http.HttpServletRequest;


/**
* <h1>CurrentIP</h1><br>
* 当前IP获取
* @author 江立国
* @since 2014年4月25日 下午8:38:51
*
*/
public class CurrentIP {

	private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();
	
	public static void putIP(HttpServletRequest request){
		String header = request.getHeader("X-Real-Ip");
		if(header!=null && !"".equals(header)){
			threadLocal.set(header);
		}else{
			threadLocal.set(request.getRemoteHost());
		}
	}
	
	public static String getIP(){
		return threadLocal.get();
	}
}
