package dev.reviewer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GitDiffExtractor {

    public String getDiff(boolean stagedOnly) throws GitExecutionException {
        List<String> command = new ArrayList<>();
        command.add("git");
        command.add("diff");
        
        if (stagedOnly) {
            command.add("--staged");
        } else {
            command.add("HEAD");
        }

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        try {
            Process process = processBuilder.start();
            
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                 BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                 
                String diff = reader.lines().collect(Collectors.joining(System.lineSeparator()));
                String error = errorReader.lines().collect(Collectors.joining(System.lineSeparator()));
                
                int exitCode = process.waitFor();
                
                if (exitCode != 0) {
                    throw new GitExecutionException("Failed to extract git diff (exit code " + exitCode + "): " + error);
                }
                
                return diff;
            }
        } catch (IOException e) {
            throw new GitExecutionException("I/O error while executing git command. Ensure you are in a Git repository.", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new GitExecutionException("Git command execution was interrupted.", e);
        }
    }
}
