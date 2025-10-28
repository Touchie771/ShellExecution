# MCP Server Tools Documentation

This document provides detailed information about all available tools in the Shell Execution MCP Server.

## Core Command Execution Tools

### execute-command

Executes a terminal command synchronously and returns its output.

**Parameters:**
- `command` (String[]): An array of strings where each element represents the command and its arguments.

**Examples:**
- `["ls", "-la"]` - List files in long format
- `["echo", "hello world"]` - Echo a message
- `["git", "status"]` - Show git repository status

**Returns:**
- Success: The command standard output as a string
- Failure: Error message with exit code or exception details

**Notes:**
- This tool blocks until the command completes
- Only captures stdout, not stderr
- Successfully executed commands are automatically added to command history

## Command History Tools

### get-command-history

Returns the in-memory list of commands that were successfully executed during the current process lifetime.

**Parameters:**
- None

**Returns:**
- Array of strings, each a command line previously executed

**Example Usage:**
```json
{
  "command": "get-command-history"
}
```

### clear-command-history

Clears the in-memory command history list.

**Parameters:**
- None

**Returns:**
- void (no return value)

**Example Usage:**
```json
{
  "command": "clear-command-history"
}
```

### save-to-file

Saves the current in-memory command history to a UTF-8 text file, one command per line.

**Parameters:**
- `absolutePath` (String): Destination file path.
- `overwrite` (boolean): If true, replaces an existing file.

**Returns:**
- Status message including how many commands were saved

**Example Usage:**
```json
{
  "command": "save-to-file",
  "arguments": {
    "absolutePath": "/home/user/command_history.txt",
    "overwrite": true
  }
}
```

**File Format:**
```
ls -la
echo hello world
git status
[ASYNC] sleep 60
```

### load-from-file

Loads command history from a UTF-8 text file, replacing the current in-memory history.

**Parameters:**
- `absolutePath` (String): Source file path.

**Returns:**
- Status message including how many commands were loaded

**Example Usage:**
```json
{
  "command": "load-from-file",
  "arguments": {
    "absolutePath": "/home/user/command_history.txt"
  }
}
```

## Asynchronous Process Management Tools

### start-command-async

Starts a command in background and returns a process ID for tracking.

**Parameters:**
- `command` (String[]): An array of strings where each element represents the command and its arguments.

**Examples:**
- `["sleep", "60"]` - Sleep for 60 seconds in background
- `["ping", "google.com"]` - Ping Google continuously in background
- `["npm", "run", "dev"]` - Start a development server in background

**Returns:**
- Process ID for tracking the background command

**Example Usage:**
```json
{
  "command": "start-command-async",
  "arguments": {
    "command": ["sleep", "60"]
  }
}
```

**Sample Response:**
```
Command started in background. Process ID: 550e8400-e29b-41d4-a716-446655440000
```

### check-command-status

Check the status of a background command. Returns status, runtime, and output if available.

**Parameters:**
- `processId` (String): The process ID returned by start-command-async.

**Returns:**
- Current status (RUNNING, COMPLETED_SUCCESS, COMPLETED_ERROR, TERMINATED)
- Command that was executed
- Runtime in seconds
- Exit code (if completed)
- Output from stdout and stderr (if available)

**Status Values:**
- `RUNNING`: Process is still executing
- `COMPLETED_SUCCESS`: Process finished successfully (exit code 0)
- `COMPLETED_ERROR`: Process finished with an error (non-zero exit code)
- `TERMINATED`: Process was manually stopped

**Example Usage:**
```json
{
  "command": "check-command-status",
  "arguments": {
    "processId": "550e8400-e29b-41d4-a716-446655440000"
  }
}
```

**Sample Response (Running):**
```
Process 550e8400-e29b-41d4-a716-446655440000 is RUNNING.
Command: sleep 60
Started: 45 seconds ago
Status: RUNNING
```

**Sample Response (Completed):**
```
Process 550e8400-e29b-41d4-a716-446655440000 is COMPLETED_SUCCESS.
Command: echo "Hello World"
Exit Code: 0
Duration: 0 seconds
Output:
Hello World
```

### stop-command

Stops a background command by its process ID.

**Parameters:**
- `processId` (String): The process ID to terminate.

**Returns:**
- Status message indicating if the process was stopped

**Example Usage:**
```json
{
  "command": "stop-command",
  "arguments": {
    "processId": "550e8400-e29b-41d4-a716-446655440000"
  }
}
```

**Notes:**
- First attempts graceful termination (SIGTERM)
- If graceful termination fails after 5 seconds, forces termination (SIGKILL)
- Process status is updated to TERMINATED

### list-background-processes

Lists all background processes with their status and details.

**Parameters:**
- None

**Returns:**
- List of all tracked background processes with their IDs, commands, status, and runtime

**Example Usage:**
```json
{
  "command": "list-background-processes"
}
```

**Sample Response:**
```
Background Processes:
===================
ID: 550e8400-e29b-41d4-a716-446655440000
Command: sleep 60
Status: RUNNING
Runtime: 30 seconds
---
ID: 550e8400-e29b-41d4-a716-446655440001
Command: echo "Hello World"
Status: COMPLETED_SUCCESS
Runtime: 1 seconds
Exit Code: 0
---
```

## Usage Patterns

### Basic Command Execution
```bash
# Execute a simple command
execute-command ["ls", "-la"]

# Get command history
get-command-history

# Clear history
clear-command-history
```

### Background Process Management
```bash
# Start a long-running process
start-command-async ["npm", "run", "dev"]

# Check its status periodically
check-command-status ["process-id"]

# List all background processes
list-background-processes

# Stop when done
stop-command ["process-id"]
```

### History Management
```bash
# Save history to file
save-to-file ["/home/user/my_commands.txt", true]

# Load history from file
load-from-file ["/home/user/my_commands.txt"]
```

## Error Handling

All tools provide descriptive error messages for common issues:
- Invalid process IDs
- File access errors
- Command execution failures
- Permission issues

## Best Practices

1. **Use async commands** for long-running operations to avoid blocking
2. **Save command history** regularly to persist between server restarts
3. **Check process status** before attempting to stop processes
4. **Clean up completed processes** by removing them from tracking when no longer needed
5. **Use absolute paths** for file operations to ensure consistency