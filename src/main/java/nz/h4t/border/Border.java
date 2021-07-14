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

import nz.h4t.border.exceptions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;

/**
 * Portcullis is a Pre-condition Library that allows us to throw
 * Runtime Exceptions if certain conditions are not met.
 */
public class Border {
    private static final Pattern VALID_EMAIL_ADDRESS = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    /**
     * checkIsNull checks that the object provided is not null
     *
     * @param mesg Error Message
     * @param obj  test object
     */
    public static void checkIsNull(String mesg, Object obj) {
        if (obj != null) {
            throw new BorderNotNullException(findReference(), mesg);
        }
    }

    /**
     * Checks that the test object is not null
     *
     * @param mesg Error Message
     * @param obj  test object
     */
    public static void checkNotNull(String mesg, Object obj) {
        if (obj == null) {
            throw new BorderIsNullException(findReference(), mesg);
        }
    }

    /**
     * Checks that obj1 and obj2 are equal
     *
     * @param mesg Error Message
     * @param obj1 test object1
     * @param obj2 test object2
     */
    public static void checkEqual(String mesg, Object obj1, Object obj2) {
        if (obj1 == null && obj2 != null) {
            throw new BorderNotEqualException(findReference(), mesg);
        }
        if (obj1 != null && obj2 == null) {
            throw new BorderNotEqualException(findReference(), mesg);
        }
        if (!obj1.equals(obj2)) {
            throw new BorderNotEqualException(findReference(), mesg);
        }
    }

    /**
     * Checks that str is not empty
     *
     * @param mesg Error Message
     * @param str  String being tested
     */
    public static void checkNotEmpty(String mesg, String str) {
        if (str == null) {
            throw new BorderIsEmptyException(findReference(), mesg);
        }
        if (str == null || "".equals(str)) {
            throw new BorderIsEmptyException(findReference(), mesg);
        }
    }

    /**
     * Checks not null or empty
     *
     * @param mesg Error Message
     * @param str  String being tested
     */
    public static void checkNotNullOrEmpty(String mesg, String str) {
        if (str == null) {
            throw new BorderIsNullException(findReference(), mesg);
        }
        if (str == null || "".equals(str)) {
            throw new BorderIsEmptyException(findReference(), mesg);
        }
    }

    /**
     * Check  that the state is true
     *
     * @param mesg  Error Message
     * @param state State being tested as true
     */
    public static void checkIsTrue(String mesg, boolean state) {
        if (!state) {
            throw new BorderIsFalseException(findReference(), mesg);
        }
    }

    /**
     * Check  that the state is false
     *
     * @param mesg  Error Message
     * @param state State being tested as false
     */
    public static void checkIsFalse(String mesg, boolean state) {
        if (state) {
            throw new BorderIsTrueException(findReference(), mesg);
        }
    }

    /**
     * Checks that the string is not greater than the max length
     *
     * @param mesg   Error Message
     * @param str    String being tested
     * @param maxLen Max length of the string
     */
    public static void checkTooLong(String mesg, String str, int maxLen) {
        if (str == null) {
            throw new BorderIsNullException(findReference(), mesg);
        }
        if (str.length() > maxLen) {
            throw new BorderTooLongException(findReference(), mesg);
        }
    }

    /**
     * Checks that the string is not less than the min length
     *
     * @param mesg   Error Message
     * @param str    String being tested
     * @param minLen Min length of the string
     */
    public static void checkTooShort(String mesg, String str, int minLen) {
        if (str == null) {
            throw new BorderIsNullException(findReference(), mesg);
        }
        if (str.length() < minLen) {
            throw new BorderTooShortException(findReference(), mesg);
        }
    }

    /**
     * Checks that the string matches the pattern provided
     *
     * @param mesg    Error Message
     * @param str     String being tested
     * @param pattern Pattern being matched
     */
    public static void checkPattern(String mesg, String str, String pattern) {
        if (str == null) {
            throw new BorderIsNullException(findReference(), mesg);
        }
        Pattern pat = Pattern.compile(pattern);
        if (!pat.matcher(str).matches()) {
            throw new BorderPatternException(findReference(), mesg);
        }
    }

    /**
     * Check that the sample date is between the specified dates
     *
     * @param mesg     Error Message
     * @param dt       LocalDate being tested
     * @param fromDate From Date
     * @param toDate   To Date
     */
    public static void checkIsBetween(String mesg, LocalDate dt, LocalDate fromDate, LocalDate toDate) {
        if (dt == null) {
            throw new BorderIsNullException(findReference(), mesg);
        }
        if (fromDate != null && dt.isBefore(fromDate)) {
            throw new BorderInvalidDateException(findReference(), mesg);
        }
        if (toDate != null && dt.isAfter(toDate)) {
            throw new BorderInvalidDateException(findReference(), mesg);
        }
    }

    /**
     * Check that the sample date is between the specified dates
     *
     * @param mesg     Error Message
     * @param dt       LocalDate being tested
     * @param fromDate From Date
     * @param toDate   To Date
     */
    public static void checkIsBetween(String mesg, LocalDateTime dt, LocalDateTime fromDate, LocalDateTime toDate) {
        if (dt == null) {
            throw new BorderIsNullException(findReference(), mesg);
        }
        if (fromDate != null && dt.isBefore(fromDate)) {
            throw new BorderInvalidDateException(findReference(), mesg);
        }
        if (toDate != null && dt.isAfter(toDate)) {
            throw new BorderInvalidDateException(findReference(), mesg);
        }
    }

    /**
     * Checks whether the sample value is one of the possible values.
     *
     * @param mesg         Error Message
     * @param obj          Sample be tested
     * @param possibleVals Possible values that the sample could be
     */
    public static void checkPossibleValues(String mesg, Object obj, Object... possibleVals) {
        if (obj == null) {
            throw new BorderIsNullException(findReference(), mesg);
        }
        if (possibleVals.length == 0) {
            throw new BorderNoMatchException(findReference(), mesg);
        }
        boolean found = Arrays.stream(possibleVals)
                .anyMatch(obj::equals);
        if (!found) {
            throw new BorderNoMatchException(findReference(), mesg);
        }
    }

    /**
     * Checks whether the sample value is one of the possible values.
     *
     * @param mesg         Error Message
     * @param obj          Sample be tested
     * @param possibleVals Possible values that the sample could be
     */
    public static void checkPossibleValues(String mesg, Object obj, Collection<Object> possibleVals) {
        if (obj == null) {
            throw new BorderIsNullException(findReference(), mesg);
        }
        if (possibleVals.size() == 0) {
            throw new BorderNoMatchException(findReference(), mesg);
        }
        boolean found = possibleVals
                .stream()
                .anyMatch(obj::equals);
        if (!found) {
            throw new BorderNoMatchException(findReference(), mesg);
        }
    }

    /**
     * Checks that the test object is not null
     *
     * @param mesg         Error Message
     * @param emailAddress test email address
     */
    public static void checkValidEmail(String mesg, String emailAddress) {
        if (emailAddress == null) {
            throw new BorderIsNullException(findReference(), mesg);
        }
        if (!VALID_EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            throw new BorderInvalidEmailException(findReference(), mesg);
        }
    }

    //
    // Internal Methods...
    //

    private static String findReference() {
        StackTraceElement[] ste = new Exception().getStackTrace();
        if (ste.length > 2) {
            String clzName = ste[2].getClassName();
            int lno = ste[2].getLineNumber();
            if (lno >= 0) {
                return clzName.substring(clzName.lastIndexOf(".") + 1).trim() + ":" + lno;
            } else {
                return clzName.substring(clzName.lastIndexOf(".") + 1).trim();
            }
        } else {
            return "-";
        }
    }
}
