package deors.training.langchain4j;

class OllamaGranite31DenseSimpleContext {

    void main() {
        // granite3.1-dense:8b model running locally with Ollama
        OllamaBaseSimpleContext.simpleContext("http://localhost:11434", "granite3.1-dense:8b");
    }
}
