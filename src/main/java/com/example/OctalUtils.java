package com.example;

import java.util.ArrayList;
import java.util.List;

public class OctalUtils {
    private static final int BYTE_BITS = 8;
    private static final int DIGIT_BITS_OCTAL = 3;
    private static final char CHAR_ZERO = '0';
    
    /**
     *
     * @param input     Encoded text
     */
    public static List<Integer> decode(String input) {
        // Pre-calculate approximate capacity: each octal character represents 3 bits
        List<Integer> result = new ArrayList<>((input.length() * DIGIT_BITS_OCTAL + 7) / BYTE_BITS);

        // Accumulator for bits
        int bitBuffer = 0;
        // Count of valid bits in buffer
        int bitCount = 0;

        for (char c : input.toCharArray()) {
            int digit = c - CHAR_ZERO;
            if (digit < 0 || digit > 7) {
                throw new IllegalArgumentException(String.format("Invalid octal character: '%c'", c));
            }

            // Add new 3-bit octal digit to buffer
            bitBuffer = (bitBuffer << DIGIT_BITS_OCTAL) | digit;
            bitCount += DIGIT_BITS_OCTAL;

            // Extract complete bytes (8 bits each)
            while (bitCount >= BYTE_BITS) {
                int byteValue = (bitBuffer >> (bitCount - BYTE_BITS)) & 0xFF;
                result.add(byteValue);
                bitCount -= BYTE_BITS;
            }
        }

        return result;
    }

    /**
     *
     * The corresponding to encode function
     * Encode a list of bytes (0-255) into an octal string using 3-bit octal digits.
     */
    public static String encode(List<Integer> input) {
        StringBuilder sb = new StringBuilder(input.size() * DIGIT_BITS_OCTAL);

        int bitBuffer = 0;
        int bitCount = 0;

        for (int b : input) {
            if (b < 0 || b > 255) {
                throw new IllegalArgumentException("Inputs element value out of range: " + b);
            }

            bitBuffer = (bitBuffer << BYTE_BITS) | b;
            bitCount += BYTE_BITS;

            while (bitCount >= DIGIT_BITS_OCTAL) {
                bitCount -= DIGIT_BITS_OCTAL;
                sb.append((bitBuffer >> bitCount) & 0b111);
            }
        }

        if (bitCount > 0) {
            sb.append((bitBuffer << (DIGIT_BITS_OCTAL - bitCount)) & 0b111);
        }

        return sb.toString();
    }
}
