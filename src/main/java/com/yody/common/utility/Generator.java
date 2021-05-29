package com.yody.common.utility;

import java.util.Random;
import java.util.UUID;

public class Generator {

  public static String generate() {

    return gen(12);
  }

  private static String gen(int length) {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    Random random = new Random();
    String generatedString = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(length)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    return generatedString;
  }


  public static String genApiKey() {
    String key = gen(30);
    Random random = new Random();
    String[] specialCharacter = {"-", "+", "="};
    for(int i=0;i<3;i++) {
      int n = random.nextInt();
      while (n <= 0) {
        n = random.nextInt();
      }
      String c = specialCharacter[n % specialCharacter.length];
      int x = n % key.length();
      key = key.substring(0, x).concat(c).concat(key.substring(x));
    }
    return key;
  }


  public static String barcode(Long sequence) {
    int length = 13 - 2 - sequence.toString().length();
    StringBuilder r = new StringBuilder("29");
    for (int i = 0; i < length; i++) {
      r.append("0");
    }
    r.append(sequence);
    return r.toString();
  }

  //  public static BufferedImage generateEAN13BarcodeImage(String barcodeText) throws Exception {
//    Barcode barcode = BarcodeFactory.createEAN13(barcodeText);
//    return BarcodeImageHandler.getImage(barcode);
//  }
//
  public static String supplierCode(Integer sequence) {
    int length = 4 - sequence.toString().length();
    StringBuilder r = new StringBuilder("NCC");

    for (int i = 0; i < length; ++i) {
      r.append("0");
    }

    r.append(sequence);
    return r.toString();
  }
}
