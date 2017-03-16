package com.dlmonkey.controller;

import com.dlmonkey.model.Instagram;
import com.dlmonkey.model.InstagramImage;
import com.dlmonkey.service.InstagramParser;
import com.dlmonkey.util.ValidationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gdong
 */
@Controller
public class IndexController {

  private static final Logger logger = LoggerFactory
      .getLogger(IndexController.class);

  @Autowired
  private InstagramParser instagramParser;

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("instagram", new Instagram());
    return "index";
  }

  @PostMapping("/instagram/image")
  public String image(@ModelAttribute Instagram instagram, Model model,
      HttpServletRequest request) {

    String ipAddress = request.getHeader("X-FORWARDED-FOR");

    logger.info(" ==== {} / {} requested instagram url: {}",
        request.getRemoteAddr(), ipAddress, instagram.getUrl());
    final String url = instagram.getUrl();
    InstagramImage image = new InstagramImage();
    if (ValidationUtils.isValidInstagramUrl(url)) {

      image = instagramParser.parseImageUrl(url);

      logger.info("    parsed image url is {}", image);
    } else {
      logger.warn("    requested for URL {} from IP {} is INVALID", url,
          request.getRemoteAddr());
    }
    model.addAttribute("image", image);
    return "index";
  }
}
