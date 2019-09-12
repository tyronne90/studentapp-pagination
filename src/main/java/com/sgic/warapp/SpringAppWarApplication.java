package com.sgic.warapp;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class SpringAppWarApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(SpringAppWarApplication.class, args);
  }

  @Bean
  public Docket swaggerConfiguration() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.basePackage("com.sgic.warapp.controller"))
        .paths(PathSelectors.ant("/api/**")).build().apiInfo(apiDetails());
  }

  private ApiInfo apiDetails() {
    return new ApiInfo("Student App API", "Sample API for Student App", "1.0", "Free to User",
        new springfox.documentation.service.Contact("Tyron", "http://www.google.com",
            "tyronne90@gmail.com"),
        "API License", "http://www.google.com", Collections.emptyList());

  }

}
