package com.yody.common.utility;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public final class Files {

  /**
   *
   */
  public static String getName(File f) {
    return f == null ? null : f.getName();
  }

  public static boolean exists(String path) {
    return new File(path).exists();
  }

  public static byte[] read(final File file) throws IOException {
    return FileUtils.readFileToByteArray(file);
  }

  /**
   *
   */
  public static boolean mkdir(final String path) {
    final File d = new File(path);
    return d.exists() ? true : d.mkdir();
  }

  public static boolean mkdirs(final String path) {
    final File d = new File(path);
    return d.exists() ? true : d.mkdirs();
  }

  /**
   * @throws IOException
   */
  public static boolean rename(File src, String dst) throws IOException {
    if (!src.exists() && !src.createNewFile()) {
      throw new IOException("cannot find/create source file");
    }
    File dstFile = new File(dst);
    if (dstFile.exists()) {
      throw new IOException("destination file exists");
    }
    return src.renameTo(dstFile);
  }

  public static boolean rename(String src, String dst) throws IOException {
    return rename(new File(src), dst);
  }
}
