package cn.qlt.utils.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.qlt.domain.Role;
import cn.qlt.domain.User;
import cn.qlt.utils.EnvironmentUtils;
import cn.qlt.utils.EnvironmentUtils.Enviroment;

public class EnvironmentInterceptor implements HandlerInterceptor{
	
	@Autowired
	private EnvironmentUtils environmentUtils;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			Enviroment enviroment = EnvironmentUtils.currentEnvironment();
			if(enviroment==null){
				environmentUtils.createEnvironment();
				enviroment = EnvironmentUtils.currentEnvironment();
				enviroment.setRequest(request);
				enviroment.setSession(request.getSession());
			}
			enviroment.setBase(request);
			HandlerMethod method = (HandlerMethod) handler;
			Auth auth = method.getMethodAnnotation(Auth.class);
			boolean islogined = AuthUtil.auth(request, response);
			if (auth != null){
				if(islogined){
					if("".equals(auth.role())) return true;
					User user = AuthUtil.getCurrentUser();
					for(Role role:user.getRoles()){
						if(role.getCode().equals(auth.role())){
							return true;
						}
					}
					throw new BusinessException(403, "权限不足");
				}else{
					throw new BusinessException(404, "未登录或登录以超时！");
				}
			}	
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		EnvironmentUtils.clear();
		AuthUtil.leave();
	}

}
