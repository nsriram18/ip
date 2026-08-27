package ramly.exception;

import ramly.command.*; import ramly.model.*; import ramly.parser.*; import ramly.storage.*; import ramly.ui.*; import ramly.exception.*;

/** Provides user-facing messages for invalid Ramly commands. */
public class RamlyException extends Exception{

    protected String s;
    /** Creates an exception for a specific command category. */
    public RamlyException(String s) {
        this.s = s;
    }
    /** Creates a general command exception. */
    public RamlyException() {
    }

    public String randomWord() {
        return "OOPS! I'm sorry but I don't understand what that means! Try Again!";
    }

    public String emptyString() {
        return "The description of " + this.s + " cannot be empty! Try Again!";
    }

    public String notANumber() {
        return "This is not a number! Try Again with a number!";
    }

    public String invalidNumber() {
        return "Try Again! Choose a number within the number of task available! To view the number of tasks, type list!";
    }

    public String correctFormat() {
        if (this.s == "deadline") {
            return "Please use the correct format to log a deadline.\n" +
                    "deadline <description> /by <yyyy-mm-dd>";
        } else if (this.s == "event") {
            return "Please use the correct format to log a event.\n" +
                    "deadline <description> /from <Day/Date/Time> /to <Day/Date/Time>";
        }
        return null;
    }
}
