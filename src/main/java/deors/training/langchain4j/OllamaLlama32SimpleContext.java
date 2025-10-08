package deors.training.langchain4j;

class OllamaLlama32SimpleContext {

    void main() {
        // llama3.2:3b model running locally with Ollama
        OllamaBaseSimpleContext.simpleContext("http://localhost:11434", "llama3.2:3b");
    }
}
