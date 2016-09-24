/**
           Copyright 2013, James G. Willmore

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package net.ljcomputing;

import java.util.Locale;

/**
 * A String utility class.
 * 
 * @author James G. Willmore
 * 
 */
public final class StringUtils {

  /** The recognized separators. */
  private final static char[] SEPARATORS = new char[] { '_', '.' };

  /**
   * Instantiates a new string utils.
   */
  private StringUtils() {
  }

  /**
   * <p>Return the camel case representation the incoming String. </p> <p>NOTE:
   * this method will return a camel case representation of the String suitable
   * for various concatenation algorithms. </p> <p>For example, "Yearly Income"
   * becomes "YearlyIncome", which in turn could be used to create another
   * String "getYearlyIncome". </p>
   *
   * @param value the value @return the string
   * @return the string
   */
  public static final String toCamelCase(final String value) {
    return camelCase(value.toLowerCase(Locale.ENGLISH));
  }

  /**
   * <p>Return the camel case representation the incoming String. </p> <p>NOTE:
   * this method will return a camel case representation of the String suitable
   * for various concatenation algorithms. </p> <p>For example, "Yearly Income"
   * becomes "YearlyIncome", which in turn could be used to create another
   * String "getYearlyIncome". </p>
   *
   * @param value the value
   * @return the string
   */
  private static String camelCase(final String value) {
    final StringBuffer buf = new StringBuffer();

    if (value != null && !value.trim().isEmpty()) {
      final char[] chars = value.trim().toCharArray();

      buf.append(String.valueOf(chars[0]).toUpperCase(Locale.ENGLISH));

      for (int i = 1; i < chars.length;) {
        final boolean foundSeparator = isSeparator(chars[i]);

        if (!foundSeparator) {
          buf.append(chars[i]);
        }

        if (foundSeparator && i < chars.length) {
          buf.append(String.valueOf(chars[++i]).toUpperCase(Locale.ENGLISH));
        }

        i++;
      }
    }

    return buf.toString();
  }

  /**
   * Checks if is separator.
   *
   * @param character the character
   * @return true, if is separator
   */
  private static boolean isSeparator(final char character) {
    boolean result = false;

    for (final char check : SEPARATORS) {
      if (check == character) {
        result = true;
        break;
      }
    }

    return result;
  }

  /**
   * <p>Return the member case representation the incoming String. </p> <p>NOTE:
   * this method will return a member case representation of the String suitable
   * for various uses of java.reflect.*. </p> <p>For example, "Yearly Income"
   * becomes "yearlyIncome", which in turn could be used to access a Class'
   * member field called by the same name. </p>
   *
   * @param value the value
   * @return the string
   */
  public static final String toMemberCase(final String value) {
    return memberCase(value.toLowerCase(Locale.ENGLISH));
  }

  /**
   * <p>Return the member case representation the incoming String. </p> <p>NOTE:
   * this method will return a member case representation of the String suitable
   * for various uses of java.reflect.*. </p> <p>For example, "Yearly Income"
   * becomes "yearlyIncome", which in turn could be used to access a Class'
   * member field called by the same name. </p>
   *
   * @param value the value
   * @return the string
   */
  private static String memberCase(final String value) {
    final StringBuffer buf = new StringBuffer();

    buf.append(camelCase(value));
    buf.replace(0, 1, buf.substring(0, 1).toLowerCase(Locale.ENGLISH));

    return buf.toString();
  }
  
  /**
   * Checks if the given String value is null, or is equal to an empty String <i>when trimmed</i>.
   *
   * @param value the value
   * @return true, if is null, or empty when trimmed
   */
  public static final boolean isNullOrEmpty(final String value) {
    return value == null || "".equals(value.trim());
  }
}