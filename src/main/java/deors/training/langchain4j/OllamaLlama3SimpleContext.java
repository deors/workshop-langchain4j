package deors.training.langchain4j;

class OllamaLlama3SimpleContext {

    void main() {
        // llama3:8b model running locally with Ollama
        OllamaBaseSimpleContext.simpleContext("http://localhost:11434", "llama3:8b");
    }
}
