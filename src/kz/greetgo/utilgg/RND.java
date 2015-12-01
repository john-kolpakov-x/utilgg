package kz.greetgo.utilgg;

import java.util.Random;

public class RND {

  public static final String eng = "abcdefghijklmnopqrstuvwxyz";
  public static final String ENG = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  public static final String rus = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
  public static final String RUS = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
  public static final String DIG = "0123456789";
  public static final String Eng = ENG + eng;
  public static final String Rus = RUS + rus;
  public static final String Eng_Rus = Eng + Rus;
  public static final String DIG_Eng_Rus = DIG + Eng_Rus;

  public static final Random RND_FAST = new Random();

  public static String str(int len) {
    char ret[] = new char[len];
    final String cc = DIG_Eng_Rus;
    for (int i = 0; i < len; i++) {
      ret[i] = cc.charAt(RND_FAST.nextInt(cc.length()));
    }
    return new String(ret);
  }

  public static boolean bool() {
    return RND_FAST.nextBoolean();
  }

}
