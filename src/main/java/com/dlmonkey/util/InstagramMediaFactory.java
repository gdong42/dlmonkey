package com.dlmonkey.util;

import com.dlmonkey.model.InstagramImage;
import com.dlmonkey.model.InstagramMedia;
import com.dlmonkey.model.InstagramVideo;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * @author gdong
 */
public class InstagramMediaFactory {

  private static final Logger logger = LoggerFactory
      .getLogger(InstagramMediaFactory.class);

  private static final String INSTAGRAM_TYPE_PHOTO = "instapp:photo";

  private static final String INSTAGRAM_TYPE_VIDEO = "video";

  /**
   * Creates appropriate InstagramMedia instance according to the given document
   *
   * @param document the input document
   * @return the parsed InstagramMedia instance, or <code>null</code> if nothing
   * can be parsed
   */
  public static InstagramMedia createInstagramMedia(Document document) {

    String type = parseMetaProperty("og:type", document);
    if (type == null) {
      return null;
    }
    if (INSTAGRAM_TYPE_PHOTO.equals(type)) {
      return parseImage(document);
    } else if (INSTAGRAM_TYPE_VIDEO.equals(type)) {
      return parseVideo(document);
    }
    logger.warn("Unknown type: " + type);
    return null;
  }

  private static InstagramMedia parseVideo(Document document) {
    InstagramVideo video = new InstagramVideo();
    video.setUrl(parseMetaProperty("og:video", document));
    video.setPosterUrl(parseMetaProperty("og:image", document));
    video.setType(parseMetaProperty("og:video:type", document));
    video.setWidth(parseMetaProperty("og:video:width", document));
    video.setHeight(parseMetaProperty("og:video:height", document));
    video.setSecureUrl(parseMetaProperty("og:video:secure_url", document));
    video.setDescription(parseMetaProperty("og:description", document));
    return video;
  }

  private static InstagramMedia parseImage(Document document) {
    InstagramImage image = new InstagramImage();
    image.setUrl(parseMetaProperty("og:image", document));
    image.setDescription(parseMetaProperty("og:description", document));
    return image;
  }

  /**
   * gets property value of the given property, parsing from the given document
   *
   * @param property the property attribute value of meta
   * @param document the jsoup document to parse from
   * @return parsed content, if any
   */
  public static String parseMetaProperty(String property, Document document) {

    if (StringUtils.isEmpty(property) || document == null) {
      throw new IllegalArgumentException(
          "property name and document cannot be null");
    }
    Elements metaElems = document
        .select(String.format("meta[property='%s']", property));
    return metaElems.isEmpty() ? null : metaElems.first().attr("content");
  }
}
