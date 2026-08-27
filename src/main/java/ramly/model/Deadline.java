package ramly.model;

import ramly.command.*; import ramly.model.*; import ramly.parser.*; import ramly.storage.*; import ramly.ui.*; import ramly.exception.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

/** Represents a task that must be completed by a date and optional time. */
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


    /** Creates a deadline from a supported user or storage date format. */
    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = parseDateTime(by);
    }

    /** Parses storage or user input into a local date and time. */
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

    /** Returns the parsed deadline date and time. */
    public LocalDateTime getBy() {
        return by;
    }

    /** Formats the deadline for display to the user. */
    private String displayDateTime() {
        String date = by.format(DISPLAY_DATE_FORMAT);
        return by.toLocalTime().equals(LocalTime.MIDNIGHT)
                ? date
                : date + ", " + by.format(DISPLAY_TIME_FORMAT);
    }

    @Override
    /** Returns the serialized deadline representation. */
    public String toFileString() {
        return super.toFileString() + " | " + by.format(STORAGE_FORMAT);
    }

    @Override
    /** Returns the user-facing deadline representation. */
    public String toString() {
        return super.toString() + " (by: " + displayDateTime() + ")";
    }
}
