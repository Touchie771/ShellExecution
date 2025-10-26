package me.touchie771.ShellExecution;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class Terminal {

    @Tool(name = "execute-command", description = "Executes a terminal command and returns the output of it, " +
            "every arg should be a different element of the array, for example: ['cd', '..']")
    public String executeCommand(String[] command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                return output.toString();
            } else {
                return "Command not executed successfully, exit code: " + exitCode;
            }
        } catch (Exception e) {
            return "Command not executed successfully: " + e.getMessage();
        }
    }
}