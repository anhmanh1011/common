package com.yody.common.utility;

import static java.util.Base64.*;

import com.yody.common.enums.ProductEventEnum;
import java.awt.Font;
import java.awt.image.BufferedImage;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import  org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Random;
import java.util.UUID;
import javax.crypto.Mac;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import lombok.val;
import org.apache.commons.lang3.StringUtils;

public class Generator {

  private static final byte[] SALT = "y0dy_Unjc0rn_2O25".getBytes(StandardCharsets.UTF_8);
  private static final Font BARCODE_TEXT_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 12);

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
    Encoder enc = getUrlEncoder().withoutPadding();
    return enc.encodeToString(hash);
  }

  public static String hmac(String key, String data) {
    try {
      val sha256_HMAC = Mac.getInstance("HmacSHA256");
      val secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA256");
      sha256_HMAC.init(secret_key);
      return new String(Base64.encodeBase64(sha256_HMAC.doFinal(data.getBytes("UTF-8")))).trim();
    } catch (Exception e) {
      return null;
    }
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

    public static BufferedImage generate128BarcodeImage(String barcodeText) {
        try {
            Barcode barcode = BarcodeFactory.createCode128(barcodeText);
            barcode.setFont(BARCODE_TEXT_FONT);
            barcode.setResolution(400);
            return BarcodeImageHandler.getImage(barcode);
        } catch (Exception e) {
            return null;
        }
    }

  public static String supplierCode(Integer sequence) {
    return generateCode(sequence, "NCC", 4);
  }

  public static String customerCode(Integer sequence) {
    return generateCode(sequence, "CYD", 10);
  }

  public static String purchaseOrderCode(Integer sequence) {
    return generateCode(sequence, "PYD", 10);
  }

  public static String generateCode(Integer sequence, String prefix, int lengthNumber) {
    int length = lengthNumber - sequence.toString().length();
    StringBuilder r = new StringBuilder(prefix);

    for (int i = 0; i < length; ++i) {
      r.append("0");
    }

    r.append(sequence);
    return r.toString();
  }

  public static String genCode(String prefix, Integer length, Long id) {
    return id.toString().length() >= length ? prefix + id.toString() : prefix + StringUtils.leftPad(id.toString(), length, "0");
  }
}
