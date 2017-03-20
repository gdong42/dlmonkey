package com.dlmonkey.model;

import org.springframework.ui.Model;

/**
 * @author gdong
 */
public class InstagramImage extends InstagramMedia {

  @Override
  public void populate(Model model) {
    model.addAttribute("type", "image");
    model.addAttribute("url", this.getUrl());
    model.addAttribute("description", this.getDescription());
  }
}
