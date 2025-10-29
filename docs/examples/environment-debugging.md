# Environment Debugging Examples

Examples for debugging environment variables and system properties.

## Environment Variable Debugging

```bash
# Check specific environment variables
get-environment-variable ["PATH"]
get-environment-variable ["HOME"]
get-environment-variable ["JAVA_HOME"]
get-environment-variable ["SHELL"]

# Debug PATH variable
get-environment-variable ["PATH"]
# Then check if specific tools are in PATH
execute-command ["which", "java"]
execute-command ["which", "git"]
execute-command ["which", "node"]
```

## Java Environment Debugging

```bash
# Check Java-related environment
get-environment-variable ["JAVA_HOME"]
get-environment-variable ["CLASSPATH"]
get-java-version
get-java-home

# Check Java system properties
get-system-property ["java.home"]
get-system-property ["java.class.path"]
get-system-property ["java.library.path"]

# Verify Java installation
execute-command ["java", "-version"]
execute-command ["javac", "-version"]
```

## System Properties Investigation

```bash
# Get all system properties for debugging
get-all-system-properties

# Check specific properties
get-system-property ["user.name"]
get-system-property ["user.dir"]
get-system-property ["user.home"]
get-system-property ["file.separator"]
get-system-property ["path.separator"]
get-system-property ["line.separator"]
```

## Environment Validation Script

```bash
# Validate development environment
validate_environment() {
    echo "=== Environment Validation ==="
    
    # Check basic environment
    echo "User: $(get-username)"
    echo "Home: $(get-home-directory)"
    echo "Working: $(get-working-directory)"
    echo "Shell: $(get-shell-type)"
    
    # Check Java environment
    echo ""
    echo "=== Java Environment ==="
    echo "Java Version: $(get-java-version)"
    echo "Java Home: $(get-java-home)"
    echo "JAVA_HOME: $(get-environment-variable ["JAVA_HOME"])"
    
    # Check PATH
    echo ""
    echo "=== PATH Analysis ==="
    echo "PATH: $(get-environment-variable ["PATH"])"
    
    # Check required tools
    echo ""
    echo "=== Required Tools ==="
    execute-command ["which", "git"]
    execute-command ["which", "java"]
    execute-command ["which", "javac"]
}

# Run validation
validate_environment
```

## Cross-Platform Environment Setup

```bash
# Detect platform and set up environment accordingly
setup_environment() {
    local os_family=$(get-os-family)
    echo "Detected OS family: $os_family"
    
    case $os_family in
        "Linux")
            echo "Setting up Linux environment..."
            # Linux-specific setup
            get-environment-variable ["DISPLAY"]
            get-environment-variable ["XDG_CONFIG_HOME"]
            ;;
        "Windows")
            echo "Setting up Windows environment..."
            # Windows-specific setup
            get-environment-variable ["USERPROFILE"]
            get-environment-variable ["APPDATA"]
            get-environment-variable ["PROGRAMFILES"]
            ;;
        "Mac")
            echo "Setting up macOS environment..."
            # macOS-specific setup
            get-environment-variable ["HOME"]
            get-environment-variable ["TMPDIR"]
            ;;
        *)
            echo "Unknown OS family: $os_family"
            ;;
    esac
}

setup_environment
```

## Environment Comparison

```bash
# Compare environment variables with expected values
compare_environment() {
    echo "=== Environment Comparison ==="
    
    # Expected vs actual JAVA_HOME
    local expected_java_home="/usr/lib/jvm/java-21-openjdk"
    local actual_java_home=$(get-environment-variable ["JAVA_HOME"])
    
    echo "Expected JAVA_HOME: $expected_java_home"
    echo "Actual JAVA_HOME: $actual_java_home"
    
    if [ "$actual_java_home" != "$expected_java_home" ]; then
        echo "WARNING: JAVA_HOME mismatch"
    fi
    
    # Check Java version consistency
    local java_version=$(get-java-version)
    echo "Java version: $java_version"
    
    # Check if tools are in PATH
    echo ""
    echo "=== PATH Tools Check ==="
    local tools=("git" "java" "javac" "mvn" "gradle")
    
    for tool in "${tools[@]}"; do
        if command -v $tool &> /dev/null; then
            echo "✓ $tool found in PATH"
        else
            echo "✗ $tool not found in PATH"
        fi
    done
}

compare_environment
```

## Environment Backup and Restore

```bash
# Backup current environment
backup_environment() {
    echo "=== Backing up Environment ==="
    
    # Save environment variables
    get-all-environment-variables > env_backup.txt
    
    # Save system properties
    get-all-system-properties > sysprops_backup.txt
    
    # Save current directory info
    echo "Current directory: $(get-working-directory)" >> env_backup.txt
    echo "User: $(get-username)" >> env_backup.txt
    echo "Shell: $(get-shell-type)" >> env_backup.txt
    
    echo "Environment backed up to env_backup.txt and sysprops_backup.txt"
}

# Restore environment (manual process)
restore_environment() {
    echo "=== Environment Restore Instructions ==="
    echo "1. Review env_backup.txt for required environment variables"
    echo "2. Set variables using export VAR=value"
    echo "3. Update ~/.bashrc or ~/.zshrc for persistence"
    echo "4. Restart shell or source the configuration file"
}

backup_environment
```

## Troubleshooting Common Issues

```bash
# Troubleshoot Java-related issues
troubleshoot_java() {
    echo "=== Java Troubleshooting ==="
    
    # Check Java installation
    echo "Java version from system property: $(get-java-version)"
    echo "Java home from system property: $(get-java-home)"
    echo "JAVA_HOME from environment: $(get-environment-variable ["JAVA_HOME"])"
    
    # Check if java command works
    if command -v java &> /dev/null; then
        echo "✓ java command found"
        execute-command ["java", "-version"]
    else
        echo "✗ java command not found"
    fi
    
    # Check PATH for Java
    local path=$(get-environment-variable ["PATH"])
    if [[ $path == *"java"* ]]; then
        echo "✓ Java found in PATH"
    else
        echo "✗ Java not found in PATH"
    fi
}

# Troubleshoot PATH issues
troubleshoot_path() {
    echo "=== PATH Troubleshooting ==="
    echo "Current PATH: $(get-environment-variable ["PATH"])"
    echo ""
    echo "PATH entries:"
    echo $(get-environment-variable ["PATH"]) | tr ':' '\n' | nl
}

troubleshoot_java
troubleshoot_path
```
