package com.wandson.food.core.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.Filter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("*");
	}

	@Bean
	public Filter shallowEtagHeaderFilter() {
		var shallowEtagHeaderFilter = new ShallowEtagHeaderFilter();
		shallowEtagHeaderFilter.setWriteWeakETag(true);

		return shallowEtagHeaderFilter;
	}

}
