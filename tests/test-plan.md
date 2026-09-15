# C-Undo Test Plan

## Setup

1. Start Pip with an empty task data file.
2. Enter each command exactly as shown.
3. Restart Pip or restore the empty data file before each test case unless the steps say otherwise.

## Command parsing and validation

### Valid undo command

1. Enter `todo buy milk`.
2. Enter `undo`.

Expected: Pip displays `One step back—I've removed the task you added:`, removes `buy milk`, and reports that the
list contains zero tasks.

### Undo with arguments

1. Enter `undo 1`.

Expected: Pip displays `Please use the correct command format.`

### Incorrect capitalization

1. Enter `Undo`.
2. Enter `UNDO`.

Expected: Pip displays `I lost that trail. Check the command and try again!` after each command.

## Undoing task additions

Repeat the following test with a todo, deadline, and event:

1. Add the task.
2. Enter `undo`.
3. Enter `list`.

Expected: The added task is absent, the reported task count is correct, and the storage file no longer contains the
task.

## Undoing deletion

1. Add tasks named `first`, `second`, and `third` in that order.
2. Enter `delete 2`.
3. Enter `undo`.
4. Enter `list`.

Expected: `second` is restored between `first` and `third` with its original task type, details, and completion state.

## Undoing status changes

### Mark

1. Add an incomplete todo.
2. Mark it.
3. Enter `undo`.

Expected: The todo is incomplete again.

### Unmark

1. Add and mark a todo.
2. Unmark it.
3. Enter `undo`.

Expected: The todo is complete again.

### No-op status commands

1. Mark an already-complete task, then enter `undo`.
2. Unmark an already-incomplete task, then enter `undo`.

Expected: Each status command creates and consumes an undo opportunity while restoring the same prior status.

## History behavior

### Read-only and invalid commands

1. Add a todo.
2. Enter `list`, `find todo`, an invalid task-number command, and an unknown command.
3. Enter `undo`.

Expected: The original add command is undone.

### Replacement by a newer mutation

1. Add `first`.
2. Add `second`.
3. Enter `undo`.

Expected: Only `second` is removed.

### Single-use undo

1. Add a task.
2. Enter `undo` twice.

Expected: The first undo removes the task. The second displays `No steps to retrace yet.`

### Session-only history

1. Add a task.
2. Restart Pip.
3. Enter `undo`.

Expected: Pip displays `No steps to retrace yet.` The task remains loaded from storage.

## Persistence and compatibility

1. Undo each supported mutation and inspect the task data file.
2. Start Pip using a task data file created before C-Undo.

Expected: Successful undo operations are saved immediately. The existing todo, deadline, and event record formats are
unchanged, and no undo-history record or separate history file is created.

## GUI

1. Start the JavaFX interface.
2. Confirm that the welcome message lists `undo`.
3. Execute an undo through the existing command field.

Expected: No new GUI control is present, and all undo response lines appear in one Pip dialog bubble.
