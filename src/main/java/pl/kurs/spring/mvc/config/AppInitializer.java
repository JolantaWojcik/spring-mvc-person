package pl.kurs.spring.mvc.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//to jest nasz "web.xml" tylko w wersji POJO
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	protected java.lang.Class<?>[] getRootConfigClasses() {
		return new Class[] { ApplicationConfiguration.class };
	}

}