package cn.qlt.utils.web;

/**
 * @ClassName: BusinessException
 * @Description: (业务异常)
 * @author 张福柱
 * @date 2014年2月26日 下午3:45:40
 * 
 */
public class BusinessException extends RuntimeException {

	/**
	 * @Fields serialVersionUID : (用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -3806456902100358000L;

	public static final BusinessException errpage = new BusinessException(500);

	/**
	 * @Fields code : 错误代码
	 */
	private int code;
	private String type = "error-business";

	public int getCode() {
		return code;
	}

	public String getType() {
		return type;
	}
	public BusinessException(int code) {
		// TODO Auto-generated constructor stub
		this.code = code;
	}

	public BusinessException(int code, String message) {
		super(message);
		this.code = code;
		// TODO Auto-generated constructor stub
	}

	public BusinessException(int code, String message, String type) {
		super(message);
		this.code = code;
		this.type = type;
		// TODO Auto-generated constructor stub
	}

	public BusinessException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}


}
