package me.touchie771.ShellExecution;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

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

    // Helper methods

    public void addToCommandHistory(String command) {
        commandHistory.add(command);
    }
}