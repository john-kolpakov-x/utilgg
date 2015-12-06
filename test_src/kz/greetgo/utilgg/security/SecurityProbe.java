package kz.greetgo.utilgg.security;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.util.Base64;

public class SecurityProbe {


  public static void main(String[] args) throws Exception {

    KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");

    kpg.initialize(1024, SecureRandom.getInstance("NativePRNG", "SUN"));

    KeyPair keyPair = kpg.generateKeyPair();

    Cipher cipherEncrypt = Cipher.getInstance("RSA");
    cipherEncrypt.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());

    //String str = Strs.streamToStr(SecurityProbe.class.getResourceAsStream("security_list.txt"));
    String str = "Привет всем!!!";

    byte[] ciphered = cipherEncrypt.doFinal(str.getBytes("UTF-8"));

    String cipheredStr = Base64.getEncoder().encodeToString(ciphered);
    System.out.println("cipheredStr : " + cipheredStr);

    byte[] ciphered2 = Base64.getDecoder().decode(cipheredStr);

    Cipher cipherDecrypt = Cipher.getInstance("RSA");
    cipherDecrypt.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());

    byte[] str2InBytes = cipherDecrypt.doFinal(ciphered2);

    String str2 = new String(str2InBytes, "UTF-8");

    System.out.println("str2 = " + str2);
  }
}
