package com.robothy.platform.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

class PropertyFileUtilsTest {

  @Test
  void upsertProperty() throws IOException {
    String content1 = """
        a=1
        b= 2
        """;
    File propFile1 = writeToTmpFile(content1);
    String oldA = PropertyFileUtils.upsertProperty(propFile1, "a", "2");
    assertEquals("""
        a=2
        b= 2
        """, Files.readString(propFile1.toPath()));
    assertEquals("1", oldA);

    String oldC = PropertyFileUtils.upsertProperty(propFile1, "c", "3");
    assertNull(oldC);
    assertEquals("""
        a=2
        b= 2
        c=3""", Files.readString(propFile1.toPath()));


  }

  @SneakyThrows
  static File writeToTmpFile(String content) {
    Path tempFile = Files.createTempFile("upsert-property", ".properties");
    Files.writeString(tempFile, content);
    File file = tempFile.toFile();
    file.deleteOnExit();
    return file;
  }

}