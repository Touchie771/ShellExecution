# System Resources Tools

These tools provide system resource monitoring and performance information.

## Available Tools

### get-available-processors
Get the number of available processor cores.

**Parameters:** None

**Returns:** Integer containing the number of available processors

### get-max-memory
Get the maximum memory the JVM can use.

**Parameters:** None

**Returns:** Long containing maximum memory in bytes

### get-total-memory
Get the total memory currently allocated to the JVM.

**Parameters:** None

**Returns:** Long containing total memory in bytes

### get-free-memory
Get the free memory available to the JVM.

**Parameters:** None

**Returns:** Long containing free memory in bytes

### get-system-load-average
Get the system load average for the last minute.

**Parameters:** None

**Returns:** Double containing the system load average

### get-disk-space-info
Get disk space information for a given path.

**Parameters:**
- `path` (String, optional): Directory path to check. Defaults to current directory if not provided.

**Returns:** Map with keys: "totalSpace", "freeSpace", "usableSpace" (all in bytes)

**Example:**
```json
{
  "command": "get-disk-space-info",
  "arguments": {
    "path": "/home"
  }
}
```

**Sample Response:**
```json
{
  "totalSpace": 107374182400,
  "freeSpace": 53687091200,
  "usableSpace": 53687091200
}
```

### get-memory-info
Get comprehensive memory information.

**Parameters:** None

**Returns:** Map containing detailed memory usage information including heap and non-heap memory

### get-system-info
Get comprehensive system information.

**Parameters:** None

**Returns:** Map containing detailed system information including OS details, Java info, and system resources

## Usage Examples

```bash
# Monitor memory usage
get-memory-info
get-free-memory
get-total-memory
get-max-memory

# Monitor system performance
get-system-load-average
get-system-info

# Check disk space
get-disk-space-info ["/home"]
get-disk-space-info []  # Current directory

# Check system capabilities
get-available-processors
```

## Resource Monitoring Workflow

```bash
# 1. Get system overview
get-system-info

# 2. Monitor memory usage
get-memory-info

# 3. Check disk space for critical directories
get-disk-space-info ["/"]
get-disk-space-info ["/home"]
get-disk-space-info ["/var"]

# 4. Monitor system load
get-system-load-average

# 5. Check available resources
get-available-processors
get-free-memory
```
