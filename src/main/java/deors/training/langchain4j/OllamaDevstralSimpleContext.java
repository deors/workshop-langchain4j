package deors.training.langchain4j;

class OllamaDevstralSimpleContext {

    void main() {
        // devstral:24b model running locally with Ollama
        OllamaBaseSimpleContext.simpleContext("http://localhost:11434", "devstral:24b");
    }
}
