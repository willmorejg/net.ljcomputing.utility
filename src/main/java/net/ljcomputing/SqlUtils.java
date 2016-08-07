/**
           Copyright 2016, James G. Willmore

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

/**
 * Implementation of a utility class to create SQL statements 
 * that can be used with JDBC implementation.
 * 
 * @author James G. Willmore
 *
 */
public final class SqlUtils {

  /** The Constant AT_LEAST_ONE - suggested by PMD plugin. */
  private final static int AT_LEAST_ONE = 1;

  /**
   * Builds the columns values used in various SQL statements based upon the given columns.
   *
   * @param columns the columns
   * @return the string
   */
  private static String buildColumns(final String... columns) {
    final StringBuffer buf = new StringBuffer();

    for (String column : columns) {
      buf.append(column).append(',');
    }

    if (buf.length() > AT_LEAST_ONE) {
      buf.reverse().deleteCharAt(0).reverse();
    }

    return buf.toString();
  }

  /**
   * Builds the place holders used in a PreparedStatement based upon the given columns.
   *
   * @param columns the columns
   * @return the string
   */
  private static String buildPlaceholders(final String... columns) {
    final StringBuffer buf = new StringBuffer();

    for (@SuppressWarnings("unused")
    String column : columns) {
      buf.append("?,");
    }

    if (buf.length() > AT_LEAST_ONE) {
      buf.reverse().deleteCharAt(0).reverse();
    }

    return buf.toString();
  }

  /**
   * Builds the SET column values; part of an UPDATE SQL statement.
   *
   * @param columns the columns
   * @return the string
   */
  private static String buildSetColumnValues(final String... columns) {
    final StringBuffer buf = new StringBuffer();

    for (String column : columns) {
      buf.append(column).append("=?,");
    }

    if (buf.length() > AT_LEAST_ONE) {
      buf.reverse().deleteCharAt(0).reverse();
    }

    return buf.toString();
  }

  /**
   * Builds the INSERT SQL statement used in a PreparedStatement.
   *
   * @param table the table
   * @param columns the columns
   * @return the string
   */
  public static String buildInsertStatement(final String table,
      final String... columns) {
    final StringBuffer buf = new StringBuffer("insert into ");

    buf.append(table).append('(').append(buildColumns(columns)).append(')')
        .append(" values(").append(buildPlaceholders(columns)).append(')');

    return buf.toString();
  }

  /**
   * Builds the UPDATE SQL statement used in a PreparedStatement.
   *
   * @param table the table
   * @param primaryKeyColumn the primary key column
   * @param columns the columns
   * @return the string
   */
  public static String buildUpdateStatement(final String table,
      final String primaryKeyColumn, final String... columns) {
    final StringBuffer buf = new StringBuffer("update ");

    buf.append(table).append(" set ").append(buildSetColumnValues(columns))
        .append(" where ").append(buildSetColumnValues(primaryKeyColumn));

    return buf.toString();
  }

  /**
   * Builds the DELETE SQL statement used in a PreparedStatement.
   *
   * @param table the table
   * @param primaryKeyColumn the primary key column
   * @return the string
   */
  public static String buildDeleteStatement(final String table,
      final String primaryKeyColumn) {
    final StringBuffer buf = new StringBuffer("delete from ");

    buf.append(table).append(" where ")
        .append(buildSetColumnValues(primaryKeyColumn));

    return buf.toString();
  }

}
