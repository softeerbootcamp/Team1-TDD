package com.tdd.backend.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.tdd.backend.auth.AuthResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Value("${domain.address}")
	private String domainAddress; //개발환경에 따른 도메인 주소를 yml에 파일변수로 세팅

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins(domainAddress)
			.allowedMethods("*")
			.maxAge(3000);
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new AuthResolver());
	}
}
