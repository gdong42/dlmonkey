package com.dlmonkey.model;

import org.springframework.ui.Model;

/**
 * @author gdong
 */
public class InstagramVideo extends InstagramMedia {

  private String type;

  private String width;

  private String height;

  private String secureUrl;

  private String posterUrl;

  @Override
  public void populate(Model model) {
    model.addAttribute("type", "video");
    model.addAttribute("url", this.getUrl());
    model.addAttribute("description", this.getDescription());
    model.addAttribute("videoType", this.getType());
    model.addAttribute("width", this.getWidth());
    model.addAttribute("height", this.getWidth());
    model.addAttribute("secureUrl", this.getSecureUrl());
    model.addAttribute("posterUrl", this.getPosterUrl());
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getWidth() {
    return width;
  }

  public void setWidth(String width) {
    this.width = width;
  }

  public String getHeight() {
    return height;
  }

  public void setHeight(String height) {
    this.height = height;
  }

  public String getSecureUrl() {
    return secureUrl;
  }

  public void setSecureUrl(String secureUrl) {
    this.secureUrl = secureUrl;
  }

  public String getPosterUrl() {
    return posterUrl;
  }

  public void setPosterUrl(String posterUrl) {
    this.posterUrl = posterUrl;
  }

  @Override
  public String toString() {
    return "InstagramVideo{" +
        "type='" + type + '\'' +
        ", width='" + width + '\'' +
        ", height='" + height + '\'' +
        ", secureUrl='" + secureUrl + '\'' +
        ", posterUrl='" + posterUrl + '\'' +
        '}';
  }
}
