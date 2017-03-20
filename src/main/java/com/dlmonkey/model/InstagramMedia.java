package com.dlmonkey.model;

import org.springframework.ui.Model;

/**
 * @author gdong
 */
public abstract class InstagramMedia {

  private String url;

  private String description;

  /**
   * populates attributes into the given model using this media.
   *
   * @param model the target model to inject into
   */
  public abstract void populate(Model model);

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
