package ramly.model;

import ramly.command.*; import ramly.model.*; import ramly.parser.*; import ramly.storage.*; import ramly.ui.*; import ramly.exception.*;

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

    public String getIcon() {
        return icon;
    }

    public String getCode() {
        return code;
    }
}
