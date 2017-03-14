package com.dlmonkey.controller;

import com.dlmonkey.model.Instagram;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author gdong
 */
@Controller
public class IndexController {

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("instagram", new Instagram());
    return "index";
  }

  @PostMapping("/instagram/image")
  public String image(@ModelAttribute Instagram instagram) {

    System.out.println(instagram.getUrl());

    return "index";
  }
}
