package ramly.model;

import ramly.command.*; import ramly.model.*; import ramly.parser.*; import ramly.storage.*; import ramly.ui.*; import ramly.exception.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Deadline extends Task {
    private static final DateTimeFormatter STORAGE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private static final DateTimeFormatter DISPLAY_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter DISPLAY_TIME_FORMAT = DateTimeFormatter.ofPattern("h:mma");
    private static final DateTimeFormatter INPUT_FORMAT = new DateTimeFormatterBuilder()
            .appendPattern("d/M/uuuu")
            .optionalStart()
            .appendPattern(" HHmm")
            .optionalEnd()
            .toFormatter();

    private final LocalDateTime by;


    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = parseDateTime(by);
    }

    private static LocalDateTime parseDateTime(String value) {
        try {
            return LocalDateTime.parse(value, STORAGE_FORMAT);
        } catch (java.time.format.DateTimeParseException ignored) {
            // User input may omit the time or use the shorter d/M/yyyy HHmm form.
        }
        if (value.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay();
        }
        return LocalDateTime.parse(value, INPUT_FORMAT);
    }

    public LocalDateTime getBy() {
        return by;
    }

    private String displayDateTime() {
        String date = by.format(DISPLAY_DATE_FORMAT);
        return by.toLocalTime().equals(LocalTime.MIDNIGHT)
                ? date
                : date + ", " + by.format(DISPLAY_TIME_FORMAT);
    }

    @Override
    public String toFileString() {
        return super.toFileString() + " | " + by.format(STORAGE_FORMAT);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + displayDateTime() + ")";
    }
}
