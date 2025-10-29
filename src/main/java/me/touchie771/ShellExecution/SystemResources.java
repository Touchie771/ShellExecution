package me.touchie771.ShellExecution;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.util.HashMap;
import java.util.Map;

@Service
public class SystemResources {

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

    private String getOsFamily() {
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

    private boolean is64Bit() {
        String arch = System.getProperty("os.arch");
        return arch.contains("64");
    }
}
