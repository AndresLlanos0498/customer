package com.customer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * My <b>Spring</b>.
 * My annotations class main
 *
 * @Data
 */
@SpringBootApplication
@EnableSwagger2
public class CustomerApplication {

  /**
  * My <b>Spring</b>.
  * My annotations class main
  *
  * @Bean
  */
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
    .select().apis(RequestHandlerSelectors.any())
    .paths(PathSelectors.any()).build();
  }

  public static void main(String[] args) {
    SpringApplication.run(CustomerApplication.class, args);
  }

}
