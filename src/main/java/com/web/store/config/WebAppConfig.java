package com.web.store.config;

import java.util.ArrayList;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
@EnableWebMvc
@ComponentScan({"com.web.store"})
//@ComponentScan({ "com.web.store.controller", "com.web.store.model", "com.web.store.service.impl",
//		"com.web.store.repository.impl", "com.web.store.config" })
public class WebAppConfig implements WebMvcConfigurer {

	@Bean
	public ViewResolver resolver() {
		InternalResourceViewResolver irvr = new InternalResourceViewResolver();
		irvr.setPrefix("/WEB-INF/views/"); // "/WEB-INF/views/" + "index" + ".jsp"
		irvr.setSuffix(".jsp"); // "/WEB-INF/views/index.jsp
		return irvr;
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename("messages");
		return source;
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() { 
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("UTF-8");
		resolver.setMaxUploadSize(81920000);
		return resolver;
	}
	
	@Bean 
	public MappingJackson2JsonView jsonView() {
	    MappingJackson2JsonView view = new MappingJackson2JsonView();
	    view.setPrettyPrint(true);
	    return view;
	}

	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
	    ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
	    resolver.setContentNegotiationManager(manager);
	    ArrayList<View> views = new ArrayList<>();
	    views.add(jsonView());
	    resolver.setDefaultViews(views);
	    return resolver;
	}

	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

//	@Override
//	// 為了處理靜態檔案必須加入下列敘述：只要是 /css/開頭的任何請求，都轉到/WEB-INF/views/css/去尋找
//	// 為了處理靜態檔案必須加入下列敘述：只要是 /image/開頭的任何請求，都轉到/WEB-INF/views/images/去尋找
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/css/**")
//				.addResourceLocations("/WEB-INF/views/css/");
//		registry.addResourceHandler("/image/**")
//				.addResourceLocations("/WEB-INF/views/images/");
//		registry.addResourceHandler("/js/**")
//				.addResourceLocations("/WEB-INF/views/js/");
//	}

}
