package com.dlmonkey.util;

import org.junit.Test;

import static com.dlmonkey.util.ValidationUtils.isValidInstagramUrl;
import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author gdong
 */
public class ValidationUtilsTest {

  @Test
  public void testUrlValidation() {
    assertThat(isValidInstagramUrl("http://example.com")).isFalse();

    assertThat(isValidInstagramUrl("http://instagram.com")).isFalse();

    assertThat(isValidInstagramUrl("http://instagram.com/p/BLj_bbaDzta")).isTrue();

    assertThat(isValidInstagramUrl(
        "https://www.instagram.com/p/BLj_bbaDzta/?taken-by=totto.31")).isTrue();
  }
}
