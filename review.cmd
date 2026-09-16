@echo off

echo Compiling the Ollama Code Reviewer...
call mvn clean package -DskipTests

if %ERRORLEVEL% NEQ 0 (
    echo Error: Build failed.
    exit /b %ERRORLEVEL%
)

set JAR_FILE=target\ollama-code-reviewer-1.0.0-SNAPSHOT.jar

if not exist "%JAR_FILE%" (
    echo Error: JAR file not found at %JAR_FILE%
    exit /b 1
)

echo Compilation successful. Running Code Reviewer...
java -jar "%JAR_FILE%" %*
