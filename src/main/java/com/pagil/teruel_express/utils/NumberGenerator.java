package com.pagil.teruel_express.utils;
import java.security.SecureRandom;

public class NumberGenerator {

    /**
     * Generate a 6-digit number for methods like forgot password
     * @return string code
     */
    public static String generateNumberConfirmation() {
        SecureRandom random = new SecureRandom();

        int numero = random.nextInt(900000) + 100000;

        return String.valueOf(numero);
    }

}

