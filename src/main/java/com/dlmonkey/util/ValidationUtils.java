package com.dlmonkey.util;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.util.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

/**
 * @author gdong
 */
public class ValidationUtils {

  private static UrlValidator urlValidator = new UrlValidator(
      Arrays.asList("http", "https").toArray(new String[2]));

  private static final String INSTAGRAM_URL_HOST_WWW = "www.instagram.com";

  private static final String INSTAGRAM_URL_HOST_NON_WWW = "instagram.com";

  private static final String INSTAGRAM_URL_PATH_PREFIX = "/p";

  // https://www.instagram.com/p/BLj_bbaDzta/?taken-by=totto.31
  public static boolean isValidInstagramUrl(String url) {
    if (StringUtils.isEmpty(url)) {
      return false;
    }
    if (!urlValidator.isValid(url)) {
      return false;
    }
    URL inputUrl = null;
    try {
      inputUrl = new URL(url);
    } catch (MalformedURLException e) {
      return false;
    }
    String host = inputUrl.getHost();
    if (host == null || !INSTAGRAM_URL_HOST_WWW.equals(host) || !INSTAGRAM_URL_HOST_NON_WWW
        .equals(host)) {
      return false;
    }
    String path = inputUrl.getPath();
    if (path == null || !path.startsWith(INSTAGRAM_URL_PATH_PREFIX)) {
      return false;
    }
    return true;
  }
}
