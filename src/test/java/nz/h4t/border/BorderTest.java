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
import nz.h4t.border.exceptions.BorderInvalidEmailException;
import nz.h4t.border.exceptions.BorderIsNullException;
import nz.h4t.border.exceptions.BorderNotNullException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class BorderTest {
    @Test
    public void checkIsNull() {
        Border.checkIsNull("Test", null);
        try {
            Border.checkIsNull("Test", "Not Null");
            fail();
        } catch (BorderNotNullException ex) {
            assertTrue(ex.getCode().matches("^BorderTest[:][0-9]+$"));
        }
    }

    @Test
    public void checkNotNull() {
        Border.checkNotNull("Test", "Not Null");
        try {
            Border.checkNotNull("Test", null);
            fail();
        } catch (BorderIsNullException ex) {
            assertTrue(ex.getCode().matches("^BorderTest[:][0-9]+$"));
        }
    }

    @Test
    public void checkEqual() {
        Border.checkEqual("Test", "ABC", "ABC");
        try {
            Border.checkEqual("Test", "ABC", "DEF");
            fail();
        } catch (BorderException ex) {
            assertTrue(ex.getCode().matches("^BorderTest[:][0-9]+$"));
        }
    }

    @Test
    public void checkNotEmpty() {
        Border.checkNotEmpty("Test", "ABC");
        try {
            Border.checkNotEmpty("Test", null);
            fail();
        } catch (BorderException ex) {
            assertTrue(ex.getCode().matches("^BorderTest[:][0-9]+$"));
        }
    }

    @Test
    public void checkNotNullOrEmpty() {
        Border.checkNotNullOrEmpty("Test", "ABC");
        try {
            Border.checkNotNullOrEmpty("Test", null);
            fail();
        } catch (BorderException ex) {
            assertTrue(ex.getCode().matches("^BorderTest[:][0-9]+$"));
        }
        try {
            Border.checkNotNullOrEmpty("Test", "");
            fail();
        } catch (BorderException ex) {
            assertTrue(ex.getCode().matches("^BorderTest[:][0-9]+$"));
        }
    }

    @Test
    public void checkIsTrue() {
        Border.checkIsTrue("Test", true);
        try {
            Border.checkIsTrue("Test", false);
            fail();
        } catch (BorderException ex) {
            assertTrue(ex.getCode().matches("^BorderTest[:][0-9]+$"));
        }
    }

    @Test
    public void checkIsFalse() {
        Border.checkIsFalse("Test", false);
        try {
            Border.checkIsFalse("Test", true);
            fail();
        } catch (BorderException ex) {
            assertTrue(ex.getCode().matches("^BorderTest[:][0-9]+$"));
        }
    }

    @Test
    public void checkTooLong() {
        Border.checkTooLong("Test", "HHH", 3);
        Border.checkTooLong("Test", "HHH", 4);
        try {
            Border.checkTooLong("Test", "HHH", 2);
            fail();
        } catch (BorderException ex) {
            assertTrue(ex.getCode().matches("^BorderTest[:][0-9]+$"));
        }
    }

    @Test
    public void checkTooShort() {
        Border.checkTooShort("Test", "HH", 2);
        Border.checkTooShort("Test", "HHH", 2);
        try {
            Border.checkTooShort("Test", "H", 2);
            fail();
        } catch (BorderException ex) {
            assertTrue(ex.getCode().matches("^BorderTest[:][0-9]+$"));
        }
    }

    @Test
    public void checkPattern() {
        Border.checkPattern("Test", "ABC", "^ABC$");
        try {
            Border.checkPattern("Test", "H", "^ABC$");
            fail();
        } catch (BorderException ex) {
            assertTrue(ex.getCode().matches("^BorderTest[:][0-9]+$"));
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
            fail();
        } catch (BorderException ex) {
            assertTrue(ex.getCode().matches("^BorderTest[:][0-9]+$"));
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
            fail();
        } catch (BorderException ex) {
            assertTrue(ex.getCode().matches("^BorderTest[:][0-9]+$"));
        }
    }

    @Test
    public void checkPossibleValues() {
        Border.checkPossibleValues("Test", "ABC", "ABC", "DEF");
        try {
            Border.checkPossibleValues("Test", "XYZ", "ABC", "DEF");
            fail();
        } catch (BorderException ex) {
            assertTrue(ex.getCode().matches("^BorderTest[:][0-9]+$"));
        }
    }

    @Test
    public void checkInCollection() {
        Set<String> vals = new HashSet<>();
        vals.add("ABC");
        vals.add("DEF");
        Border.checkPossibleValues("Test", "ABC", vals);
        try {
            Border.checkPossibleValues("Test", "XYZ", vals);
            fail();
        } catch (BorderException ex) {
            assertTrue(ex.getCode().matches("^BorderTest[:][0-9]+$"));
        }
    }

    @Test
    public void checkValidEmail() {
        Border.checkValidEmail("Test", "joebloggs@abc.com");
        Border.checkValidEmail("Test", "joe_bloggs@abc.co.nz");
        Border.checkValidEmail("Test", "joe-bloggs@abc.co.nz");
        Border.checkValidEmail("Test", "joe.bloggs@abc.co.nz");
        try {
            Border.checkValidEmail("Test", null);
            fail();
        } catch (BorderIsNullException ex) {
            assertTrue(ex.getCode().matches("^BorderTest[:][0-9]+$"));
        }
        try {
            Border.checkValidEmail("Test", "InvalidEmailAddress");
            fail();
        } catch (BorderInvalidEmailException ex) {
            assertTrue(ex.getCode().matches("^BorderTest[:][0-9]+$"));
        }
        try {
            Border.checkValidEmail("Test", "Invalid Email Address@abc.com");
            fail();
        } catch (BorderInvalidEmailException ex) {
            assertTrue(ex.getCode().matches("^BorderTest[:][0-9]+$"));
        }
    }
}
