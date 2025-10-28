package me.touchie771.ShellExecution;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AsyncProcessManager {

    private final Map<String, ProcessInfo> runningProcesses = new ConcurrentHashMap<>();
    private final CommandHistory commandHistory;

    public AsyncProcessManager(CommandHistory commandHistory) {
        this.commandHistory = commandHistory;
    }

    @Tool(name = "start-command-async", description = "Starts a command in background and returns a process ID for tracking. Every arg should be a different element of the array, for example: ['sleep', '60']")
    public String startCommandAsync(String[] command) {
        try {
            String processId = UUID.randomUUID().toString();
            Process process = Runtime.getRuntime().exec(command);
            
            ProcessInfo processInfo = new ProcessInfo(
                processId,
                process,
                String.join(" ", command),
                System.currentTimeMillis(),
                ProcessStatus.RUNNING
            );
            
            runningProcesses.put(processId, processInfo);
            commandHistory.addToCommandHistory("[ASYNC] " + processInfo.getCommand());
            
            return "Command started in background. Process ID: " + processId;
        } catch (Exception e) {
            return "Failed to start command: " + e.getMessage();
        }
    }

    @Tool(name = "check-command-status", description = "Check the status of a background command. Returns status, runtime, and output if available.")
    public String checkCommandStatus(String processId) {
        ProcessInfo processInfo = runningProcesses.get(processId);
        if (processInfo == null) {
            return "Process not found: " + processId;
        }

        Process process = processInfo.getProcess();
        ProcessStatus status = processInfo.getStatus();
        
        try {
            if (process.isAlive()) {
                status = ProcessStatus.RUNNING;
                processInfo.setStatus(status);
                return String.format(
                    "Process %s is RUNNING.\nCommand: %s\nStarted: %d seconds ago\nStatus: %s",
                    processId,
                    processInfo.getCommand(),
                    (System.currentTimeMillis() - processInfo.getStartTime()) / 1000,
                    status
                );
            } else {
                int exitCode = process.exitValue();
                status = exitCode == 0 ? ProcessStatus.COMPLETED_SUCCESS : ProcessStatus.COMPLETED_ERROR;
                processInfo.setStatus(status);
                
                String output = getProcessOutput(process);
                return String.format(
                    "Process %s is %s.\nCommand: %s\nExit Code: %d\nDuration: %d seconds\nOutput:\n%s",
                    processId,
                    status,
                    processInfo.getCommand(),
                    exitCode,
                    (System.currentTimeMillis() - processInfo.getStartTime()) / 1000,
                    output.isEmpty() ? "[No output]" : output
                );
            }
        } catch (IllegalThreadStateException e) {
            processInfo.setStatus(ProcessStatus.RUNNING);
            return "Process " + processId + " is still running. Status: " + status;
        }
    }

    @Tool(name = "stop-command", description = "Stops a background command by its process ID")
    public String stopCommand(String processId) {
        ProcessInfo processInfo = runningProcesses.get(processId);
        if (processInfo == null) {
            return "Process not found: " + processId;
        }

        Process process = processInfo.getProcess();
        if (!process.isAlive()) {
            return "Process " + processId + " is already terminated.";
        }

        try {
            process.destroy();
            boolean terminated = process.waitFor(5, java.util.concurrent.TimeUnit.SECONDS);
            
            if (!terminated) {
                process.destroyForcibly();
                process.waitFor(5, java.util.concurrent.TimeUnit.SECONDS);
            }
            
            processInfo.setStatus(ProcessStatus.TERMINATED);
            return "Process " + processId + " has been stopped.";
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "Failed to stop process " + processId + ": " + e.getMessage();
        } catch (Exception e) {
            return "Error stopping process " + processId + ": " + e.getMessage();
        }
    }

    @Tool(name = "list-background-processes", description = "Lists all background processes with their status")
    public String listBackgroundProcesses() {
        if (runningProcesses.isEmpty()) {
            return "No background processes are currently tracked.";
        }

        StringBuilder result = new StringBuilder();
        result.append("Background Processes:\n");
        result.append("===================\n");
        
        for (Map.Entry<String, ProcessInfo> entry : runningProcesses.entrySet()) {
            String processId = entry.getKey();
            ProcessInfo processInfo = entry.getValue();
            
            result.append("ID: ").append(processId).append("\n");
            result.append("Command: ").append(processInfo.getCommand()).append("\n");
            result.append("Status: ").append(processInfo.getStatus()).append("\n");
            
            long runtime = (System.currentTimeMillis() - processInfo.getStartTime()) / 1000;
            result.append("Runtime: ").append(runtime).append(" seconds\n");
            
            if (!processInfo.getProcess().isAlive()) {
                try {
                    result.append("Exit Code: ").append(processInfo.getProcess().exitValue()).append("\n");
                } catch (IllegalThreadStateException e) {
                    result.append("Exit Code: N/A\n");
                }
            }
            
            result.append("---\n");
        }
        
        return result.toString();
    }

    private String getProcessOutput(Process process) {
        StringBuilder output = new StringBuilder();
        
        try (BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = stdoutReader.readLine()) != null) {
                output.append(line).append("\n");
            }
        } catch (Exception e) {
            output.append("Error reading stdout: ").append(e.getMessage()).append("\n");
        }
        
        try (BufferedReader stderrReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
            String line;
            boolean hasStderr = false;
            StringBuilder stderr = new StringBuilder();
            while ((line = stderrReader.readLine()) != null) {
                stderr.append(line).append("\n");
                hasStderr = true;
            }
            if (hasStderr) {
                output.append("STDERR:\n").append(stderr.toString());
            }
        } catch (Exception e) {
            output.append("Error reading stderr: ").append(e.getMessage()).append("\n");
        }
        
        return output.toString();
    }

    private static class ProcessInfo {
        private final Process process;
        private final String command;
        private final long startTime;
        private ProcessStatus status;

        public ProcessInfo(String processId, Process process, String command, long startTime, ProcessStatus status) {
            this.process = process;
            this.command = command;
            this.startTime = startTime;
            this.status = status;
        }

        public Process getProcess() { return process; }
        public String getCommand() { return command; }
        public long getStartTime() { return startTime; }
        public ProcessStatus getStatus() { return status; }
        public void setStatus(ProcessStatus status) { this.status = status; }
    }

    private enum ProcessStatus {
        RUNNING,
        COMPLETED_SUCCESS,
        COMPLETED_ERROR,
        TERMINATED
    }
}
