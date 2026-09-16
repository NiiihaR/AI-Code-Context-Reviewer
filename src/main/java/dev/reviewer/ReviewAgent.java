package dev.reviewer;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface ReviewAgent {

    @SystemMessage("You are an expert Senior Software Engineer reviewing code. You are direct, concise, and only point out actual bugs, security flaws, or severe anti-patterns. Ignore minor formatting. Output your review in clean Markdown.")
    @UserMessage("Review the following git diff and provide actionable feedback:\n\n{{diff}}")
    String reviewCode(@V("diff") String diff);
}
