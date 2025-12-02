package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class OctalUtilsTest {

    @Test
    void testBasicDecode() {
        String input = "31646541";
        List<Integer> expected = List.of(103, 77, 97);
        List<Integer> actual = OctalUtils.decode(input);
        assertEquals(expected, actual, "Basic decode failed");
    }

    @Test
    void testLongInput() {
        String input =
                "2277114742311135167021343424004141432061264036716605455" +
                        "35070012425145143366515462107042711115720106717127670062" +
                        "71704657770433346073017047360217626325467150763006577133" +
                        "54152655466766041402716542311111131505761476052650004524" +
                        "21616177052165224543311447543654741617367042213645643631" +
                        "33346575330621635642541636644326535501666004333326756424" +
                        "47003252221104064117622317044717471111";

        List<Integer> result = OctalUtils.decode(input);

        // Basic sanity checks
        assertNotNull(result, "Result should not be null");
        assertFalse(result.isEmpty(), "Result should not be empty");

        // Every decoded value must be a valid byte
        for (int val : result) {
            assertTrue(val >= 0 && val <= 255, "Decoded byte out of range: " + val);
        }

        // Optional: print size for verification
        System.out.println("Decoded bytes count: " + result.size());
    }

    @Test
    void testEncodePositive() {
        List<Integer> input = List.of(103, 77, 97);
        String expected = "31646541";
        String actual = OctalUtils.encode(input);
        assertEquals(expected, actual, "Decode failed");
    }

    // todo: add more negative or invalid input test cases.
}
