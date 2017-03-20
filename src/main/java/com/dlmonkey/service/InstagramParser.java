package com.dlmonkey.service;

import com.dlmonkey.model.InstagramMedia;
import com.dlmonkey.util.InstagramMediaFactory;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author gdong
 */
@Service
public class InstagramParser {

  private static final Logger logger = LoggerFactory
      .getLogger(InstagramParser.class);

  private static final int TIMEOUT = 10 * 1000;  // in ms

  private HttpClient client;

  @Autowired
  public InstagramParser(@Value("${app.http.proxy}") boolean useHttpProxy) {

    RequestConfig.Builder configBuilder = RequestConfig.custom()
        .setConnectTimeout(TIMEOUT)
        .setConnectionRequestTimeout(TIMEOUT)
        .setSocketTimeout(TIMEOUT);

    if (useHttpProxy) {
      configBuilder.setProxy(new HttpHost("127.0.0.1", 8123, "http"));
    }

    logger.info("========> useHttpProxy = " + useHttpProxy);

    client = HttpClientBuilder.create()
        .setDefaultRequestConfig(configBuilder.build())
        .build();
  }

  public InstagramMedia parseMedia(String url) {
    HttpGet request = new HttpGet(url);
    String html;
    try {
      HttpResponse response = client.execute(request);
      html = EntityUtils.toString(response.getEntity());
    } catch (IOException e) {
      logger.error("Failed to parse response for url. ", e);
      return null;
    }
    return InstagramMediaFactory.createInstagramMedia(Jsoup.parse(html));
  }
}
