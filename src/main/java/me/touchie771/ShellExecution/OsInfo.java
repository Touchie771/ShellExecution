package me.touchie771.ShellExecution;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
public class OsInfo {

    @Tool(name = "get-os", description = "Get the current OS name")
    public String getOs() {
        return System.getProperty("os.name");
    }

    @Tool(name = "get-os-architecture", description = "Get the OS architecture")
    public String getOsArchitecture() {
        return System.getProperty("os.arch");
    }

    @Tool(name = "get-os-version", description = "Get the OS version")
    public String getOsVersion() {
        return System.getProperty("os.version");
    }

    @Tool(name = "get-os-family", description = "Get the OS family (Windows, Linux, Mac, Unix, or Unknown)")
    public String getOsFamily() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            return "Windows";
        } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("aix")) {
            return "Linux";
        } else if (osName.contains("mac")) {
            return "Mac";
        } else if (osName.contains("sunos") || osName.contains("solaris")) {
            return "Unix";
        } else {
            return "Unknown";
        }
    }

    @Tool(name = "is-64-bit", description = "Check if the OS is 64-bit")
    public boolean is64Bit() {
        String arch = System.getProperty("os.arch");
        return arch.contains("64");
    }

    @Tool(name = "get-shell-type", description = "Get the current shell type")
    public String getShellType() {
        return System.getenv("SHELL");
    }

    @Tool(name = "get-username", description = "Get the current username")
    public String getUsername() {
        return System.getenv("USER");
    }

    @Tool(name = "get-java-version", description = "Get the Java version")
    public String getJavaVersion() {
        return System.getProperty("java.version");
    }

    @Tool(name = "get-java-home", description = "Get the Java home directory")
    public String getJavaHome() {
        return System.getProperty("java.home");
    }

    @Tool(name = "get-working-directory", description = "Get the current working directory")
    public String getWorkingDirectory() {
        return System.getProperty("user.dir");
    }

    @Tool(name = "get-home-directory", description = "Get the user home directory")
    public String getHomeDirectory() {
        return System.getProperty("user.home");
    }

    @Tool(name = "get-temp-directory", description = "Get the temporary directory")
    public String getTempDirectory() {
        return System.getProperty("java.io.tmpdir");
    }
}
