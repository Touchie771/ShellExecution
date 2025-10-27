package me.touchie771.ShellExecution;

import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ShellExecutionApplication {

    public static final CommandHistory commandHistory = new CommandHistory();

	public static void main(String[] args) {
		SpringApplication.run(ShellExecutionApplication.class, args);
	}

    @Bean
    public List<ToolCallback> tools(Terminal terminal) {
        return List.of(ToolCallbacks.from(terminal, commandHistory));
    }
}