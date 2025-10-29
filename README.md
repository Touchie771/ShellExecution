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
- **get-command-history** - View executed commands
- **clear-command-history** - Clear command history
- **save-to-file** - Save history to file
- **load-from-file** - Load history from file

### OS Information Tools (12 tools)
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

### System Resources Tools (8 tools)
- **get-available-processors** - Get the number of available processors
- **get-max-memory** - Get the maximum memory the JVM can use (in bytes)
- **get-total-memory** - Get the total memory currently allocated to the JVM (in bytes)
- **get-free-memory** - Get the free memory available to the JVM (in bytes)
- **get-system-load-average** - Get the system load average for the last minute
- **get-disk-space-info** - Get disk space information for a given path
- **get-memory-info** - Get comprehensive memory information
- **get-system-info** - Get comprehensive system information

### Environment & Properties Tools (4 tools)
- **get-environment-variable** - Get a specific environment variable by name
- **get-all-environment-variables** - Get all environment variables as a map
- **get-system-property** - Get a specific system property by name
- **get-all-system-properties** - Get all system properties as a map

**Total: 33 available tools**

## Documentation

For detailed documentation, see the [docs/](./docs/) folder:

### Tools Documentation
- [Tools Overview](./docs/tools/README.md) - Complete tools reference organized by category
- [Core Tools](./docs/tools/core-tools.md) - Command execution and process management
- [OS Information](./docs/tools/os-info.md) - Operating system discovery tools
- [System Resources](./docs/tools/system-resources.md) - Resource monitoring tools
- [Environment Tools](./docs/tools/environment-tools.md) - Environment variables and properties

### Usage Examples
- [Examples Overview](./docs/examples/README.md) - Practical examples organized by category
- [OS Discovery Examples](./docs/examples/os-discovery.md) - Environment understanding examples
- [System Monitoring Examples](./docs/examples/system-monitoring.md) - Resource monitoring examples
- [Environment Debugging Examples](./docs/examples/environment-debugging.md) - Troubleshooting examples

### Development
- [Development Guide](./docs/development.md) - Development setup and contribution guidelines

## Project Structure

```
ShellExecution/
├── src/main/java/me/touchie771/ShellExecution/
│   ├── ShellExecutionApplication.java  # Main application
│   ├── Terminal.java                   # Command execution
│   ├── CommandHistory.java             # History management
│   ├── AsyncProcessManager.java        # Background processes
│   ├── OsInfo.java                     # OS information tools
│   ├── SystemResources.java            # System resource monitoring
│   └── EnvironmentTools.java           # Environment and properties tools
├── docs/                               # Documentation
│   ├── tools/                          # Tools documentation
│   │   ├── README.md                   # Tools overview
│   │   ├── core-tools.md               # Core command execution tools
│   │   ├── os-info.md                  # OS information tools
│   │   ├── system-resources.md         # System resource tools
│   │   └── environment-tools.md        # Environment tools
│   ├── examples/                       # Usage examples
│   │   ├── README.md                   # Examples overview
│   │   ├── os-discovery.md             # OS discovery examples
│   │   ├── system-monitoring.md        # System monitoring examples
│   │   └── environment-debugging.md    # Environment debugging examples
│   └── development.md                  # Development guide
└── build.gradle
```

## Dependencies

- Spring Boot 3.5.7
- Spring AI MCP Server 1.0.3
- Java 21

## License

MIT License