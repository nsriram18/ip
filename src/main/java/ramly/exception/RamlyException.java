package ramly.exception;

/** Provides user-facing messages for invalid Ramly commands. */
public class RamlyException extends Exception {

    protected String s;
    /** Creates an exception for a specific command category. */
    public RamlyException(String s) {
        this.s = s;
    }
    /** Creates a general command exception. */
    public RamlyException() {
    }

    public String randomWord() {
        return "I lost that trail. Check the command and try again!";
    }

    public String emptyString() {
        return "That " + this.s + " needs a description before we set off. Try again!";
    }

    public String notANumber() {
        return "That isn't a trail-marker number. Try again with a number!";
    }

    public String invalidNumber() {
        return "That trail marker doesn't exist. Use list to check the available numbers.";
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
