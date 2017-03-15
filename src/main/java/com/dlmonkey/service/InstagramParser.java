package com.dlmonkey.service;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author gdong
 */
@Service
public class InstagramParser {

  private static final Logger logger = LoggerFactory
      .getLogger(InstagramParser.class);

  private static final int TIMEOUT = 5 * 1000;  // in ms

  private HttpClient client;

  public InstagramParser() {
    RequestConfig config = RequestConfig.custom()
        .setConnectTimeout(TIMEOUT)
        .setConnectionRequestTimeout(TIMEOUT)
        .setSocketTimeout(TIMEOUT)
        .build();
    client = HttpClientBuilder.create()
        .setDefaultRequestConfig(config)
        .build();
  }

  public String parseImageUrl(String url) {

    HttpGet request = new HttpGet(url);
    String html = null;
    try {
      HttpResponse response = client.execute(request);
      html = EntityUtils.toString(response.getEntity());
    } catch (IOException e) {
      logger.error("Failed to parse response for url. ", e);
      return "";
    }
    System.out.println(html);
    return "hahahahahahahahah";
  }
}
