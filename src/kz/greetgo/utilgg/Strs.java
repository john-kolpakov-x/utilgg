package kz.greetgo.utilgg;

import java.util.Iterator;

public class Strs {
  private Strs() {
  }

  public static String upper(String str) {
    if (str == null) return null;
    return str.toUpperCase();
  }

  public static String lower(String str) {
    if (str == null) return null;
    return str.toLowerCase();
  }

  public static String firstLower(String str) {
    if (str == null) return null;
    int length = str.length();
    if (length == 0) return "";
    if (length == 1) return str.toLowerCase();
    return str.substring(0, 1).toLowerCase() + str.substring(1);
  }

  public static String firstUpper(String str) {
    if (str == null) return null;
    int length = str.length();
    if (length == 0) return "";
    if (length == 1) return str.toUpperCase();
    return str.substring(0, 1).toUpperCase() + str.substring(1);
  }

  public static void join(StringBuilder sb, String joiner, Iterable<?> iterable) {
    boolean addJoiner = false;
    for (Object o : iterable) {
      if (addJoiner) sb.append(joiner);
      addJoiner = true;
      sb.append(o);
    }
  }

  private static Iterable<?> arrayAsIterable(final Object[] array) {
    return () -> new Iterator<Object>() {
      int index = 0;
      final int length = array.length;

      @Override
      public boolean hasNext() {
        return index < length;
      }

      @Override
      public Object next() {
        return array[index++];
      }
    };
  }

  public static <T> void join(StringBuilder sb, String joiner, T[] array) {
    join(sb, joiner, arrayAsIterable(array));
  }

  public static String join(String joiner, Iterable<?> iterable) {
    StringBuilder sb = new StringBuilder();
    join(sb, joiner, iterable);
    return sb.toString();
  }

  public static <T> String join(String joiner, T[] array) {
    StringBuilder sb = new StringBuilder();
    join(sb, joiner, array);
    return sb.toString();
  }

}
