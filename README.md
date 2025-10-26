# Shell Execution MCP Server

A Model Context Protocol (MCP) server built with Spring Boot that provides shell command execution capabilities.

## Description

The Shell Execution MCP Server exposes a tool called `execute-command` that allows executing terminal commands and retrieving their output. This enables MCP clients to run shell commands programmatically through the standardized MCP interface.

## Prerequisites

- Java 21 or higher
- Gradle (or use the included Gradle wrapper)

## Building the Project

```bash
./gradlew build
```

## Running the Server

```bash
./gradlew bootRun
```

The server will start using Spring Boot's default configuration.

## Available Tools

### execute-command

Executes a terminal command and returns its output.

**Parameters:**
- `command` (String[]): An array of strings where each element represents the command and its arguments.

**Examples:**
- `["ls", "-la"]` - List files in long format
- `["echo", "hello world"]` - Echo a message
- `["cd", ".."]` - Change directory

**Returns:**
- Success: The command output as a string
- Failure: Error message with exit code or exception details

## Configuration

The server uses Spring Boot's default configuration. You can customize settings by creating an `application.properties` file in `src/main/resources/`.

## Project Structure

```
ShellExecution/
├── src/
│   └── main/
│       └── java/
│           └── me/touchie771/ShellExecution/
│               ├── ShellExecutionApplication.java  # Main application
│               └── Terminal.java                   # Command execution tool
├── build.gradle
└── README.md
```

## Dependencies

- Spring Boot 3.5.7
- Spring AI MCP Server 1.0.3
- Java 21

## License

This project is licensed under the MIT License.