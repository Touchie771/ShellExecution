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

---

## OS Information and System Resources Tools

### get-os

Get the current operating system name.

**Parameters:**
- None

**Returns:**
- String containing the OS name (e.g., "Linux", "Windows 10", "macOS")

**Example Usage:**
```json
{
  "command": "get-os"
}
```

**Sample Response:**
```
Linux
```

### get-os-architecture

Get the operating system architecture.

**Parameters:**
- None

**Returns:**
- String containing the OS architecture (e.g., "amd64", "x86_64", "aarch64")

**Example Usage:**
```json
{
  "command": "get-os-architecture"
}
```

### get-os-version

Get the operating system version.

**Parameters:**
- None

**Returns:**
- String containing the OS version information

**Example Usage:**
```json
{
  "command": "get-os-version"
}
```

### get-os-family

Get the OS family classification.

**Parameters:**
- None

**Returns:**
- String: "Windows", "Linux", "Mac", "Unix", or "Unknown"

**Example Usage:**
```json
{
  "command": "get-os-family"
}
```

### is-64-bit

Check if the operating system is 64-bit.

**Parameters:**
- None

**Returns:**
- Boolean: true if 64-bit, false otherwise

**Example Usage:**
```json
{
  "command": "is-64-bit"
}
```

### get-shell-type

Get the current shell type.

**Parameters:**
- None

**Returns:**
- String containing the shell path (e.g., "/bin/bash", "/bin/zsh")

**Example Usage:**
```json
{
  "command": "get-shell-type"
}
```

### get-username

Get the current username.

**Parameters:**
- None

**Returns:**
- String containing the current user's name

**Example Usage:**
```json
{
  "command": "get-username"
}
```

### get-java-version

Get the Java version being used.

**Parameters:**
- None

**Returns:**
- String containing the Java version (e.g., "21.0.2")

**Example Usage:**
```json
{
  "command": "get-java-version"
}
```

### get-java-home

Get the Java home directory.

**Parameters:**
- None

**Returns:**
- String containing the Java installation directory path

**Example Usage:**
```json
{
  "command": "get-java-home"
}
```

### get-working-directory

Get the current working directory.

**Parameters:**
- None

**Returns:**
- String containing the current working directory path

**Example Usage:**
```json
{
  "command": "get-working-directory"
}
```

### get-home-directory

Get the user's home directory.

**Parameters:**
- None

**Returns:**
- String containing the user home directory path

**Example Usage:**
```json
{
  "command": "get-home-directory"
}
```

### get-temp-directory

Get the temporary directory.

**Parameters:**
- None

**Returns:**
- String containing the temporary directory path

**Example Usage:**
```json
{
  "command": "get-temp-directory"
}
```

### get-available-processors

Get the number of available processor cores.

**Parameters:**
- None

**Returns:**
- Integer containing the number of available processors

**Example Usage:**
```json
{
  "command": "get-available-processors"
}
```

### get-max-memory

Get the maximum memory the JVM can use.

**Parameters:**
- None

**Returns:**
- Long containing maximum memory in bytes

**Example Usage:**
```json
{
  "command": "get-max-memory"
}
```

### get-total-memory

Get the total memory currently allocated to the JVM.

**Parameters:**
- None

**Returns:**
- Long containing total memory in bytes

**Example Usage:**
```json
{
  "command": "get-total-memory"
}
```

### get-free-memory

Get the free memory available to the JVM.

**Parameters:**
- None

**Returns:**
- Long containing free memory in bytes

**Example Usage:**
```json
{
  "command": "get-free-memory"
}
```

### get-system-load-average

Get the system load average for the last minute.

**Parameters:**
- None

**Returns:**
- Double containing the system load average

**Example Usage:**
```json
{
  "command": "get-system-load-average"
}
```

### get-disk-space-info

Get disk space information for a given path.

**Parameters:**
- `path` (String, optional): Directory path to check. Defaults to current directory if not provided.

**Returns:**
- Map with keys: "totalSpace", "freeSpace", "usableSpace" (all in bytes)

**Example Usage:**
```json
{
  "command": "get-disk-space-info",
  "arguments": {
    "path": "/home"
  }
}
```

**Sample Response:**
```json
{
  "totalSpace": 107374182400,
  "freeSpace": 53687091200,
  "usableSpace": 53687091200
}
```

### get-memory-info

Get comprehensive memory information.

**Parameters:**
- None

**Returns:**
- Map containing detailed memory usage information including heap and non-heap memory

**Example Usage:**
```json
{
  "command": "get-memory-info"
}
```

### get-system-info

Get comprehensive system information.

**Parameters:**
- None

**Returns:**
- Map containing detailed system information including OS details, Java info, and system resources

**Example Usage:**
```json
{
  "command": "get-system-info"
}
```

## Environment and Properties Tools

### get-environment-variable

Get a specific environment variable by name.

**Parameters:**
- `variableName` (String): Name of the environment variable to retrieve

**Returns:**
- String containing the environment variable value, or null if not found

**Example Usage:**
```json
{
  "command": "get-environment-variable",
  "arguments": {
    "variableName": "PATH"
  }
}
```

### get-all-environment-variables

Get all environment variables as a map.

**Parameters:**
- None

**Returns:**
- Map containing all environment variables and their values

**Example Usage:**
```json
{
  "command": "get-all-environment-variables"
}
```

### get-system-property

Get a specific system property by name.

**Parameters:**
- `propertyName` (String): Name of the system property to retrieve

**Returns:**
- String containing the system property value, or null if not found

**Example Usage:**
```json
{
  "command": "get-system-property",
  "arguments": {
    "propertyName": "user.timezone"
  }
}
```

### get-all-system-properties

Get all system properties as a map.

**Parameters:**
- None

**Returns:**
- Map containing all system properties and their values

**Example Usage:**
```json
{
  "command": "get-all-system-properties"
}
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

### OS Information Discovery
```bash
# Get basic OS information
get-os
get-os-family
get-os-version
get-os-architecture

# Check system capabilities
is-64-bit
get-available-processors

# Get Java runtime information
get-java-version
get-java-home
```

### System Resource Monitoring
```bash
# Monitor memory usage
get-memory-info
get-free-memory
get-total-memory
get-max-memory

# Monitor system performance
get-system-load-average
get-system-info

# Check disk space
get-disk-space-info ["/home"]
get-disk-space-info []  # Current directory
```

### Environment and Properties Access
```bash
# Get specific environment variables
get-environment-variable ["PATH"]
get-environment-variable ["HOME"]

# Get all environment variables
get-all-environment-variables

# Get system properties
get-system-property ["user.timezone"]
get-all-system-properties
```

### Directory and User Information
```bash
# Get current context
get-working-directory
get-home-directory
get-temp-directory
get-username
get-shell-type
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