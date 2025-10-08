package deors.training.langchain4j;

class OllamaMistralSmallSimpleContext {

    void main() {
        // mistral-small:22b model running locally with Ollama
        OllamaBaseSimpleContext.simpleContext("http://localhost:11434", "mistral-small:22b");
    }
}
