package com.dlmonkey.service;

import com.dlmonkey.model.Instagram;
import com.dlmonkey.model.InstagramImage;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

  public InstagramParser() {
    RequestConfig config = RequestConfig.custom()
        .setConnectTimeout(TIMEOUT)
        .setConnectionRequestTimeout(TIMEOUT)
        .setSocketTimeout(TIMEOUT)
        .setProxy(new HttpHost("127.0.0.1", 8123, "http"))
        .build();
    client = HttpClientBuilder.create()
        .setDefaultRequestConfig(config)
        .build();
  }

  public InstagramImage parseImageUrl(String url) {

    HttpGet request = new HttpGet(url);
    String html = null;
    try {
      HttpResponse response = client.execute(request);
      html = EntityUtils.toString(response.getEntity());
    } catch (IOException e) {
      logger.error("Failed to parse response for url. ", e);
      return null;
    }
//    System.out.println(html);

    Document doc = Jsoup.parse(html);
    Elements metaImg = doc.select("meta[property='og:image']");
    InstagramImage instagramImage = new InstagramImage();
    if (!metaImg.isEmpty()) {
      instagramImage.setUrl(metaImg.first().attr("content"));
    }

    Elements metaDesc = doc.select("meta[property='og:description']");
    if (!metaDesc.isEmpty()) {
      instagramImage.setDescription(metaDesc.first().attr("content"));
    }

    return instagramImage;
  }
}
