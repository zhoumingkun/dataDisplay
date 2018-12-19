package com.toughguy.educationSystem;

import java.util.concurrent.TimeUnit;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
@EnableAutoConfiguration
@ServletComponentScan(basePackages = {"com.toughguy.educationSystem.filter"})
public class educationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(educationSystemApplication.class, args);
	}
	
	//-- 自己的应用的服务器设置
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
//	    TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory() {
//	    	@Override
//	    	protected void postProcessContext(Context context) {
//	    		SecurityConstraint securityConstraint = new SecurityConstraint();
//	    		securityConstraint.setUserConstraint("CONFIDENTIAL");
//	    		SecurityCollection collection = new SecurityCollection();
//	    		collection.addPattern("/*");
//	    		securityConstraint.addCollection(collection);
//	    		context.addConstraint(securityConstraint);
//	    	}
//	    };
//	    factory.addAdditionalTomcatConnectors(initiateHttpConnector());
//	    factory.setPort(8083);
//	    factory.setContextPath("/educationSystem");
//	    factory.setSessionTimeout(60, TimeUnit.MINUTES);
//	    return factory;
		 TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		    factory.setPort(8080);
		    factory.setContextPath("/educationSystem");
		    factory.setSessionTimeout(60, TimeUnit.MINUTES);
		    return factory;
	}
	
//	private Connector initiateHttpConnector() {
//		
//		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//		connector.setScheme("http");
//		connector.setPort(8085);
//		connector.setSecure(false);
//		connector.setRedirectPort(8443);
//		return connector;
//	}
}
