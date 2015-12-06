package kz.greetgo.utilgg.security;

import java.security.Provider;
import java.security.Security;

public class ListOfSecurityProbe {
  public static void main(String[] args) {
    for (Provider provider : Security.getProviders()) {
      System.out.println("===============================================================");
      System.out.println("[PROVIDER: " + provider + "]");
      System.out.println();
      for (Provider.Service service : provider.getServices()) {
        System.out.println("SERVICE: " + service);
      }
    }
  }
}
