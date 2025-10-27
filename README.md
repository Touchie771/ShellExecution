# Shell Execution MCP Server

A Model Context Protocol (MCP) server built with Spring Boot that provides shell command execution and command history management tools.

## Description

The Shell Execution MCP Server exposes multiple tools for interacting with the host shell and managing command history via the MCP interface. The primary tool is `execute-command` for running commands. Additional tools provide history retrieval, clearing, and persistence to/from a file.

## Prerequisites

- Java 21 or higher
- Gradle (or use the included Gradle wrapper)

## Building the Project

```bash
./gradlew build
```

## Running the Server

During development:

```bash
./gradlew bootRun
```

Build a runnable jar:

```bash
./gradlew bootJar
java -jar build/libs/ShellExecution-0.0.1-SNAPSHOT.jar
```

## Configuring the Server

```json
{
  "mcpServers": {
    "shell-execution": {
      "command": "java",
      "args": [
        "-jar",
        "<absolute_path_to>/build/libs/ShellExecution-0.0.1-SNAPSHOT.jar"
      ]
    }
  }
}
```

## Available Tools

### execute-command

Executes a terminal command and returns its output.

**Parameters:**
- `command` (String[]): An array of strings where each element represents the command and its arguments.

**Examples:**
- `["ls", "-la"]` - List files in long format
- `["echo", "hello world"]` - Echo a message

**Returns:**
- Success: The command standard output as a string
- Failure: Error message with exit code or exception details

### get-command-history

Returns the in-memory list of commands that were successfully executed via `execute-command` during the current process lifetime.

**Parameters:**
- none

**Returns:**
- Array of strings, each a command line previously executed

### clear-command-history

Clears the in-memory command history list.

**Parameters:**
- none

**Returns:**
- void

### save-to-file

Saves the current in-memory command history to a UTF-8 text file, one command per line.

**Parameters:**
- `absolutePath` (String): Destination file path.
- `overwrite` (boolean): If true, replaces an existing file.

**Returns:**
- Status message including how many commands were saved

### load-from-file

Loads command history from a UTF-8 text file, replacing the current in-memory history.

**Parameters:**
- `absolutePath` (String): Source file path.

**Returns:**
- Status message including how many commands were loaded

## Project Structure

```
ShellExecution/
├── src/
│   └── main/
│       └── java/
│           └── me/touchie771/ShellExecution/
│               ├── ShellExecutionApplication.java  # Main application and tool registry
│               ├── Terminal.java                   # Command execution tool
│               └── CommandHistory.java             # History tools (get/clear/save/load)
├── build.gradle
└── README.md
```

## Dependencies

- Spring Boot 3.5.7
- Spring AI MCP Server 1.0.3
- Java 21

## License

This project is licensed under the MIT License.