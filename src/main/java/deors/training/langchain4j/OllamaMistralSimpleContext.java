package deors.training.langchain4j;

public class OllamaMistralSimpleContext {

    void main() {
        // mistral:7b model running locally with Ollama
        OllamaBaseSimpleContext.simpleContext("http://localhost:11434", "mistral:7b");
    }
}
