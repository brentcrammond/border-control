/*
 * The MIT License
 *
 * Copyright (c) 2018. Anameg Consulting Limited
 *
 * Permission is hereby granted, free of charge,
 * to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to
 * deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom
 * the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice
 * shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR
 * ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package nz.h4t.border;

import nz.h4t.border.exceptions.BorderException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BorderTest {
    @Test
    public void checkIsNull() {
        Border.checkIsNull("Test", null);
        try {
            Border.checkIsNull("Test", "Not Null");
        } catch (BorderException ex) {
            assertTrue(ex.getCode().matches("^PortcullisTest[:][0-9]+$"));
        }
    }

    @Test
    public void checkNotNull() {
        Border.checkNotNull("Test", "Not Null");
        try {
            Border.checkIsNull("Test", null);
        } catch (BorderException ex) {
            assertTrue(ex.getCode().matches("^PortcullisTest[:][0-9]+$"));
        }
    }

    @Test
    public void checkEqual() {
        Border.checkEqual("Test", "ABC", "ABC");
        try {
            Border.checkEqual("Test", "ABC", "DEF");
        } catch (BorderException ex) {
            assertTrue(ex.getCode().matches("^PortcullisTest[:][0-9]+$"));
        }
    }

    @Test
    public void checkNotEmpty() {
        Border.checkNotEmpty("Test", "ABC");
        try {
            Border.checkNotEmpty("Test", null);
        } catch (BorderException ex) {
            assertTrue(ex.getCode().matches("^PortcullisTest[:][0-9]+$"));
        }
    }

    @Test
    public void checkNotNullOrEmpty() {
        Border.checkNotNullOrEmpty("Test", "ABC");
        try {
            Border.checkNotNullOrEmpty("Test", null);
        } catch (BorderException ex) {
            assertTrue(ex.getCode().matches("^PortcullisTest[:][0-9]+$"));
        }
        try {
            Border.checkNotNullOrEmpty("Test", "");
        } catch (BorderException ex) {
            assertTrue(ex.getCode().matches("^PortcullisTest[:][0-9]+$"));
        }
    }

    @Test
    public void checkIsTrue() {
        Border.checkIsTrue("Test", true);
        try {
            Border.checkIsTrue("Test", false);
        } catch (BorderException ex) {
            assertTrue(ex.getCode().matches("^PortcullisTest[:][0-9]+$"));
        }
    }

    @Test
    public void checkIsFalse() {
        Border.checkIsFalse("Test", false);
        try {
            Border.checkIsFalse("Test", true);
        } catch (BorderException ex) {
            assertTrue(ex.getCode().matches("^PortcullisTest[:][0-9]+$"));
        }
    }

    @Test
    public void checkTooLong() {
        Border.checkTooLong("Test", "HHH", 3);
        Border.checkTooLong("Test", "HHH", 4);
        try {
            Border.checkTooLong("Test", "HHH", 2);
        } catch (BorderException ex) {
            assertTrue(ex.getCode().matches("^PortcullisTest[:][0-9]+$"));
        }
    }

    @Test
    public void checkTooShort() {
        Border.checkTooShort("Test", "HH", 2);
        Border.checkTooShort("Test", "HHH", 2);
        try {
            Border.checkTooShort("Test", "H", 2);
        } catch (BorderException ex) {
            assertTrue(ex.getCode().matches("^PortcullisTest[:][0-9]+$"));
        }
    }

    @Test
    public void checkPattern() {
        Border.checkPattern("Test", "ABC", "^ABC$");
        try {
            Border.checkPattern("Test", "H", "^ABC$");
        } catch (BorderException ex) {
            assertTrue(ex.getCode().matches("^PortcullisTest[:][0-9]+$"));
        }
    }

    @Test
    public void checkIsBetweenLD() {
        Border.checkIsBetween("Test",
                LocalDate.of(2018, 6, 15),
                LocalDate.of(2018, 6, 10),
                LocalDate.of(2018, 6, 20));
        Border.checkIsBetween("Test",
                LocalDate.of(2018, 6, 10),
                LocalDate.of(2018, 6, 10),
                LocalDate.of(2018, 6, 10));
        try {
            Border.checkIsBetween("Test",
                    LocalDate.of(2018, 6, 15),
                    LocalDate.of(2018, 6, 10),
                    LocalDate.of(2018, 6, 12));
        } catch (BorderException ex) {
            assertTrue(ex.getCode().matches("^PortcullisTest[:][0-9]+$"));
        }
    }

    @Test
    public void checkIsBetweenLDT() {
        Border.checkIsBetween("Test",
                LocalDateTime.of(2018, 6, 15, 0, 0),
                LocalDateTime.of(2018, 6, 10, 0, 0),
                LocalDateTime.of(2018, 6, 20, 0, 0));
        Border.checkIsBetween("Test",
                LocalDateTime.of(2018, 6, 10, 0, 0),
                LocalDateTime.of(2018, 6, 10, 0, 0),
                LocalDateTime.of(2018, 6, 10, 0, 0));
        try {
            Border.checkIsBetween("Test",
                    LocalDateTime.of(2018, 6, 15, 0, 0),
                    LocalDateTime.of(2018, 6, 10, 0, 0),
                    LocalDateTime.of(2018, 6, 12, 0, 0));
        } catch (BorderException ex) {
            assertTrue(ex.getCode().matches("^PortcullisTest[:][0-9]+$"));
        }
    }

    @Test
    public void checkPossibleValues() {
        Border.checkPossibleValues("Test", "ABC", "ABC", "DEF");
        try {
            Border.checkPossibleValues("Test", "XYZ", "ABC", "DEF");
        } catch (BorderException ex) {
            assertTrue(ex.getCode().matches("^PortcullisTest[:][0-9]+$"));
        }
    }
}