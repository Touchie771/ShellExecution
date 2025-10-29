package me.touchie771.ShellExecution;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class Os {

    @Tool(name = "get-os", description = "Get the current OS name")
    public String getOs() {
        return System.getProperty("os.name");
    }

    @Tool(name = "get-shell-type", description = "Get the current shell type")
    public String getShellType() {
        return System.getenv("SHELL");
    }

    @Tool(name = "get-username", description = "Get the current username")
    public String getUsername() {
        return System.getenv("USER");
    }

    @Tool(name = "get-os-architecture", description = "Get the OS architecture")
    public String getOsArchitecture() {
        return System.getProperty("os.arch");
    }

    @Tool(name = "get-os-version", description = "Get the OS version")
    public String getOsVersion() {
        return System.getProperty("os.version");
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

    @Tool(name = "get-available-processors", description = "Get the number of available processors")
    public int getAvailableProcessors() {
        return Runtime.getRuntime().availableProcessors();
    }

    @Tool(name = "get-max-memory", description = "Get the maximum memory the JVM can use (in bytes)")
    public long getMaxMemory() {
        return Runtime.getRuntime().maxMemory();
    }

    @Tool(name = "get-total-memory", description = "Get the total memory currently allocated to the JVM (in bytes)")
    public long getTotalMemory() {
        return Runtime.getRuntime().totalMemory();
    }

    @Tool(name = "get-free-memory", description = "Get the free memory available to the JVM (in bytes)")
    public long getFreeMemory() {
        return Runtime.getRuntime().freeMemory();
    }

    @Tool(name = "get-system-load-average", description = "Get the system load average for the last minute")
    public double getSystemLoadAverage() {
        return ManagementFactory.getOperatingSystemMXBean().getSystemLoadAverage();
    }

    @Tool(name = "get-disk-space-info", description = "Get disk space information for a given path (defaults to current directory)")
    public Map<String, Long> getDiskSpaceInfo(String path) {
        if (path == null || path.trim().isEmpty()) {
            path = System.getProperty("user.dir");
        }
        File file = new File(path);
        Map<String, Long> spaceInfo = new HashMap<>();
        spaceInfo.put("totalSpace", file.getTotalSpace());
        spaceInfo.put("freeSpace", file.getFreeSpace());
        spaceInfo.put("usableSpace", file.getUsableSpace());
        return spaceInfo;
    }

    @Tool(name = "get-environment-variable", description = "Get a specific environment variable by name")
    public String getEnvironmentVariable(String variableName) {
        if (variableName == null || variableName.trim().isEmpty()) {
            return null;
        }
        return System.getenv(variableName);
    }

    @Tool(name = "get-all-environment-variables", description = "Get all environment variables as a map")
    public Map<String, String> getAllEnvironmentVariables() {
        return System.getenv();
    }

    @Tool(name = "get-system-property", description = "Get a specific system property by name")
    public String getSystemProperty(String propertyName) {
        if (propertyName == null || propertyName.trim().isEmpty()) {
            return null;
        }
        return System.getProperty(propertyName);
    }

    @Tool(name = "get-all-system-properties", description = "Get all system properties as a map")
    public Map<String, String> getAllSystemProperties() {
        Properties props = System.getProperties();
        Map<String, String> propMap = new HashMap<>();
        for (String name : props.stringPropertyNames()) {
            propMap.put(name, props.getProperty(name));
        }
        return propMap;
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

    @Tool(name = "get-memory-info", description = "Get comprehensive memory information")
    public Map<String, Object> getMemoryInfo() {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        Runtime runtime = Runtime.getRuntime();
        
        Map<String, Object> memoryInfo = new HashMap<>();
        memoryInfo.put("maxMemory", runtime.maxMemory());
        memoryInfo.put("totalMemory", runtime.totalMemory());
        memoryInfo.put("freeMemory", runtime.freeMemory());
        memoryInfo.put("usedMemory", runtime.totalMemory() - runtime.freeMemory());
        memoryInfo.put("heapMemoryUsage", memoryBean.getHeapMemoryUsage());
        memoryInfo.put("nonHeapMemoryUsage", memoryBean.getNonHeapMemoryUsage());
        
        return memoryInfo;
    }

    @Tool(name = "get-system-info", description = "Get comprehensive system information")
    public Map<String, Object> getSystemInfo() {
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        Runtime runtime = Runtime.getRuntime();
        
        Map<String, Object> systemInfo = new HashMap<>();
        systemInfo.put("osName", System.getProperty("os.name"));
        systemInfo.put("osVersion", System.getProperty("os.version"));
        systemInfo.put("osArchitecture", System.getProperty("os.arch"));
        systemInfo.put("osFamily", getOsFamily());
        systemInfo.put("availableProcessors", runtime.availableProcessors());
        systemInfo.put("systemLoadAverage", osBean.getSystemLoadAverage());
        systemInfo.put("javaVersion", System.getProperty("java.version"));
        systemInfo.put("javaVendor", System.getProperty("java.vendor"));
        systemInfo.put("userName", System.getProperty("user.name"));
        systemInfo.put("userHome", System.getProperty("user.home"));
        systemInfo.put("userDir", System.getProperty("user.dir"));
        systemInfo.put("is64Bit", is64Bit());
        
        return systemInfo;
    }
}