package ramly.model;

import ramly.command.*; import ramly.model.*; import ramly.parser.*; import ramly.storage.*; import ramly.ui.*; import ramly.exception.*;

/** Identifies the supported task categories and their display metadata. */
public enum TaskType {
    TODO("[T]", "T"),
    DEADLINE("[D]", "D"),
    EVENT("[E]", "E");

    private final String icon;
    private final String code;

    TaskType(String icon, String code) {
        this.icon = icon;
        this.code = code;
    }

    /** Returns the icon used when displaying this task type. */
    public String getIcon() {
        return icon;
    }

    /** Returns the code used when serializing this task type. */
    public String getCode() {
        return code;
    }
}
