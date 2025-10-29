# Environment and Properties Tools

These tools provide access to environment variables and system properties.

## Available Tools

### get-environment-variable
Get a specific environment variable by name.

**Parameters:**
- `variableName` (String): Name of the environment variable to retrieve

**Returns:** String containing the environment variable value, or null if not found

**Example:**
```json
{
  "command": "get-environment-variable",
  "arguments": {
    "variableName": "PATH"
  }
}
```

### get-all-environment-variables
Get all environment variables as a map.

**Parameters:** None

**Returns:** Map containing all environment variables and their values

### get-system-property
Get a specific system property by name.

**Parameters:**
- `propertyName` (String): Name of the system property to retrieve

**Returns:** String containing the system property value, or null if not found

**Example:**
```json
{
  "command": "get-system-property",
  "arguments": {
    "propertyName": "user.timezone"
  }
}
```

### get-all-system-properties
Get all system properties as a map.

**Parameters:** None

**Returns:** Map containing all system properties and their values

## Usage Examples

```bash
# Get specific environment variables
get-environment-variable ["PATH"]
get-environment-variable ["HOME"]
get-environment-variable ["JAVA_HOME"]

# Get all environment variables for debugging
get-all-environment-variables

# Get system properties
get-system-property ["user.timezone"]
get-system-property ["file.separator"]
get-system-property ["line.separator"]

# Get all system properties
get-all-system-properties
```

## Common Environment Variables

```bash
# Path-related
get-environment-variable ["PATH"]
get-environment-variable ["HOME"]
get-environment-variable ["PWD"]

# Java-related
get-environment-variable ["JAVA_HOME"]
get-environment-variable ["CLASSPATH"]

# Shell-related
get-environment-variable ["SHELL"]
get-environment-variable ["TERM"]

# System-related
get-environment-variable ["USER"]
get-environment-variable ["LANG"]
get-environment-variable ["DISPLAY"]
```

## Common System Properties

```bash
# User information
get-system-property ["user.name"]
get-system-property ["user.home"]
get-system-property ["user.dir"]

# Java information
get-system-property ["java.version"]
get-system-property ["java.vendor"]
get-system-property ["java.home"]

# File system
get-system-property ["file.separator"]
get-system-property ["path.separator"]
get-system-property ["line.separator"]

# Operating system
get-system-property ["os.name"]
get-system-property ["os.version"]
get-system-property ["os.arch"]

# Temporary directory
get-system-property ["java.io.tmpdir"]
```
