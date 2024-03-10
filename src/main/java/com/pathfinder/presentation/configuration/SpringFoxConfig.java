package com.pathfinder.presentation.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    Docket apiConfig() {
	return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
			.paths(PathSelectors.any())
			.build()
			.apiInfo(this.apiInfo())
			.useDefaultResponseMessages(false)
			.host("");
    }
	
    @Bean
    InternalResourceViewResolver defaultViewResolver() {
      return new InternalResourceViewResolver();
    }
    
    private ApiInfo apiInfo() {
	return new ApiInfoBuilder()
		.title("API PathFinder")
		.description("Esta API proporciona servicios para la gestion de rutas")
		.version("1.0.0")
		.license("Apache 2.0")
	    .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
	    .termsOfServiceUrl("https://github.com/DevWorksGPS")
		.build();
    }
    
    
}
