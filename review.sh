#!/usr/bin/env bash

set -e

echo "Compiling the Ollama Code Reviewer..."
mvn clean package -DskipTests

JAR_FILE="target/ollama-code-reviewer-1.0.0-SNAPSHOT.jar"

if [ ! -f "$JAR_FILE" ]; then
    echo "Error: Build failed or JAR file not found at $JAR_FILE"
    exit 1
fi

echo "Compilation successful. Running Code Reviewer..."
java -jar "$JAR_FILE" "$@"
