package com.example.topcv.common.util;

import org.apache.commons.lang3.RandomStringUtils;

public class Utils {

  /** Hàm tạo mã xác thực */
  public static String createVerifyCode() {
    String upperCaseLetters = RandomStringUtils.random(2, 65, 90, true, true);
    String lowerCaseLetters = RandomStringUtils.random(2, 97, 122, true, true);
    String numbers = RandomStringUtils.randomNumeric(2);
    String specialChar = RandomStringUtils.random(2, 33, 47, false, false);
    return upperCaseLetters.concat(lowerCaseLetters).concat(numbers).concat(specialChar);
  }
}
