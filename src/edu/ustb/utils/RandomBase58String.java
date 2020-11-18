package edu.ustb.utils;

import java.util.Objects;
import java.util.Random;

public class RandomBase58String {

    /**
     * generate a random string
     */
    public String nextString() {
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }

    public static final String alphas = "ABCDEF";

    public static final String digits = "0123456789";

    public static final String alphanum = alphas + digits;

    private final Random random;

    private final char[] symbols;

    private final char[] buf;

    public RandomBase58String(int length, Random random, String symbols) {
        if (length < 1) throw new IllegalArgumentException();
        if (symbols.length() < 2) throw new IllegalArgumentException();
        this.random = Objects.requireNonNull(random);
        this.symbols = symbols.toCharArray();
        this.buf = new char[length];
    }

    /**
     * Create an alphanumberic string generator
     */
    public RandomBase58String(int length, Random random) {
        this(length, random, alphanum);
    }

    /**
     * Create an alphanumeric strings from a sucure generator.
     */
    public RandomBase58String(int length) {
        this(length, new Random());
    }

}
