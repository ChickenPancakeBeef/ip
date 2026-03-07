# Jerry Task Manager 🤖: User Guide

## Overview
Welcome to **Jerry**, a specialized task-tracking chatbot designed to help you manage your todos, deadlines, and events. Jerry is optimized for speed via a Command Line Interface (CLI), allowing you to organize your life as fast as you can type.

---

## Table of Contents
1. [Quick Start](#quick-start)
2. [Features](#features)
    - [Add Todo: `todo`](#add-todo-task-todo)
    - [Add Deadline: `deadline`](#add-deadline-task-deadline)
    - [Add Event: `event`](#add-event-task-event)
    - [List tasks: `list`](#list-of-tasks-list)
    - [Mark task: `mark`](#mark-a-task-mark)
    - [Unmark task: `unmark`](#unmark-a-task-unmark)
    - [Delete task: `delete`](#deleting-a-task-delete)
    - [Find task: `find`](#finding-a-task-find)
    - [Exit: `bye`](#exiting-the-program-bye)
3. [Saving the Data](#saving-the-data)
4. [FAQ](#faq)
5. [Command Summary](#command-summary)

---

## Quick Start

1. Ensure you have **Java 17** or above installed on your computer.
2. Download the latest `ip.jar` file for Jerry.
3. Copy the file to the folder you want to use as the "home" folder for your tasks.
4. Open Terminal (Mac) or PowerShell (Windows), `cd` into that folder, and run:
   ```
   java -jar ip.jar
   ```
5. Jerry will greet you:
   ```
   Hello! I'm Jerry
   What can I do for you?
   ```

---

## Features

> [!IMPORTANT]
> Jerry uses **1-based indexing**. For commands like `mark`, `unmark`, and `delete`, the first task is `1`, the second is `2`, etc.

---

### Add Todo task: `todo`

Adds a basic task to the list without a specific date or time.
* **Format:** `todo DESCRIPTION`
* **Example:** `todo buy groceries`
* **Outcome:** Adds a Todo task to your list.

**Expected Output:**
```
Got it. I've added this task:
  [T][ ] buy groceries
Now you have 1 task in the list.
```

---

### Add Deadline task: `deadline`

Adds a task with a specific completion date.

* **Format:** `deadline DESCRIPTION /by DATE`
* **Example:** `deadline submit homework /by 2026-04-15`
* **Outcome:** Adds a Deadline task with a formatted due date.

**Expected Output:**
```
Got it. I've added this task:
  [D][ ] submit homework (by: Apr 15 2026)
Now you have 2 tasks in the list.
```

---

### Add Event task: `event`

Adds a task with a specific start and end time range.

* **Format:** `event DESCRIPTION /from START /to END`
* **Example:** `event project meeting /from 2pm /to 4pm`
* **Outcome:** Adds an Event task with a time range.

**Expected Output:**
```
Got it. I've added this task:
  [E][ ] project meeting (from: 2pm to: 4pm)
Now you have 3 tasks in the list.
```

---

### List of tasks: `list`

Displays all tasks currently being tracked by Jerry.

* **Format:** `list`
* **Example:** `list`
* **Outcome:** Prints all tasks with their type, status, and description.

**Expected Output:**
```
1.[T][ ] buy groceries
2.[D][ ] submit homework (by: Apr 15 2026)
3.[E][ ] project meeting (from: 2pm to: 4pm)
```

---

### Mark a task: `mark`

Marks a task as completed using its index in the list.

* **Format:** `mark INDEX`
* **Example:** `mark 1`
* **Outcome:** The task's status box changes from `[ ]` to `[X]`.

**Expected Output:**
```
Nice! I've marked this task as done:
  [T][X] buy groceries
```

---

### Unmark a task: `unmark`

Reverts a completed task to "not done" status.

* **Format:** `unmark INDEX`
* **Example:** `unmark 1`
* **Outcome:** The task's status box changes from `[X]` back to `[ ]`.

**Expected Output:**
```
OK, I've marked this task as not done yet:
  [T][ ] buy groceries
```

---

### Deleting a task: `delete`

Removes a task from the list permanently using its index.

* **Format:** `delete INDEX`
* **Example:** `delete 2`
* **Outcome:** The task is removed and the list is re-indexed.

**Expected Output:**
```
Noted. I've removed this task:
  [D][ ] submit homework (by: Apr 15 2026)
Now you have 2 tasks in the list.
```

---

### Finding a task: `find`

Finds tasks by searching for a specific keyword in their description.

* **Format:** `find KEYWORD`
* **Example:** `find groceries`
* **Outcome:** Displays all tasks whose descriptions contain the keyword.

**Expected Output:**
```
Here are the matching tasks in your list:
1.[T][ ] buy groceries
```

---

### Exiting the program: `bye`

Closes Jerry and automatically saves all your current tasks to the hard drive.

* **Format:** `bye`
* **Example:** `bye`
* **Outcome:** Jerry saves your data and exits.

**Expected Output:**
```
Bye. Hope to see you again soon!
```

---

## Saving the Data

Jerry automatically saves all your task data to `data/jerry.txt` whenever you exit with `bye`. The file is created automatically — no setup needed.

> [!CAUTION]
> Do not manually edit the `jerry.txt` file. Manual changes can lead to data corruption and may prevent Jerry from loading your tasks.

---

## FAQ

**Q: What happens if I close the window without typing `bye`?**  
A: Your tasks may not be saved. Always use `bye` to exit safely.

**Q: Can I have two tasks with the same description?**  
A: Yes, Jerry does not prevent duplicate descriptions.

**Q: What date format does Jerry accept for deadlines?**  
A: Use `YYYY-MM-DD` format (e.g. `2026-04-15`). Jerry will display it as `Apr 15 2026`.

---

## Command Summary

| Action   | Format                                  | Example                          |
|----------|-----------------------------------------|----------------------------------|
| Todo     | `todo DESCRIPTION`                      | `todo buy milk`                  |
| Deadline | `deadline DESCRIPTION /by DATE`         | `deadline exam /by 2026-05-20`   |
| Event    | `event DESCRIPTION /from START /to END` | `event party /from 6pm /to 10pm` |
| List     | `list`                                  | `list`                           |
| Mark     | `mark INDEX`                            | `mark 1`                         |
| Unmark   | `unmark INDEX`                          | `unmark 1`                       |
| Delete   | `delete INDEX`                          | `delete 1`                       |
| Find     | `find KEYWORD`                          | `find groceries`                 |
| Exit     | `bye`                                   | `bye`                            |