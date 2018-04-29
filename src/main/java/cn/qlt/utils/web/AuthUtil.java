package cn.qlt.utils.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import cn.qlt.domain.User;

/**
 * @ClassName: AuthUtil
 * @date 2014年2月27日 上午10:10:09
 * 
 */
@Component
public class AuthUtil {
	
	public final static String SYSTEM="master";
	public final static String TEACHRE="teacher";
	public final static String CLASS="class";

	private static ThreadLocal<User> threadLocal = new ThreadLocal<User>();

	/**
	 * @Title: auth @param request HTTP请求 @return boolean 是否登录 @throws
	 * UnauthorizedException 未登录请求抛出此异常 @throws
	 */
	public static boolean auth(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			if (user != null) {
				threadLocal.set(user);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		// }
		// }
		// return false;
	}

	/**
	 * @Title: leave @Description: 离开，用户请求操作结束时调用。 @throws
	 */
	public static void leave() {
		threadLocal.remove();
	}

	public static User getCurrentUser() {
		return threadLocal.get();
	}

	/**
	 * @Title: login @param response HTTP响应 @param userId 用户编号 @param userName
	 * 用户显示姓名 @throws Exception @throws
	 */
	public static void login(HttpServletRequest request, User user) throws Exception {

		request.getSession().setAttribute("user", user);
	}

}
