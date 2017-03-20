package com.dlmonkey.controller;

import com.dlmonkey.model.Instagram;
import com.dlmonkey.model.InstagramImage;
import com.dlmonkey.model.InstagramMedia;
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
    model.addAttribute("image", new InstagramImage());
    return "index";
  }

  @GetMapping("/instagram")
  public String showImg() {
    return "redirect:index";
  }

  @PostMapping("/instagram")
  public String getMedia(@ModelAttribute Instagram instagram, Model model,
      HttpServletRequest request) {

    String ipAddress = request.getHeader("X-FORWARDED-FOR");

    logger.info(" ==== {} / {} requested instagram url: {}",
        request.getRemoteAddr(), ipAddress, instagram.getUrl());
    final String url = instagram.getUrl();
    if (ValidationUtils.isValidInstagramUrl(url)) {
      InstagramMedia media = instagramParser.parseMedia(url);
      if (media != null) {
        media.populate(model);
        return "index";
      }
      model.addAttribute("message", "Sorry, we don't seem to know the URL");
    } else {
      logger.warn("    requested for URL {} from IP {} is INVALID", url,
          request.getRemoteAddr());
      model.addAttribute("message",
          "Please input the entire instagram photo URL including 'https://' ");
    }
    return "index";
  }
}
