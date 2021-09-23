package com.yody.common.utility;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.digest.DigestUtils;

public class HashUtils {

  private static final String HMAC_SHA256 = "HmacSHA256";
  private static final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();

  private static final byte[] SALT = "y0dy_Unjc0rn_2O25".getBytes(StandardCharsets.UTF_8);
  private static final byte[] SALT16 = "yody@)@%".getBytes(StandardCharsets.UTF_8);
  private static final String PBE_WITH_MD5_AND_DES = "PBEWithMD5AndDES";
  private static final String AES = "AES";

  private static final String SECRET_KEY = "FzHA#(24Jf/MTO4nF10@<:j94Eeg,vA(";
  private static final String PASSWORD = "R24$11pd94$jtoAnF";

  public static Mac hmacSha1;
  public static Cipher aesEncrypt, aesDecrypt;


  static {
    try {
      hmacSha1 = Mac.getInstance("HmacSHA1");
      hmacSha1.init(new SecretKeySpec(SECRET_KEY.getBytes(), "HmacSHA1"));
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      SecretKeyFactory factory = SecretKeyFactory.getInstance(PBE_WITH_MD5_AND_DES);
      KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT, 65536, 128);
      SecretKey tmp = factory.generateSecret(spec);
      SecretKey secret = new SecretKeySpec(tmp.getEncoded(), AES);

      aesEncrypt = getCipher(AES);
      aesEncrypt.init(Cipher.ENCRYPT_MODE, secret);

      aesDecrypt = getCipher(AES);
      aesDecrypt.init(Cipher.DECRYPT_MODE, secret);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static String md5(String input) {
    return DigestUtils.md5Hex(input).toLowerCase();
  }

  public static String aesEncryptToBase64Url(String s) {
    if (s == null) {
      return null;
    }
    try {
      byte[] encryptedBytes = HashUtils.aesEncrypt.doFinal(s.getBytes());
      return Base64.getUrlEncoder().encodeToString(encryptedBytes);
    } catch (Exception e) {
      return null;
    }
  }

  public static synchronized String aesDecryptFromBase64Url(String s) {
    if (s == null) {
      return null;
    }
    try {
      byte[] encryptedBytes = Base64.getUrlDecoder().decode(s);
      return new String(HashUtils.aesDecrypt.doFinal(encryptedBytes));
    } catch (Exception e) {
      return null;
    }
  }

  public static String hashToSHA256(String s) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    byte[] hashInBytes = md.digest(s.getBytes(StandardCharsets.UTF_8));
    StringBuilder sb = new StringBuilder();
    for (byte b : hashInBytes) {
      sb.append(String.format("%02x", b));

    }
    return sb.toString();
  }

  public static String hashLogData(String publicKey, String data) {
    try {
      byte[] byteKey = Base64.getDecoder().decode(publicKey);
      Mac sha256HMAC = Mac.getInstance(HMAC_SHA256);
      SecretKeySpec secretKey = new SecretKeySpec(byteKey, HMAC_SHA256);
      sha256HMAC.init(secretKey);
      byte[] hashing = sha256HMAC.doFinal(data.getBytes());
      return Base64.getEncoder().encodeToString(toStringHex(hashing).getBytes());
    } catch (Exception e) {
      return null;
    }
  }

  private static String toStringHex(byte[] bytes) {
    StringBuilder sb = new StringBuilder(2 * bytes.length);
    for (byte b : bytes) {
      sb.append(HEX_DIGITS[(b >> 4) & 0xf]).append(HEX_DIGITS[b & 0xf]);
    }
    return sb.toString();
  }

  public static String hashSHA512(String toBeHashed) {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-512");
      byte[] bytes = md.digest(toBeHashed.getBytes(StandardCharsets.UTF_8));
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < bytes.length; i++) {
        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
      }
      return sb.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String encrypt(String property)
      throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
    SecretKey key = getSecretKey();
    Cipher pbeCipher = getCipher(PBE_WITH_MD5_AND_DES);
    pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT16, 20));
    return base64Encode(pbeCipher.doFinal(property.getBytes(StandardCharsets.UTF_8)));
  }

  private static Cipher getCipher(String algorithm)
      throws NoSuchAlgorithmException, NoSuchPaddingException {
    return Cipher.getInstance(algorithm);
  }

  public static String decrypt(String property)
      throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
    SecretKey key = getSecretKey();
    Cipher pbeCipher = getCipher(PBE_WITH_MD5_AND_DES);
    pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(SALT16, 20));
    return new String(pbeCipher.doFinal(base64Decode(property)), StandardCharsets.UTF_8);
  }

  private static SecretKey getSecretKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(PBE_WITH_MD5_AND_DES);
    return keyFactory.generateSecret(new PBEKeySpec(PASSWORD.toCharArray()));
  }

  private static String base64Encode(byte[] bytes) {
    return Base64.getEncoder().encodeToString(bytes);
  }

  private static byte[] base64Decode(String property) {
    return Base64.getDecoder().decode(property);
  }
}
