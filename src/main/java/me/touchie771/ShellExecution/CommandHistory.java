package me.touchie771.ShellExecution;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommandHistory {

    private final List<String> commandHistory = new ArrayList<>();

    @Tool(name = "clear-command-history", description = "Clears the command history")
    public void clearCommandHistory() {
        commandHistory.clear();
    }

    @Tool(name = "get-command-history", description = "Gets the command history")
    public List<String> getCommandHistory() {
        return commandHistory;
    }

    @Tool(name = "save-to-file", description = "Saves current command history to a file")
    public String saveToFile(String absolutePath) {
        File file = new File(absolutePath);
        if (file.exists()) {
            return "File already exists!";
        }
        try {
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                boolean created = parent.mkdirs();
                if (!created) {
                    return "Error saving history: could not create directories: " + parent.getAbsolutePath();
                }
            }
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
                for (String command : commandHistory) {
                    writer.write(command);
                    writer.newLine();
                }
                return "History saved successfully! Saved " + commandHistory.size() + " commands.";
            }
        } catch (Exception e) {
            return "Error saving history: " + e.getMessage();
        }
    }

    @Tool(name = "load-from-file", description = "Loads command history from a file")
    public String loadFromFile(String absolutePath) {
        File file = new File(absolutePath);
        if (!file.exists()) {
            return "File doesn't exist!";
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            commandHistory.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    commandHistory.add(line);
                }
            }
            return "History loaded successfully! Loaded " + commandHistory.size() + " commands.";
        } catch (Exception e) {
            return "Error loading history: " + e.getMessage();
        }
    }

    // Helper methods

    public void addToCommandHistory(String command) {
        commandHistory.add(command);
    }
}