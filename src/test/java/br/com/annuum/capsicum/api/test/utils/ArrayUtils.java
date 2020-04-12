package br.com.annuum.capsicum.api.test.utils;

import static org.apache.commons.lang3.RandomUtils.nextInt;

public final class ArrayUtils {

  public static <T> T random(final T[] collection) {
    return collection[nextInt(0, collection.length)];
  }

}
