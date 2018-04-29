package cn.qlt.utils.web;


import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

public class QltFreeMarkerViewResolver extends AbstractTemplateViewResolver {

	public QltFreeMarkerViewResolver() {
		setViewClass(requiredViewClass());
	}

	/**
	 * Requires {@link FreeMarkerView}.
	 */
	@Override
	protected Class<?> requiredViewClass() {
		return QltFreeMarkerView.class;
	}
}
