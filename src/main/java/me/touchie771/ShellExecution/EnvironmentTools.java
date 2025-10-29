package me.touchie771.ShellExecution;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class EnvironmentTools {

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
}
