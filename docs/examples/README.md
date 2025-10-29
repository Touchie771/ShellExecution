# Usage Examples

This directory contains practical examples of how to use the Shell Execution MCP Server in various scenarios.

## Example Categories

### OS Discovery Examples
- **[OS Discovery](./os-discovery.md)** - Examples for discovering and understanding the operating system environment

### System Monitoring Examples
- **[System Monitoring](./system-monitoring.md)** - Examples for monitoring system resources and performance

### Environment Debugging Examples
- **[Environment Debugging](./environment-debugging.md)** - Examples for debugging environment variables and system properties

## Example Topics

### OS Discovery
- Basic OS information gathering
- User and directory context understanding
- Java runtime information
- Cross-platform scripting
- Environment validation
- System capability assessment

### System Monitoring
- Memory usage monitoring
- System performance tracking
- Disk space monitoring
- System health check workflows
- Resource planning
- Automated monitoring scripts
- Performance troubleshooting
- Resource alerts

### Environment Debugging
- Environment variable debugging
- Java environment troubleshooting
- System properties investigation
- Environment validation scripts
- Cross-platform environment setup
- Environment comparison
- Environment backup and restore
- Troubleshooting common issues

## Getting Started

1. **Begin with OS Discovery** - Understand your environment first
2. **Move to System Monitoring** - Track system health and performance
3. **Use Environment Debugging** - Solve configuration and setup issues

## Real-world Scenarios

### Development Environment Setup
```bash
# Start with OS discovery
get-os-family
get-java-version

# Validate environment
validate_environment  # from environment-debugging.md

# Monitor system resources
get-system-info
get-memory-info
```

### Production Monitoring
```bash
# System health check
check_system_resources  # from system-monitoring.md

# Performance monitoring
monitor_resources  # from system-monitoring.md
```

### Troubleshooting
```bash
# Environment issues
troubleshoot_java  # from environment-debugging.md
troubleshoot_path  # from environment-debugging.md

# Performance issues
check_system_resources  # from system-monitoring.md
```

## Best Practices

1. **Always validate environment** before running critical operations
2. **Monitor system resources** during intensive tasks
3. **Use cross-platform detection** for portable scripts
4. **Log environment state** for debugging
5. **Set up monitoring alerts** for production systems

## Integration Examples

### Complete Workflow Example
```bash
# 1. Environment discovery (os-discovery.md)
get-system-info
validate_environment

# 2. Resource monitoring (system-monitoring.md)
check_system_resources

# 3. Execute commands (core-tools.md)
execute-command ["./build.sh"]

# 4. Monitor results (system-monitoring.md)
get-system-load-average
get-memory-info

# 5. Debug if needed (environment-debugging.md)
troubleshoot_java
```

### Automation Script Example
```bash
#!/bin/bash
# Automated deployment script with monitoring

# Environment validation
validate_environment

# Resource check
if [$(get-free-memory) < 1000000000]; then
    echo "Insufficient memory"
    exit 1
fi

# Deployment
start-command-async ["./deploy.sh"]

# Monitor deployment
while [$(check-command-status ["deploy-id"]) == "RUNNING"]; do
    get-system-load-average
    sleep 5
done

# Cleanup
save-to-file ["/var/log/deployment_history.txt", true]
```

These examples provide practical, real-world guidance for using the MCP server effectively in various scenarios.
