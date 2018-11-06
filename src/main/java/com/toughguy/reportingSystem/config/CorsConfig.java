package com.toughguy.reportingSystem.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * 跨域请求配置
 * @author BOBO
 *
 */
@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter {
	
	 private CorsConfiguration buildConfig() {
		    CorsConfiguration corsConfiguration = new CorsConfiguration();
		    List<String> list = new ArrayList<>();
		    list.add("*");
		    corsConfiguration.setAllowedOrigins(list);
		    /*
		    // 请求常用的三种配置，*代表允许所有，当时你也可以自定义属性（比如header只能带什么，只能是post方式等等）
		    */
		    corsConfiguration.addAllowedOrigin("*"); 
		    corsConfiguration.addAllowedHeader("*"); 
		    corsConfiguration.addAllowedMethod("*"); 
		    return corsConfiguration;
	 }
	 @Bean
	  public CorsFilter corsFilter() {
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", buildConfig());
	    return new CorsFilter(source);
	  }

}
