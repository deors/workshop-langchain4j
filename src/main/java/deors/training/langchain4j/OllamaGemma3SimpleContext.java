package deors.training.langchain4j;

class OllamaGemma3SimpleContext {

    void main() {
        // gemma3:12b model running locally with Ollama
        OllamaBaseSimpleContext.simpleContext("http://localhost:11434", "gemma3:12b");
    }
}
