package kz.greetgo.utilgg;

public class Strs {
  private Strs() {
  }

  public static final String upper(String str) {
    if (str == null) return null;
    return str.toUpperCase();
  }

  public static final String lower(String str) {
    if (str == null) return null;
    return str.toLowerCase();
  }

  public static final String firstLower(String str) {
    if (str == null) return null;
    int length = str.length();
    if (length == 0) return "";
    if (length == 1) return str.toLowerCase();
    return str.substring(0, 1).toLowerCase() + str.substring(1);
  }

  public static final String firstUpper(String str) {
    if (str == null) return null;
    int length = str.length();
    if (length == 0) return "";
    if (length == 1) return str.toUpperCase();
    return str.substring(0, 1).toUpperCase() + str.substring(1);
  }
}
