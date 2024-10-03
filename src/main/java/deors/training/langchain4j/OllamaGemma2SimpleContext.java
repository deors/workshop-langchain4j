package deors.training.langchain4j;

public class OllamaGemma2SimpleContext {

    void main() {
        // gemma2:9b model running locally with Ollama
        OllamaBaseSimpleContext.simpleContext("http://localhost:11434", "gemma2:9b");
    }
}
