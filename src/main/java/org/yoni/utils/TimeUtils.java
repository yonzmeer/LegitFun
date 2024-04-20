package org.yoni.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TimeUtils {
  public static Instant toInstant(String isoTime) {
    try {
      return LocalDateTime.parse(isoTime, DateTimeFormatter.ISO_DATE_TIME)
          .toInstant(ZoneOffset.UTC);
    } catch (DateTimeParseException e) {
      return Instant.ofEpochMilli(Long.parseLong(isoTime));
    }
  }
}
