package lab.panchuk.application.util;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class SHA384Coder {
    private static final String encryptAlg = "SHA-384";

    public static String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance(encryptAlg);
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            StringBuilder hashText = new StringBuilder(no.toString(16));
            while (hashText.length() < 32) {
                hashText.insert(0, "0");
            }
            return hashText.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No such algorithm");
        }
    }
}
