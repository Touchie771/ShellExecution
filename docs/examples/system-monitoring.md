# System Monitoring Examples

Examples for monitoring system resources and performance.

## Memory Usage Monitoring

```bash
# Basic memory information
get-free-memory
get-total-memory
get-max-memory

# Comprehensive memory details
get-memory-info

# Sample memory info output:
# {
#   "maxMemory": 4294967296,
#   "totalMemory": 251658240,
#   "freeMemory": 184549376,
#   "usedMemory": 67108864,
#   "heapMemoryUsage": {...},
#   "nonHeapMemoryUsage": {...}
# }
```

## System Performance Monitoring

```bash
# Check system load
get-system-load-average
get-available-processors

# Get comprehensive system overview
get-system-info

# Sample system info output:
# {
#   "osName": "Linux",
#   "osVersion": "5.15.0-91-generic",
#   "osArchitecture": "amd64",
#   "osFamily": "Linux",
#   "availableProcessors": 8,
#   "systemLoadAverage": 0.45,
#   "javaVersion": "21.0.2",
#   "javaVendor": "Ubuntu",
#   "userName": "developer",
#   "userHome": "/home/developer",
#   "userDir": "/home/developer/project",
#   "is64Bit": true
# }
```

## Disk Space Monitoring

```bash
# Check disk space for critical directories
get-disk-space-info ["/"]
get-disk-space-info ["/home"]
get-disk-space-info ["/var"]
get-disk-space-info ["/tmp"]

# Check current directory disk space
get-disk-space-info []

# Sample output:
# {
#   "totalSpace": 107374182400,
#   "freeSpace": 53687091200,
#   "usableSpace": 53687091200
# }
```

## System Health Check Workflow

```bash
# 1. Comprehensive system health assessment
get-system-info
get-memory-info
get-disk-space-info ["/"]
get-system-load-average

# 2. Check resource thresholds
get-available-processors
get-free-memory

# 3. Monitor specific directories
get-disk-space-info ["/var/log"]
get-disk-space-info ["/tmp"]

# 4. Check system performance metrics
get-system-load-average
```

## Resource Planning Examples

```bash
# Check available resources before starting heavy operations
get-available-processors
get-free-memory
get-disk-space-info ["/tmp"]

# Make decisions based on available resources
if [$(get-free-memory) < 1000000000]; then  # Less than 1GB
    echo "Warning: Low memory available"
fi

if [$(get-system-load-average) > 2.0]; then
    echo "Warning: High system load detected"
    execute-command ["ps", "aux", "--sort=-%cpu", "|", "head", "-10"]
fi
```

## Automated Monitoring Script

```bash
# Function to check system resources
check_system_resources() {
    echo "=== System Resource Check ==="
    get-system-info
    echo ""
    echo "=== Memory Usage ==="
    get-memory-info
    echo ""
    echo "=== Disk Space ==="
    get-disk-space-info ["/"]
    echo ""
    echo "=== System Load ==="
    get-system-load-average
}

# Run system check
check_system_resources
```

## Performance Troubleshooting

```bash
# When system is slow, check these metrics
get-system-load-average
get-available-processors
get-memory-info

# Check for high CPU usage
execute-command ["ps", "aux", "--sort=-%cpu", "|", "head", "-10"]

# Check for high memory usage
execute-command ["ps", "aux", "--sort=-%mem", "|", "head", "-10"]

# Check disk I/O
execute-command ["iostat", "-x", "1", "3"]
```

## Resource Alerts

```bash
# Set up resource monitoring alerts
monitor_resources() {
    # Check memory usage
    local free_mem=$(get-free-memory)
    local total_mem=$(get-total-memory)
    local mem_usage=$((($total_mem - $free_mem) * 100 / $total_mem))
    
    if [ $mem_usage -gt 80 ]; then
        echo "ALERT: Memory usage is ${mem_usage}%"
    fi
    
    # Check system load
    local load_avg=$(get-system-load-average)
    local cpu_count=$(get-available-processors)
    
    if (( $(echo "$load_avg > $cpu_count" | bc -l) )); then
        echo "ALERT: System load ($load_avg) exceeds CPU count ($cpu_count)"
    fi
    
    # Check disk space
    local disk_info=$(get-disk-space-info ["/"])
    local free_space=$(echo $disk_info | jq '.freeSpace')
    local total_space=$(echo $disk_info | jq '.totalSpace')
    local disk_usage=$((($total_space - $free_space) * 100 / $total_space))
    
    if [ $disk_usage -gt 90 ]; then
        echo "ALERT: Disk usage is ${disk_usage}%"
    fi
}

# Run monitoring
monitor_resources
```
