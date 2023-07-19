package it.academy.utils.constants;

public class DataEncrypt {
    public static final String SECURE_ALG = "SHA1PRNG";

    public static final String ALGORITHM = "PBKDF2WithHmacSHA1";

    public static final int KEY_LENGTH = 128;

    public static final int RADIX = 16;

    public static final int SALT_LENGTH = 16;

    public static final int ITERATIONS = 10000;

    public static final int SIGNUM = 1;

    public static final String BEGIN_HEX = "%0";

    public static final String END_HEX = "d";
}