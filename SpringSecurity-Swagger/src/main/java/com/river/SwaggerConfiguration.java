package com.river;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-04-20 23:17
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(
                        RequestHandlerSelectors.basePackage("com.ramostear.apisecurity.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(
                        new ApiInfoBuilder()
                                .title("Spring Security接管Swagger认证授权")
                                .description("Spring Security and Swagger")
                                .version("1.0.0")
                                .contact(
                                        new Contact(
                                                "树下魅狐",
                                                "https://www.ramostear.com",
                                                "ramostear@163.com"
                                        )
                                ).build()
                );
    }
}
