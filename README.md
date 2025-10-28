# Shell Execution MCP Server

A Model Context Protocol (MCP) server built with Spring Boot that provides shell command execution and command history management tools.

## Description

The Shell Execution MCP Server exposes tools for interacting with the host shell and managing command history via the MCP interface. Supports both synchronous command execution and asynchronous background process management.

## Prerequisites

- Java 21 or higher
- Gradle (or use the included Gradle wrapper)

## Building

```bash
./gradlew build
```

## Running

Development:
```bash
./gradlew bootRun
```

Production:
```bash
./gradlew bootJar
java -jar build/libs/ShellExecution-0.0.1-SNAPSHOT.jar
```

## Configuration

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

### Core Tools
- **execute-command** - Run shell commands synchronously
- **start-command-async** - Run commands in background with process tracking
- **check-command-status** - Monitor background processes
- **stop-command** - Terminate background processes
- **list-background-processes** - View all running processes

### History Tools
- **get-command-history** - View executed commands
- **clear-command-history** - Clear command history
- **save-to-file** - Save history to file
- **load-from-file** - Load history from file

## Documentation

For detailed documentation, see the [docs/](./docs/) folder:
- [Complete tools reference](./docs/tools.md)
- [Usage examples](./docs/usage-examples.md)
- [Development guide](./docs/development.md)

## Project Structure

```
ShellExecution/
├── src/main/java/me/touchie771/ShellExecution/
│   ├── ShellExecutionApplication.java  # Main application
│   ├── Terminal.java                   # Command execution
│   ├── CommandHistory.java             # History management
│   └── AsyncProcessManager.java        # Background processes
├── docs/                               # Documentation
└── build.gradle
```

## Dependencies

- Spring Boot 3.5.7
- Spring AI MCP Server 1.0.3
- Java 21

## License

MIT License