package com.dlmonkey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class DlmonkeyApplication {

  public static void main(String[] args) {
    SpringApplication.run(DlmonkeyApplication.class, args);
  }

  @Bean
  public WebMvcConfigurer configurer() {
    return new WebMvcConfigurerAdapter() {
      @Override
      public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/about").setViewName("about.html");
      }
    };
  }

//  @Bean
//  public EmbeddedServletContainerCustomizer containerCustomizer() {
//    return container -> {
//      final ErrorPage notFoundPage = new ErrorPage(HttpStatus.NOT_FOUND,
//          "/404.html");
//      container.addErrorPages(notFoundPage);
//    };
//  }
}
