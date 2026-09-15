# Pip User Guide

// Product screenshot goes here

Pip is a calm pocket pathfinder that helps you keep everyday tasks on a clear trail. Its warm, concise responses
celebrate progress without changing the familiar command workflow.

## Command input

Pip ignores leading and trailing whitespace and accepts repeated spaces or tabs between command parts. Command words
and parameter markers remain case-sensitive. A command can contain at most 500 characters.

Task descriptions can contain ordinary Unicode text and punctuation. They cannot be blank, exceed 200 characters,
or contain `|`, line breaks, or control characters because those values cannot be represented safely in the task
data file. Find keywords have the same rules and can contain at most 100 characters.

Commands that do not accept parameters must contain only their command word:

```text
list
undo
bye
```

Supplying extra text produces a command-specific message such as `Use this format: undo`.

## Adding tasks

Add a todo with `todo <description>`.

Add a deadline with `deadline <description> /by <date or date-time>`. Accepted values are:

- `yyyy-MM-dd`, such as `2026-10-15`
- `yyyy-MM-dd HHmm`, such as `2026-10-15 1830`
- `d/M/yyyy HHmm`, such as `15/10/2026 1830`

Dates are checked strictly, so impossible dates such as February 30 are rejected.

Add an event with:

```text
event <description> /from <date-time> /to <date-time>
```

Each event boundary must use `yyyy-MM-dd HHmm` or `d/M/yyyy HHmm`, and the start must be earlier than the end.
For example:

```text
event project meeting /from 2026-10-15 1400 /to 2026-10-15 1500
```

Each parameter marker must appear exactly once and in the documented order. Pip rejects an exact duplicate task,
ignoring differences in description capitalization and repeated whitespace. Tasks with the same description but
different dates or time bounds remain valid.

## Selecting tasks

`mark`, `unmark`, and `delete` require one positive whole-number trail marker, such as `mark 1`. Zero, negative
numbers, decimal values, words, trailing arguments, and numbers outside the displayed list are rejected.

## Storage recovery

Pip creates the `data/ramly.txt` UTF-8 data file when it is missing. Saves use a temporary file and replace the data
file only after the complete update is written. If a save fails, Pip restores the in-memory task list and its undo
history, then reports that no changes were applied.

If stored records are malformed, Pip loads the valid records, creates a neighboring `.bak` copy of the original
file, and identifies the skipped line numbers. Legacy event records containing free-text boundaries remain readable.
If the file cannot be read or safely backed up, Pip reports the problem and disables command entry rather than
running with data that cannot be persisted safely.

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

Only one command can be undone. Read-only commands such as `list` and `find`, as well as rejected commands, do not
replace the available undo. If there is nothing to undo, Pip displays:

```text
No steps to retrace yet.
```

Undo history lasts only for the current application session and is cleared when Pip restarts.

## Feature ABC

// Feature details


## Feature XYZ

// Feature details
