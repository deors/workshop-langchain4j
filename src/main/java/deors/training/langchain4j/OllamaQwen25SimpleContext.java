package deors.training.langchain4j;

class OllamaQwen25SimpleContext {

    void main() {
        // qwen2.5:7b model running locally with Ollama
        OllamaBaseSimpleContext.simpleContext("http://localhost:11434", "qwen2.5:7b");
    }
}
