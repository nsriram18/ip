# Pip User Guide

// Product screenshot goes here

Pip is a calm pocket pathfinder that helps you keep everyday tasks on a clear trail. Its warm, concise responses
celebrate progress without changing the familiar command workflow.

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
One step back—I've removed the task you added:
 [•][ ] buy milk
Now you have 0 tasks in the list.
```

Only one command can be undone. Read-only commands such as `list` and `find`, as well as invalid commands, do not
replace the available undo. If there is nothing to undo, Pip displays:

```text
No steps to retrace yet.
```

Undo history lasts only for the current application session and is cleared when Pip restarts.

## Feature ABC

// Feature details


## Feature XYZ

// Feature details
