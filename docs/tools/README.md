# Tools Documentation

This directory contains detailed documentation for all available tools in the Shell Execution MCP Server.

## Tool Categories

### Core Tools
- **[Core Tools](./core-tools.md)** - Command execution, process management, and history tools

### OS Information Tools
- **[OS Information](./os-info.md)** - Basic operating system information and user context

### System Resources Tools
- **[System Resources](./system-resources.md)** - System resource monitoring and performance information

### Environment Tools
- **[Environment Tools](./environment-tools.md)** - Environment variables and system properties access

## Quick Reference

| Category | Tool Count | Purpose |
|----------|------------|---------|
| Core Tools | 9 | Command execution and history management |
| OS Information | 12 | OS discovery and user context |
| System Resources | 8 | Resource monitoring and performance |
| Environment Tools | 4 | Environment variables and properties |
| **Total** | **33** | Complete system management |

## Usage Examples

For practical examples of how to use these tools, see the [Examples Directory](../examples/).

## Getting Started

1. Start with [Core Tools](./core-tools.md) for basic command execution
2. Use [OS Information](./os-info.md) to understand your environment
3. Monitor system health with [System Resources](./system-resources.md)
4. Debug environment issues with [Environment Tools](./environment-tools.md)

## Tool Naming Convention

All tools follow a consistent naming pattern:
- `get-*` - Retrieve information
- `execute-command` - Run commands synchronously
- `start-command-async` - Run commands asynchronously
- `check-command-status` - Monitor background processes
- `stop-command` - Terminate background processes
- `list-background-processes` - List all background processes
- `clear-command-history` - Clear history
- `save-to-file` - Save history to file
- `load-from-file` - Load history from file
