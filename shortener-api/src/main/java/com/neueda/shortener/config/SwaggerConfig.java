package com.neueda.shortener.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.select()
        	    .apis(RequestHandlerSelectors.basePackage("com.neueda.shortener.controller"))
        	    .paths(PathSelectors.any())
        	    .build()
        	    .apiInfo(metaData());
	
    }
	
    private ApiInfo metaData() {
    	Collection<VendorExtension> vendorExtensions = Collections.EMPTY_LIST;
        ApiInfo apiInfo = new ApiInfo(
                "URL Shortener API",
                "Backend API for URL Shortener Application",
                "1.0",
                "Terms of service",
                new Contact("Sidharthan Kamaraj", "https://cksidharthan.github.io", "csidharthank@gmail.com"),
               "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0", vendorExtensions);
        return apiInfo;
    }
	

}
