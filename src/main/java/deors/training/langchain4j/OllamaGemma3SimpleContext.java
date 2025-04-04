package deors.training.langchain4j;

public class OllamaGemma3SimpleContext {

    void main() {
        // gemma3:14b model running locally with Ollama
        OllamaBaseSimpleContext.simpleContext("http://localhost:11434", "gemma3:14b");
    }
}
