package dev.reviewer;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.service.AiServices;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.time.Duration;
import java.util.concurrent.Callable;

@Command(name = "ollama-reviewer", mixinStandardHelpOptions = true, version = "1.0",
        description = "Reviews git diffs using a local Ollama model.")
public class ReviewCLI implements Callable<Integer> {

    @Option(names = {"-m", "--model"}, description = "Ollama model to use", defaultValue = "llama3.1")
    private String model;

    @Option(names = {"-s", "--staged"}, description = "Only review staged changes")
    private boolean staged;

    @Option(names = {"-t", "--temperature"}, description = "Model temperature", defaultValue = "0.1")
    private double temperature;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new ReviewCLI()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        try {
            GitDiffExtractor extractor = new GitDiffExtractor();
            String diff = extractor.getDiff(staged);

            if (diff == null || diff.trim().isEmpty()) {
                System.out.println("No changes to review.");
                return 0;
            }

            ChatModel chatModel = OllamaConfig.createChatModel(model, temperature);
            ReviewAgent agent = AiServices.create(ReviewAgent.class, chatModel);

            System.out.println("Reviewing changes with " + model + "...");
            String review = agent.reviewCode(diff);
            
            System.out.println("\n" + review);
            return 0;

        } catch (GitExecutionException e) {
            System.err.println("Git error: " + e.getMessage());
            return 1;
        } catch (Exception e) {
            System.err.println("Error connecting to Ollama: " + e.getMessage());
            if (e.getMessage() != null && e.getMessage().toLowerCase().contains("connection refused")) {
                System.err.println("Please ensure Ollama is running at http://localhost:11434");
            }
            return 1;
        }
    }
}
