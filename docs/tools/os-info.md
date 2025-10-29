# OS Information Tools

These tools provide basic operating system information and user context.

## Available Tools

### get-os
Get the current operating system name.

**Parameters:** None

**Returns:** String containing the OS name (e.g., "Linux", "Windows 10", "macOS")

**Example:**
```json
{
  "command": "get-os"
}
```

### get-os-architecture
Get the operating system architecture.

**Parameters:** None

**Returns:** String containing the OS architecture (e.g., "amd64", "x86_64", "aarch64")

### get-os-version
Get the operating system version.

**Parameters:** None

**Returns:** String containing the OS version information

### get-os-family
Get the OS family classification.

**Parameters:** None

**Returns:** String: "Windows", "Linux", "Mac", "Unix", or "Unknown"

### is-64-bit
Check if the operating system is 64-bit.

**Parameters:** None

**Returns:** Boolean: true if 64-bit, false otherwise

### get-shell-type
Get the current shell type.

**Parameters:** None

**Returns:** String containing the shell path (e.g., "/bin/bash", "/bin/zsh")

### get-username
Get the current username.

**Parameters:** None

**Returns:** String containing the current user's name

### get-java-version
Get the Java version being used.

**Parameters:** None

**Returns:** String containing the Java version (e.g., "21.0.2")

### get-java-home
Get the Java home directory.

**Parameters:** None

**Returns:** String containing the Java installation directory path

### get-working-directory
Get the current working directory.

**Parameters:** None

**Returns:** String containing the current working directory path

### get-home-directory
Get the user's home directory.

**Parameters:** None

**Returns:** String containing the user home directory path

### get-temp-directory
Get the temporary directory.

**Parameters:** None

**Returns:** String containing the temporary directory path

## Usage Examples

```bash
# Basic OS discovery
get-os
get-os-family
get-os-version
get-os-architecture

# Check system capabilities
is-64-bit

# Get Java runtime information
get-java-version
get-java-home

# Get current context
get-working-directory
get-home-directory
get-temp-directory
get-username
get-shell-type
```
