package cn.qlt.utils.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CommonInterceptor implements HandlerInterceptor {


	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		CurrentIP.putIP(request);//ip
		
		if (AuthUtil.auth(request, response)) {
				return true;
			} else {
				String uri = request.getRequestURI();
				if(uri.endsWith(".html")){
					response.sendRedirect(request.getContextPath()+"/login.html");
					return false;
				}else{
					throw new BusinessException(401,"用户未登陆！");
				}
			}
		}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		// System.out.println("post");

	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		AuthUtil.leave();

	}
	
}