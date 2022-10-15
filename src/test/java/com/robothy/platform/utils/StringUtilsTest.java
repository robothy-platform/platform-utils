package com.robothy.platform.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class StringUtilsTest {

  @ParameterizedTest
  @MethodSource("toCamelCaseTestCase")
  void toCamelCase(String input, Character[] delimiters, String expect) {
    assertEquals(expect, StringUtils.toCamelCase(input, delimiters));
  }

  static Stream<Arguments> toCamelCaseTestCase() {
    return Stream.of(
        arguments("hello_world", new Character[] {'_'}, "helloWorld"),
        arguments("_hello_world", new Character[] {'_'}, "HelloWorld"),
        arguments("com.robothy:platform-utils", new Character[] {'.', ':', '-'}, "comRobothyPlatformUtils")
    );
  }


  @ParameterizedTest
  @CsvSource(value = {
      "hello;Hello",
      ";",
      "g;G",
      "G;G"
  }, delimiter = ';')
  void upperCaseFirstCharacter(String input, String expect) {
    assertEquals(expect, StringUtils.upperCaseFirstCharacter(input));
  }
}