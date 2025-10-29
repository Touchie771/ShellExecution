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

### OS Information Tools
- **get-os** - Get the current OS name
- **get-os-architecture** - Get the OS architecture
- **get-os-version** - Get the OS version
- **get-os-family** - Get the OS family (Windows, Linux, Mac, Unix, or Unknown)
- **is-64-bit** - Check if the OS is 64-bit
- **get-shell-type** - Get the current shell type
- **get-username** - Get the current username
- **get-java-version** - Get the Java version
- **get-java-home** - Get the Java home directory
- **get-working-directory** - Get the current working directory
- **get-home-directory** - Get the user home directory
- **get-temp-directory** - Get the temporary directory

### System Resources Tools
- **get-available-processors** - Get the number of available processors
- **get-max-memory** - Get the maximum memory the JVM can use (in bytes)
- **get-total-memory** - Get the total memory currently allocated to the JVM (in bytes)
- **get-free-memory** - Get the free memory available to the JVM (in bytes)
- **get-system-load-average** - Get the system load average for the last minute
- **get-disk-space-info** - Get disk space information for a given path
- **get-memory-info** - Get comprehensive memory information
- **get-system-info** - Get comprehensive system information

### Environment & Properties Tools
- **get-environment-variable** - Get a specific environment variable by name
- **get-all-environment-variables** - Get all environment variables as a map
- **get-system-property** - Get a specific system property by name
- **get-all-system-properties** - Get all system properties as a map

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
│   ├── AsyncProcessManager.java        # Background processes
│   └── Os.java                         # OS information and system resources
├── docs/                               # Documentation
└── build.gradle
```

## Dependencies

- Spring Boot 3.5.7
- Spring AI MCP Server 1.0.3
- Java 21

## License

MIT License