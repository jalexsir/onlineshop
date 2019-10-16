package util;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class EncryptPassw {
    private final static String SALT = "AlexType";

    public static String toHashWithSalt(String password) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(StandardCharsets.UTF_8.encode(password + SALT));
        return String.format("%032x", new BigInteger(md5.digest()));
    }
}
