package deors.training.langchain4j;

class OllamaLlama31SimpleContext {

    void main() {
        // llama3.1:8b model running locally with Ollama
        OllamaBaseSimpleContext.simpleContext("http://localhost:11434", "llama3.1:8b");
    }
}
