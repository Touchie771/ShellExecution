# Development Documentation

This document provides information for developers working on the Shell Execution MCP Server.

## Project Architecture

The project follows a modular architecture with three main service components:

### Core Components

1. **ShellExecutionApplication.java** - Main Spring Boot application class
   - Configures and registers all MCP tools
   - Handles dependency injection

2. **Terminal.java** - Synchronous command execution
   - Executes commands and blocks until completion
   - Integrates with command history
   - Handles basic error reporting

3. **CommandHistory.java** - Command history management
   - In-memory storage of executed commands
   - File persistence operations
   - History manipulation utilities

4. **AsyncProcessManager.java** - Background process management
   - Tracks background processes with unique IDs
   - Provides status monitoring and control
   - Handles process lifecycle management

## Adding New Tools

To add a new MCP tool:

1. Create a new service class or add to an existing one
2. Use the `@Service` annotation for Spring component detection
3. Add the `@Tool` annotation to your method:
   ```java
   @Tool(name = "your-tool-name", description = "Tool description")
   public String yourTool(String param1, int param2) {
       // Implementation
   }
   ```
4. Register the service in `ShellExecutionApplication.java`:
   ```java
   @Bean
   public List<ToolCallback> tools(Terminal terminal, CommandHistory commandHistory, 
                                   AsyncProcessManager asyncProcessManager, YourService yourService) {
       return List.of(ToolCallbacks.from(terminal, commandHistory, asyncProcessManager, yourService));
   }
   ```

## Tool Development Guidelines

### Parameter Handling
- Use primitive types or simple objects as parameters
- Provide clear parameter descriptions in the `@Tool` annotation
- Validate inputs and handle edge cases

### Error Handling
- Return descriptive error messages
- Use try-catch blocks for external operations
- Consider security implications of command execution

### Return Values
- Return strings that are human-readable
- Include relevant status information
- Format output for easy parsing

## Security Considerations

### Command Injection Prevention
- Never concatenate user input directly into commands
- Use array format for command arguments
- Validate and sanitize inputs when possible

### File System Access
- Validate file paths to prevent directory traversal
- Check file permissions before operations
- Use absolute paths for consistency

### Process Management
- Limit the number of concurrent background processes
- Implement timeouts for long-running operations
- Clean up resources properly

## Configuration

### Environment Variables
The server can be configured using environment variables or application properties:

```properties
# application.properties
server.port=8080
logging.level.me.touchie771.ShellExecution=DEBUG
```

### Maven/Gradle Dependencies
Current dependencies are managed through Gradle:
```gradle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter'
    implementation 'org.springframework.ai:spring-ai-mcp-server:1.0.3'
}
```

## Testing

### Unit Testing
Create unit tests for individual tools:
```java
@SpringBootTest
class TerminalTest {
    @Autowired
    private Terminal terminal;
    
    @Test
    void testExecuteCommand() {
        String result = terminal.executeCommand(new String[]{"echo", "test"});
        assertEquals("test\n", result);
    }
}
```

### Integration Testing
Test the MCP server integration:
```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class McpIntegrationTest {
    // Test MCP protocol communication
}
```

## Building and Deployment

### Local Development
```bash
# Build the project
./gradlew build

# Run in development mode
./gradlew bootRun

# Run tests
./gradlew test
```

### Production Deployment
```bash
# Build executable JAR
./gradlew bootJar

# Run the JAR
java -jar build/libs/ShellExecution-0.0.1-SNAPSHOT.jar

# Run with specific configuration
java -jar build/libs/ShellExecution-0.0.1-SNAPSHOT.jar --server.port=8080
```

## Debugging

### Logging
Configure logging levels in `application.properties`:
```properties
logging.level.me.touchie771.ShellExecution=DEBUG
logging.level.org.springframework.ai=DEBUG
```

### Common Issues

1. **Process Not Found**: Ensure command exists in PATH
2. **Permission Denied**: Check file/directory permissions
3. **Memory Issues**: Monitor background process count
4. **Port Conflicts**: Change server port if needed

## Performance Considerations

### Background Process Limits
- Monitor memory usage with many background processes
- Consider implementing process cleanup strategies
- Add configuration limits for concurrent processes

### History Management
- Large command histories consume memory
- Implement history size limits if needed
- Consider database persistence for large-scale usage

## Future Enhancements

### Potential Features
1. **Process Scheduling**: Cron-like functionality
2. **Resource Monitoring**: CPU/memory usage tracking
3. **Command Templates**: Reusable command sequences
4. **Web Dashboard**: Process monitoring interface
5. **Database Integration**: Persistent storage for history and processes

### Architecture Improvements
1. **Event-driven Design**: Use Spring Events for decoupling
2. **Caching Layer**: Redis for distributed deployments
3. **Security Layer**: Authentication and authorization
4. **Metrics Collection**: Micrometer for monitoring

## Contributing

### Code Style
- Follow Java conventions
- Use meaningful variable and method names
- Add Javadoc comments for public methods

### Pull Request Process
1. Fork the repository
2. Create a feature branch
3. Add tests for new functionality
4. Ensure all tests pass
5. Submit a pull request with description

### Issue Reporting
- Use GitHub Issues for bug reports
- Include logs and reproduction steps
- Provide environment details