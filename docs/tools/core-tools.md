# Core Tools

These tools provide the fundamental shell command execution and history management capabilities.

## Command Execution Tools

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

## Asynchronous Process Management Tools

### start-command-async
Starts a command in background and returns a process ID for tracking.

**Parameters:**
- `command` (String[]): An array of strings where each element represents the command and its arguments.

**Examples:**
- `["sleep", "60"]` - Sleep for 60 seconds in background
- `["ping", "google.com"]` - Ping Google continuously in background
- `["npm", "run", "dev"]` - Start a development server in background

**Returns:** Process ID for tracking the background command

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

### stop-command
Stops a background command by its process ID.

**Parameters:**
- `processId` (String): The process ID to terminate.

**Returns:** Status message indicating if the process was stopped

### list-background-processes
Lists all background processes with their status and details.

**Parameters:** None

**Returns:** List of all tracked background processes with their IDs, commands, status, and runtime

## Command History Tools

### get-command-history
Returns the in-memory list of commands that were successfully executed during the current process lifetime.

**Parameters:** None

**Returns:** Array of strings, each a command line previously executed

### clear-command-history
Clears the in-memory command history list.

**Parameters:** None

**Returns:** void (no return value)

### save-to-file
Saves the current in-memory command history to a UTF-8 text file, one command per line.

**Parameters:**
- `absolutePath` (String): Destination file path.
- `overwrite` (boolean): If true, replaces an existing file.

**Returns:** Status message including how many commands were saved

### load-from-file
Loads command history from a UTF-8 text file, replacing the current in-memory history.

**Parameters:**
- `absolutePath` (String): Source file path.

**Returns:** Status message including how many commands were loaded

## Usage Examples

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
