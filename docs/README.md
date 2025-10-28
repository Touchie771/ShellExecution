# Shell Execution MCP Server Documentation

Welcome to the documentation for the Shell Execution MCP Server. This folder contains comprehensive documentation about the server's features, tools, and usage.

## Documentation Structure

- **[tools.md](./tools.md)** - Complete reference for all available MCP tools and their parameters
- **[development.md](./development.md)** - Developer guide for contributing to or extending the server
- **[usage-examples.md](./usage-examples.md)** - Practical examples and common usage patterns

## Quick Reference

### Essential Tools

| Tool | Purpose | Key Use Cases |
|------|---------|---------------|
| `execute-command` | Run commands synchronously | Quick operations, git commands |
| `start-command-async` | Run commands in background | Dev servers, long-running tasks |
| `check-command-status` | Monitor background processes | Check completion, view output |
| `stop-command` | Terminate background processes | Stop dev servers, cancel long tasks |
| `get-command-history` | View executed commands | Review operations, debugging |
| `save-to-file` | Persist command history | Backup, share commands |

### Common Workflows

1. **Development Setup**: Start dev server in background, monitor status
2. **Data Processing**: Download data, process in background, monitor progress
3. **System Administration**: Run maintenance tasks, track progress
4. **CI/CD Pipeline**: Build, test, deploy operations with monitoring

For detailed information, see the specific documentation files listed above.