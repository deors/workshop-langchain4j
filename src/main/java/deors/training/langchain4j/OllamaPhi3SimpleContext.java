package deors.training.langchain4j;

class OllamaPhi3SimpleContext {

    void main() {
        // phi3:3.8b model running locally with Ollama
        OllamaBaseSimpleContext.simpleContext("http://localhost:11434", "phi3:3.8b");
    }
}
