# Usage Examples

This document provides practical examples of how to use the Shell Execution MCP Server in various scenarios.

## Basic Command Execution

### File Operations
```bash
# List current directory
execute-command ["ls", "-la"]

# Create a directory
execute-command ["mkdir", "new_folder"]

# Copy files
execute-command ["cp", "source.txt", "destination.txt"]

# Remove files (use with caution)
execute-command ["rm", "old_file.txt"]
```

### Git Operations
```bash
# Check git status
execute-command ["git", "status"]

# Add all changes
execute-command ["git", "add", "."]

# Commit changes
execute-command ["git", "commit", "-m", "Update files"]

# Push to remote
execute-command ["git", "push", "origin", "main"]
```

### System Information
```bash
# Check system info
execute-command ["uname", "-a"]

# Check disk usage
execute-command ["df", "-h"]

# Check memory usage
execute-command ["free", "-h"]

# List running processes
execute-command ["ps", "aux"]
```

## Background Process Management

### Development Servers
```bash
# Start a Node.js development server
start-command-async ["npm", "run", "dev"]

# Start a Python development server
start-command-async ["python", "-m", "http.server", "8080"]

# Start a Java Spring Boot application
start-command-async ["java", "-jar", "app.jar"]
```

### Long-running Tasks
```bash
# Download a large file
start-command-async ["wget", "https://example.com/large-file.zip"]

# Process a large dataset
start-command-async ["python", "process_data.py"]

# Run database migrations
start-command-async ["./migrate.sh"]
```

### Monitoring Background Processes
```bash
# List all background processes
list-background-processes

# Check specific process status
check-command-status ["process-id-here"]

# Stop a running process
stop-command ["process-id-here"]
```

## Command History Management

### History Operations
```bash
# View command history
get-command-history

# Clear history
clear-command-history

# Save history to file
save-to-file ["/home/user/my_commands.txt", true]

# Load history from file
load-from-file ["/home/user/my_commands.txt"]
```

### History Backup Strategy
```bash
# Save history with timestamp
execute-command ["date", "+%Y%m%d_%H%M%S"]
save-to-file ["/home/user/commands_20231028_143022.txt", true]
```

## Real-world Scenarios

### Scenario 1: Web Development Workflow
```bash
# 1. Start development server
start-command-async ["npm", "run", "dev"]

# 2. Run tests in background
start-command-async ["npm", "test"]

# 3. Check both processes
list-background-processes

# 4. Make code changes
execute-command ["git", "add", "."]
execute-command ["git", "commit", "-m", "Update features"]

# 5. Check test results
check-command-status ["test-process-id"]

# 6. Stop dev server when done
stop-command ["dev-process-id"]
```

### Scenario 2: Data Processing Pipeline
```bash
# 1. Download data
start-command-async ["wget", "https://data.example.com/dataset.csv"]

# 2. While downloading, prepare processing script
execute-command ["python", "-c", "print('Preparing script...')"]

# 3. Check download status
check-command-status ["download-process-id"]

# 4. Process data when download complete
start-command-async ["python", "process_dataset.py", "dataset.csv"]

# 5. Monitor processing
check-command-status ["processing-process-id"]
```

### Scenario 3: System Maintenance
```bash
# 1. Check system status
execute-command ["df", "-h"]
execute-command ["free", "-h"]

# 2. Clean temporary files
start-command-async ["find", "/tmp", "-type", "f", "-atime", "+7", "-delete"]

# 3. Update system packages
start-command-async ["sudo", "apt-get", "update"]

# 4. Install updates
start-command-async ["sudo", "apt-get", "upgrade", "-y"]

# 5. Monitor update process
list-background-processes
```

### Scenario 4: Continuous Integration
```bash
# 1. Pull latest changes
execute-command ["git", "pull", "origin", "main"]

# 2. Build project
start-command-async ["./gradlew", "build"]

# 3. Run tests in parallel
start-command-async ["./gradlew", "test"]

# 4. Check build status
check-command-status ["build-process-id"]

# 5. If build successful, deploy
execute-command ["./deploy.sh"]
```

## Error Handling Examples

### Handling Command Failures
```bash
# Try to execute command
execute-command ["nonexistent_command"]

# Response: "Command not executed successfully: Cannot run program "nonexistent_command""
```

### Process Not Found
```bash
# Check non-existent process
check-command-status ["invalid-process-id"]

# Response: "Process not found: invalid-process-id"
```

### File Operations
```bash
# Try to save to invalid path
save-to-file ["/invalid/path/file.txt", false]

# Response: "Error saving history: could not create directories: /invalid/path"
```

## Best Practices

### Security
```bash
# Good: Use array arguments
execute-command ["ls", "-la", "/home/user"]

# Avoid: Never concatenate user input
# execute-command ["ls " + userInput]  // DON'T DO THIS
```

### Resource Management
```bash
# Clean up completed processes
list-background-processes
stop-command ["completed-process-id"]  # Remove from tracking
```

### Automation
```bash
# Save history after important operations
execute-command ["git", "push", "origin", "main"]
save-to-file ["/home/user/git_operations.txt", true]
```

## Integration with Other Tools

### Combining with File Operations
```bash
# Create a script file
execute-command ["echo", "#!/bin/bash", ">", "myscript.sh"]
execute-command ["echo", "echo 'Hello World'", ">>", "myscript.sh"]

# Make executable
execute-command ["chmod", "+x", "myscript.sh"]

# Run script in background
start-command-async ["./myscript.sh"]
```

### Working with Logs
```bash
# Monitor log files
start-command-async ["tail", "-f", "/var/log/application.log"]

# Process log data
execute-command ["grep", "ERROR", "/var/log/application.log", ">", "errors.txt"]

# Analyze errors
execute-command ["wc", "-l", "errors.txt"]
```

## Troubleshooting

### Common Issues and Solutions

1. **Process hangs indefinitely**
   ```bash
   # Check process status
   check-command-status ["stuck-process-id"]
   
   # Force stop if needed
   stop-command ["stuck-process-id"]
   ```

2. **Permission denied errors**
   ```bash
   # Check file permissions
   execute-command ["ls", "-la", "problematic_file.txt"]
   
   # Fix permissions if needed
   execute-command ["chmod", "644", "problematic_file.txt"]
   ```

3. **Command not found**
   ```bash
   # Check if command exists
   execute-command ["which", "command_name"]
   
   # Check PATH
   execute-command ["echo", "$PATH"]
   ```

These examples demonstrate the flexibility and power of the MCP server for various automation and system management tasks.