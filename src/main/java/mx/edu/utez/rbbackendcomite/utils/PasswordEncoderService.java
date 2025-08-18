// PasswordEncoderService.java
package mx.edu.utez.rbbackendcomite.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderService {

    public static String encodePassword(String rawPassword){  // Fixed method name
        return new BCryptPasswordEncoder().encode(rawPassword);
    }

    public static boolean verifyPassword(String rawPassword, String encodedPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
    }
}