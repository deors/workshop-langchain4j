package deors.training.langchain4j;

public class OllamaGemmaSimpleContext {

    void main() {
        // gemma:2b model running locally with Ollama
        OllamaBaseSimpleContext.simpleContext("http://localhost:11434", "gemma:2b");
    }
}
