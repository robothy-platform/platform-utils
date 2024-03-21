package com.robothy.platform.utils.comcurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamingThreadFactory implements ThreadFactory {

  private final AtomicInteger threadNumber = new AtomicInteger(0);

  private final String namePrefix;

  public NamingThreadFactory(String namePrefix) {
    this.namePrefix = namePrefix;
  }

  @Override
  public Thread newThread(Runnable r) {
    return new Thread(r, namePrefix + "-" + threadNumber.getAndIncrement());
  }
}
