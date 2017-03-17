package com.dlmonkey.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author gdong
 */
@Controller
@AutoConfigureBefore(ErrorMvcAutoConfiguration.class)
public class GlobalErrorController implements ErrorController {

  private static final Logger logger = LoggerFactory
      .getLogger(GlobalErrorController.class);

  @Autowired
  private ErrorAttributes errorAttributes;

  @Override
  public String getErrorPath() {
    return "/error";
  }

  @RequestMapping(value = "/error")
  public String error(HttpServletRequest request) {
    Map<String, Object> data = errorAttributes
        .getErrorAttributes(new ServletRequestAttributes(request), false);
    logger.error("Error occurred: {}", data);
    return "redirect:/";
  }
}
