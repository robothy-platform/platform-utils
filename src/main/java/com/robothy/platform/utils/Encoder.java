package com.robothy.platform.utils;

import java.util.Base64;
import java.util.function.Function;

public enum Encoder {

  BASE64(data -> new String(Base64.getEncoder().encode(data))),

  /**
   * Convert bytes to HEX string.
   * One byte to two HEX characters.
   */
  HEX(Encoder::toHex);

  private static final char[] HEX_CHARS =
      {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

  private final Function<byte[], String> encoder;

  private static String toHex(byte[] data) {
    char[] hex = new char[data.length << 1];
    for (int i = 0; i < hex.length; i += 2) {
      byte b = data[i >>> 1];
      hex[i] = HEX_CHARS[(b >>> 4) & 0xf]; // high 4 bits
      hex[i + 1] = HEX_CHARS[b & 0xf]; // low 4 bits.
    }
    return new String(hex);
  }

  /**
   * Encode the given data.
   */
  public String encode(byte[] data) {
    return this.encoder.apply(data);
  }

  Encoder(Function<byte[], String> encoder) {
    this.encoder = encoder;
  }
}