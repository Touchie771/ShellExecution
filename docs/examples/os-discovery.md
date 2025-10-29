# OS Discovery Examples

Examples for discovering and understanding the operating system environment.

## Basic OS Information Discovery

```bash
# Get comprehensive OS overview
get-os
get-os-family
get-os-version
get-os-architecture
is-64-bit

# Sample output:
# get-os -> "Linux"
# get-os-family -> "Linux"
# get-os-version -> "5.15.0-91-generic"
# get-os-architecture -> "amd64"
# is-64-bit -> true
```

## User and Directory Context

```bash
# Understand current execution context
get-username
get-working-directory
get-home-directory
get-temp-directory
get-shell-type

# Sample output:
# get-username -> "developer"
# get-working-directory -> "/home/bara-mihai/Desktop/ShellExecution"
# get-home-directory -> "/home/bara-mihai"
# get-temp-directory -> "/tmp"
# get-shell-type -> "/bin/bash"
```

## Java Runtime Information

```bash
# Check Java environment
get-java-version
get-java-home

# Sample output:
# get-java-version -> "21.0.2"
# get-java-home -> "/usr/lib/jvm/java-21-openjdk-amd64"
```

## Cross-Platform Scripting

```bash
# Detect OS family for platform-specific commands
get-os-family

# Then use appropriate commands:
if [$(get-os-family) == "Linux"]; then
    execute-command ["apt-get", "update"]
elif [$(get-os-family) == "Windows"]; then
    execute-command ["choco", "upgrade", "all"]
elif [$(get-os-family) == "Mac"]; then
    execute-command ["brew", "update"]
fi
```

## Environment Validation

```bash
# Validate development environment setup
get-java-version
get-java-home
get-environment-variable ["JAVA_HOME"]
get-environment-variable ["PATH"]

# Check if required tools are available
execute-command ["which", "git"]
execute-command ["which", "node"]
execute-command ["which", "python3"]
```

## System Capability Assessment

```bash
# Check system capabilities before running resource-intensive tasks
get-available-processors
is-64-bit
get-os-family

# Make decisions based on capabilities
if [$(get-available-processors) < 4]; then
    echo "Warning: Low CPU core count detected"
fi

if [$(is-64-bit) == false]; then
    echo "Error: 64-bit system required"
fi
```
