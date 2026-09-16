# Ollama Code Reviewer

A local, AI-powered CLI application that reviews your uncommitted or staged Git changes using Ollama. It catches bugs, security flaws, and severe anti-patterns directly from your terminal.

## Prerequisites & Installation

Before getting started, you need to install Java, Maven, Git, and Ollama. 

**👉 Please see [REQUIREMENTS.md](REQUIREMENTS.md) for step-by-step installation instructions for Windows, macOS, and Linux.**

## Quick Start

Once your prerequisites are installed and the Ollama service is running, clone this repository and use the provided wrapper scripts to build and run the reviewer.

### macOS & Linux (Terminal)
```bash
# Make the script executable (only needed once)
chmod +x review.sh

# Review all changes against HEAD
./review.sh

# Review only staged changes
./review.sh --staged

# Specify a different model and temperature
./review.sh -m qwen2.5-coder -t 0.2
```

### Windows (PowerShell / Command Prompt)
```cmd
:: Review all changes against HEAD
.\review.cmd

:: Review only staged changes
.\review.cmd --staged

:: Specify a different model and temperature
.\review.cmd -m qwen2.5-coder -t 0.2
```

## How It Works
- The tool executes `git diff HEAD` (or `git diff --staged`) natively.
- If changes are detected, it connects to your local Ollama instance (`http://localhost:11434`) using LangChain4j.
- The LLM processes the diff and returns actionable markdown feedback directly in the terminal.

## Troubleshooting
- **Connection refused:** Ensure your local Ollama instance is running (run `ollama serve` or check your system tray).
- **Git error:** Ensure you are executing the tool from within a valid Git repository and `git` is accessible in your environment PATH.
