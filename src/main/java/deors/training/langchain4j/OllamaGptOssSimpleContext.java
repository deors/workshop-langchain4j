package deors.training.langchain4j;

class OllamaGptOssSimpleContext {

    void main() {
        // gpt-oss:20b model running locally with Ollama
        OllamaBaseSimpleContext.simpleContext("http://localhost:11434", "gpt-oss:20b");
    }
}
