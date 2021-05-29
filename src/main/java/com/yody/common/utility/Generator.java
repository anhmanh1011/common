package com.yody.common.utility;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Generator {

  private static final byte[] SALT ="y0dy_Unjc0rn_2O25".getBytes(StandardCharsets.UTF_8);

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


  public static String genApiKey(String input) throws NoSuchAlgorithmException, InvalidKeySpecException {
    KeySpec spec = new PBEKeySpec(input.toCharArray(), SALT, 6500, 128); // 6500 tham số cường độ là số lần lặp thuật toán này chạy
    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    byte[] dk = factory.generateSecret(spec).getEncoded();
    byte[] hash = new byte[SALT.length + dk.length];
    System.arraycopy(SALT, 0, hash, 0, SALT.length);
    System.arraycopy(dk, 0, hash, SALT.length, dk.length);
    Base64.Encoder enc = Base64.getUrlEncoder().withoutPadding();
    return enc.encodeToString(hash);
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
