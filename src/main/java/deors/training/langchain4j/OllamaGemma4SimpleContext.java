package deors.training.langchain4j;

class OllamaGemma4SimpleContext {

    void main() {
        // gemma4:e4b model running locally with Ollama
        OllamaBaseSimpleContext.simpleContext("http://localhost:11434", "gemma4:e4b");
    }
}
