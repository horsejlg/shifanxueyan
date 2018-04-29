package cn.qlt.utils;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QlDateFormat extends SimpleDateFormat {

	private static final long serialVersionUID = -7712649608066970212L;
	
	private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>();
	
	private DateFormat getFormat(){
		DateFormat format = threadLocal.get();
		if(format==null){
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			threadLocal.set(format);
		}
		return format;
	}

	public StringBuffer format(Date date, StringBuffer toAppendTo,
			FieldPosition fieldPosition) {
		// TODO 自动生成的方法存根
		return getFormat().format(date, toAppendTo, fieldPosition);
	}

	@Override
	public Date parse(String source) throws ParseException {
		// TODO 自动生成的方法存根
		return parse(source, null);
	}
	
	@Override
	public Date parse(String source, ParsePosition pos) {
		if(source!=null)
		try {
			if(source.matches("[-]{0,1}\\d+")){
				return new Date(Long.valueOf(source));
			}else if (source.length() < 11) {
				return getDateInstance().parse(source);
			} else if (source
					.matches("\\d{1,4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
				return getDateTimeInstance().parse(source);
			} else if (source.lastIndexOf(".") > -1) {
				return getFormat().parse(source);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
