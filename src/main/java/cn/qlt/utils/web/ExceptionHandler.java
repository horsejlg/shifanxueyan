package cn.qlt.utils.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.SystemException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * @ClassName: ExceptionHandler
 * @Description: (实现HandlerExceptionResolver 接口自定义异常处理器 .<bean
 *               id="exceptionHandler"
 *               class="cn.qlt.utils.web.ExceptionHandler"/> )
 * @author 张福柱
 * @date 2014年2月26日 上午11:03:15
 * 
 */
public class ExceptionHandler implements HandlerExceptionResolver {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		ModelAndView mav = new ModelAndView();
		MappingJackson2JsonView view = new MappingJackson2JsonView();
		Map<String, String> json = new HashMap<String, String>();
		// 根据不同错误转向不同页面
//		LOGGER.log(Level.WARNING, ex.getMessage(),Thread.currentThread());
		if (ex instanceof BusinessException) {
			
			json.put("type", ((BusinessException) ex).getType());
			json.put("message", ex.getMessage());
			response.setStatus(((BusinessException) ex).getCode());
			int code = ((BusinessException) ex).getCode();
			if(code<400 || code>499){
				LOGGER.error("error",ex);
				ex.printStackTrace();
			}
		} else if (ex instanceof SystemException) {
			LOGGER.error("error",ex);
			ex.printStackTrace();
			json.put("type", "error-system");
			json.put("message", ex.getLocalizedMessage());
			response.setStatus(500);
		} else {
			LOGGER.error("error",ex);
			ex.printStackTrace();
			json.put("type", "error");
			json.put("message", ex.getLocalizedMessage());
			response.setStatus(500);
		}
		view.setAttributesMap(json);
		mav.setView(view);
		return mav;
	}
}
