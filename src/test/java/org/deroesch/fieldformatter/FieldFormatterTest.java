package org.deroesch.fieldformatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FieldFormatterTest {

    FieldFormatter ff;

    @BeforeEach
    void setUp() throws Exception {
        ff = new FieldFormatter();
    }

    @Test
    void testDateFormatter() {
        assertEquals("", ff.asDate(""));
        assertEquals("04/19/1959", ff.asDate("04/19/1959"));
        assertEquals("04/19/1959", ff.asDate("04191959"));
        assertEquals("04/19/1959", ff.asDate("04a19b1959"));
        assertEquals("04/19/1959", ff.asDate("x04a19b1959y"));
        assertEquals("", ff.asDate("041919590"));
        assertEquals("", ff.asDate("0419195"));

        assertThrows(NullPointerException.class, () -> ff.asDate(null));
    }

    @Test
    void testSsnFormatter() {
        assertEquals("", ff.asSSN(""));
        assertEquals("123-45-6789", ff.asSSN("123-45-6789"));
        assertEquals("123-45-6789", ff.asSSN("123456789"));
        assertEquals("123-45-6789", ff.asSSN("123a45b6789"));
        assertEquals("123-45-6789", ff.asSSN("x123a45b6789y"));
        assertEquals("", ff.asSSN("123a45b67890"));
        assertEquals("", ff.asSSN("123a45b678"));

        assertThrows(NullPointerException.class, () -> ff.asSSN(null));
    }

    @Test
    void testPhoneFormatter() {
        assertEquals("", ff.asPhone(""));
        assertEquals("123-456-7890", ff.asPhone("123-456-7890"));
        assertEquals("123-456-7890", ff.asPhone("1234567890"));
        assertEquals("123-456-7890", ff.asPhone("123a456b7890"));
        assertEquals("123-456-7890", ff.asPhone("x123a456b7890y"));
        assertEquals("", ff.asPhone("123a456b78900"));
        assertEquals("", ff.asPhone("123a456b789"));

        assertThrows(NullPointerException.class, () -> ff.asPhone(null));
    }

    @Test
    void testFormat() {
        assertEquals("", ff.format("", 0, new TreeSet<Integer>(), ""));
        assertEquals("", ff.format("", -1, new TreeSet<Integer>(), "x"));
        assertThrows(NullPointerException.class, () -> ff.format("", 0, new TreeSet<Integer>(), null));
        assertThrows(NullPointerException.class, () -> ff.format("", 0, null, "x"));
    }

    @Test
    void filterDigits() {
        assertThrows(NullPointerException.class, () -> ff.filterDigits(null));
    }
}
