package com.mh.base.web.springboot.config;

import java.util.Locale;

import javax.servlet.DispatcherType;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import com.mh.base.web.filter.AdminSessionFilter;

@Configuration
@ComponentScan(basePackages = { "com.mh.base.web.springboot.controller" })
public class SpringMVCConfigure extends WebMvcAutoConfigurationAdapter {

	public static final String JQUERY_DATA_TABLE_CHINESE = "//cdn.datatables.net/plug-ins/1.10.10/i18n/Chinese.json";
	public static final String JQUERY_DATA_TABLE_ENGLISH = "//cdn.datatables.net/plug-ins/1.10.10/i18n/English.json";
	private static final String MESSAGE_SOURCE = "classpath:i18n/messages";

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/index.html");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		super.addViewControllers(registry);
	}

	@Bean
	public FilterRegistrationBean adminSessionFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new AdminSessionFilter());
		registration.setDispatcherTypes(DispatcherType.REQUEST);
		registration.addUrlPatterns("/admin/*");
		registration.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
		return registration;
	}

	@Bean(name = "messageSource")
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename(MESSAGE_SOURCE);
		messageSource.setFallbackToSystemLocale(false);
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setCacheSeconds(5);
		return messageSource;
	}

	public static Locale getLocale() {
		// mapping to classpath:i18n/messages_zh.properties
		// return Locale.CHINESE;
		// mapping to classpath:i18n/messages.properties
		return null;
	}

}
