package deors.training.langchain4j;

public class OllamaNemotronMiniSimpleContext {

    void main() {
        // nemotron-mini:4b model running locally with Ollama
        OllamaBaseSimpleContext.simpleContext("http://localhost:11434", "nemotron-mini:4b");
    }
}
