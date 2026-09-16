# Ollama Code Reviewer

A local, AI-powered CLI application that reviews your uncommitted or staged Git changes using Ollama.

## Setup

1. **Prerequisites:** Java 21, Maven, Git, and [Ollama](https://ollama.com).
2. Start Ollama: Make sure you have Ollama running locally (it defaults to `http://localhost:11434`).
3. Make the runner executable:
   ```bash
   chmod +x review.sh
   ```

## Usage

Review all changes against HEAD:
```bash
./review.sh
```

Review only staged changes:
```bash
./review.sh --staged
```

Change the model and temperature:
```bash
./review.sh -m qwen2.5-coder -t 0.2
```
