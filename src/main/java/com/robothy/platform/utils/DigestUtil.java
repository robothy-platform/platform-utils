package com.robothy.platform.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtil {

  /**
   * Digest input data with SHA-256.
   */
  public static String sha256AsHex(byte[] input) {
    return digest("SHA-256", Encoder.HEX, input);
  }

  public static String md5AsHex(byte[] input) {
    return digest("MD5", Encoder.HEX, input);
  }

  /**
   * Digest the data from {@linkplain InputStream}.
   */
  public static String digest(String algorithm, Encoder encoder, InputStream input) throws IOException {
    MessageDigest messageDigest = getMessageDigest(algorithm);
    byte[] buf = new byte[1024];
    int len;
    while ((len = input.read(buf)) != -1) {
      messageDigest.update(buf, 0, len);
    }
    return encoder.encode(messageDigest.digest());
  }

  /**
   * Digest the input data with specified algorithm.
   *
   * @param algorithm digest algorithm name.
   * @param input     input data.
   * @return digest of input data.
   */
  public static String digest(String algorithm, Encoder encoder, byte[] input) {
    return encoder.encode(getMessageDigest(algorithm).digest(input));
  }

  private static MessageDigest getMessageDigest(String algorithm) {
    try {
      // The instance is not thread-safe; need to get a new instance everytime.
      return MessageDigest.getInstance(algorithm);
    } catch (NoSuchAlgorithmException ex) {
      throw new IllegalStateException("Could not find MessageDigest with algorithm '" + algorithm + "'.", ex);
    }
  }

}
