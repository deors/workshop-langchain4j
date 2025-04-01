package deors.training.langchain4j;

public class OllamaPhi4SimpleContext {

    void main() {
        // phi4:14b model running locally with Ollama
        OllamaBaseSimpleContext.simpleContext("http://localhost:11434", "phi4:14b");
    }
}
