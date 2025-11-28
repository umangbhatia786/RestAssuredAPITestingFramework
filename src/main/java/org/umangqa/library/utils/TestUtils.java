package org.umangqa.library.utils;

import java.util.Random;

public class TestUtils {
    private static final String ALPHA_NUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALPHA_NUMERIC.charAt(random.nextInt(ALPHA_NUMERIC.length())));
        }

        return sb.toString();
    }

    public static String generateRandomNumberString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public static String randomNonZeroNumberString(int len) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder(len);

        for (int i = 0; i < len; i++) {
            int digit = r.nextInt(10);

            // First digit must not be 0
            if (i == 0 && digit == 0) {
                digit = r.nextInt(9) + 1; // 1-9
            }

            sb.append(digit);
        }
        return sb.toString();
    }

}
