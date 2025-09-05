package deors.training.langchain4j;

public class OllamaQwen3CoderSimpleContext {

    void main() {
        // qwen3-coder:30b model running locally with Ollama
        OllamaBaseSimpleContext.simpleContext("http://localhost:11434", "qwen3-coder:30b");
    }
}
