public class Todo extends Task {

    protected String by;


    public Todo(String description) {
        super(description, TaskType.TODO);

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
