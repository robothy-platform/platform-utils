package com.robothy.platform.utils;

import java.util.Objects;
import java.util.Set;

public class StringUtils {


  /**
   * Convert a string into camel-case.
   *
   * @param input string to convert.
   * @param delimiters delimiters of the string.
   * @return converted camel-case.
   */
  public static String toCamelCase(String input, Character... delimiters) {
    Objects.requireNonNull(input, "The input shouldn't be null.");
    Objects.requireNonNull(delimiters, "You must provide a delimiter.");
    Set<Character> set = Set.of(delimiters);
    StringBuilder result = new StringBuilder();

    boolean preIsDelimiter = false;
    for (int i = 0; i < input.length(); i++) {
      Character ch = input.charAt(i);
      if (set.contains(ch)) {
        preIsDelimiter = true;
      } else {
        if (preIsDelimiter) {
          result.append(Character.toUpperCase(ch));
        } else {
          result.append(ch);
        }
        preIsDelimiter = false;
      }
    }
    return result.toString();
  }

  /**
   * Uppercase the first character of a word.
   *
   * @param word the word to convert.
   * @return converted word.
   */
  public static String upperCaseFirstCharacter(String word) {
    if (Objects.isNull(word) || word.length() == 0) {
      return word;
    }

    return Character.toUpperCase(word.charAt(0)) + word.substring(1);
  }

}
