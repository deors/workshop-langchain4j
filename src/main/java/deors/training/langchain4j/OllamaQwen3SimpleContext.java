package deors.training.langchain4j;

public class OllamaQwen3SimpleContext {

    void main() {
        // qwen3:14b model running locally with Ollama
        OllamaBaseSimpleContext.simpleContext("http://localhost:11434", "qwen3:14b");
    }
}
