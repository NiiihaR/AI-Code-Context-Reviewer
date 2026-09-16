package dev.reviewer;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

import java.time.Duration;

public class OllamaConfig {

    private static final String DEFAULT_BASE_URL = "http://localhost:11434";

    public static ChatModel createChatModel(String modelName, double temperature) {
        return OllamaChatModel.builder()
                .baseUrl(DEFAULT_BASE_URL)
                .modelName(modelName)
                .temperature(temperature)
                .timeout(Duration.ofMinutes(5))
                .build();
    }
}
