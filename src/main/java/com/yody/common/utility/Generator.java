package com.yody.common.utility;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.Random;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

public class Generator {

  public static String generate() {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 12;
    Random random = new Random();
    String generatedString =
        random
            .ints(leftLimit, rightLimit + 1)
            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
    return generatedString;
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

}
