package com.tweetapp.configs.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Kalyan Kommulapati
 * @project TweetApp-API
 * @since 06/07/2022 - 07:52 PM
 */
@EnableSwagger2
@Component
public class SwaggerConfig {

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("Twitter App Service").apiInfo(apiInfo()).select().build();
    }


    /**
     * Internal method used by Swagger to configure its test user-interface.
     *
     * @return Object of various configured application properties.
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Tweet App Api Service")
                .description("Tweet App - Stay Connected.")
                .termsOfServiceUrl("TBD")
                .contact(new Contact("Kalyan", "https://www.linkedin.com/in/heykalyan/", "ikalyan183@gmail.com"))
                .license("License of API").version("1.0").build();
    }

}
