package com.example.demo.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncoder {
  // private static final String SALT = "sf2342.sdaf234F11";

  public static String encode(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt());
  }

  public static boolean check(String password, String encodedPassword) {
    return BCrypt.checkpw(password, encodedPassword);
  }
}
