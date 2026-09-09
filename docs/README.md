# Duke User Guide

// Update the title above to match the actual product name

// Product screenshot goes here

// Product intro goes here

## Adding deadlines

// Describe the action and its outcome.

// Give examples of usage

Example: `keyword (optional arguments)`

// A description of the expected outcome goes here

```
expected output
```

## Undoing the previous command

Use `undo` to reverse the most recent successful command that changed the task list.

Example:

```text
todo buy milk
undo
```

Expected undo output:

```text
Okay, I've removed the task added by the previous command:
 [T][ ] buy milk
Now you have 0 tasks in the list.
```

Only one command can be undone. Read-only commands such as `list` and `find`, as well as invalid commands, do not
replace the available undo. If there is nothing to undo, Ramly displays:

```text
There is no command to undo.
```

Undo history lasts only for the current application session and is cleared when Ramly restarts.

## Feature ABC

// Feature details


## Feature XYZ

// Feature details
