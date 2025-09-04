package deors.training.langchain4j;

public class OllamaDeepSeekSimpleContext {

    void main() {
        // deepseek-r1:14b model running locally with Ollama
        OllamaBaseSimpleContext.simpleContext("http://localhost:11434", "deepseek-r1:14b");
    }
}
