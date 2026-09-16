# Requirements & Installation Guide

To run the **Ollama Code Reviewer**, your system needs the following tools installed. Follow the instructions for your Operating System.

## 1. Java Development Kit (JDK) 21+
You need Java 21 or newer to compile and run the application.

*   **Windows:** Download the installer from [Adoptium](https://adoptium.net/) or use winget: `winget install Microsoft.OpenJDK.21`
*   **macOS:** Use Homebrew: `brew install openjdk@21`
*   **Linux (Ubuntu/Debian):** `sudo apt install openjdk-21-jdk`

## 2. Apache Maven
Maven is required to build the project and download the LangChain4j dependencies.

*   **Windows:** Use Chocolatey: `choco install maven` or download from the [Maven website](https://maven.apache.org/download.cgi) and add it to your system PATH.
*   **macOS:** Use Homebrew: `brew install maven`
*   **Linux (Ubuntu/Debian):** `sudo apt install maven`

## 3. Git
Git must be installed and accessible in your terminal so the tool can extract diffs.

*   **Windows:** Download [Git for Windows](https://git-scm.com/download/win) or use winget: `winget install Git.Git`
*   **macOS:** Use Homebrew: `brew install git` or run `git --version` to trigger the Xcode command line tools prompt.
*   **Linux (Ubuntu/Debian):** `sudo apt install git`

## 4. Ollama & LLM Model
Ollama runs the AI models locally on your machine.

1.  **Install Ollama:**
    *   **Windows & macOS:** Download the installer directly from [ollama.com](https://ollama.com).
    *   **Linux:** Run the install script: `curl -fsSL https://ollama.com/install.sh | sh`
2.  **Pull the Default Model:**
    *   Once Ollama is installed, open your terminal and run: 
        ```bash
        ollama pull llama3.1
        ```
    *   *(Note: The CLI defaults to `llama3.1`. You can pull others like `qwen2.5-coder` and use the `-m` flag to specify them).*
    *   Make sure Ollama is running in the background before trying to run the code reviewer. It typically runs on `http://localhost:11434`.
