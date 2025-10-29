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
# Check system info using shell commands
execute-command ["uname", "-a"]
execute-command ["df", "-h"]
execute-command ["free", "-h"]
execute-command ["ps", "aux"]

# OR use the new OS information tools
get-system-info
get-os
get-os-family
get-os-version
get-os-architecture
```

## OS Information and System Resources

### Basic OS Discovery
```bash
# Get comprehensive system overview
get-system-info

# Get specific OS details
get-os                    # "Linux", "Windows 10", etc.
get-os-family            # "Windows", "Linux", "Mac", "Unix", or "Unknown"
get-os-version           # Version string
get-os-architecture      # "amd64", "x86_64", etc.
is-64-bit               # true/false
```

### Java Runtime Information
```bash
# Check Java environment
get-java-version         # "21.0.2", etc.
get-java-home           # "/usr/lib/jvm/java-21-openjdk", etc.

# Useful for debugging Java applications
get-system-property ["java.class.path"]
get-system-property ["java.library.path"]
```

### Directory and User Context
```bash
# Understand current execution context
get-working-directory    # Current directory
get-home-directory       # User home directory
get-temp-directory       # Temp directory
get-username            # Current user
get-shell-type          # Current shell

# Example: Create a user-specific backup path
get-home-directory
# Returns: "/home/username"
# Then use: execute-command ["mkdir", "-p", "/home/username/backups"]
```

### System Resource Monitoring
```bash
# Monitor memory usage
get-memory-info          # Comprehensive memory details
get-free-memory         # Available JVM memory
get-total-memory        # Allocated JVM memory
get-max-memory          # Maximum JVM memory

# Monitor system performance
get-available-processors # CPU core count
get-system-load-average  # System load (last minute)

# Check disk space for specific paths
get-disk-space-info ["/home"]
get-disk-space-info ["/var"]
get-disk-space-info []   # Current directory
```

### Environment and Properties Access
```bash
# Access specific environment variables
get-environment-variable ["PATH"]
get-environment-variable ["HOME"]
get-environment-variable ["JAVA_HOME"]

# Get all environment variables for debugging
get-all-environment-variables

# Access system properties
get-system-property ["user.timezone"]
get-system-property ["file.separator"]
get-system-property ["line.separator"]

# Get all system properties
get-all-system-properties
```

### Practical OS Examples

#### Example 1: System Health Check
```bash
# Comprehensive system health assessment
get-system-info
get-memory-info
get-disk-space-info ["/"]
get-system-load-average

# Sample workflow for monitoring
if [$(get-system-load-average) > 2.0]; then
    execute-command ["ps", "aux", "--sort=-%cpu", "|", "head", "-10"]
fi
```

#### Example 2: Environment Setup Validation
```bash
# Validate development environment
get-java-version
get-java-home
get-environment-variable ["JAVA_HOME"]
get-environment-variable ["PATH"]

# Check if required tools are available
execute-command ["which", "git"]
execute-command ["which", "node"]
execute-command ["which", "python3"]
```

#### Example 3: Cross-Platform Scripting
```bash
# Detect OS family for platform-specific commands
get-os-family
# Returns: "Linux", "Windows", "Mac", etc.

# Then use appropriate commands:
if [$(get-os-family) == "Linux"]; then
    execute-command ["apt-get", "update"]
elif [$(get-os-family) == "Windows"]; then
    execute-command ["choco", "upgrade", "all"]
fi
```

#### Example 4: Resource Planning
```bash
# Check available resources before starting heavy operations
get-available-processors
get-free-memory
get-disk-space-info ["/tmp"]

# Make decisions based on available resources
if [$(get-free-memory) < 1000000000]; then  # Less than 1GB
    echo "Warning: Low memory available"
fi
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