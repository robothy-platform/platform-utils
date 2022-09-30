package com.robothy.platform.utils;

import java.util.UUID;

public class UUIDGenerator {

  public static String next() {
    return UUID.randomUUID().toString();
  }

}
