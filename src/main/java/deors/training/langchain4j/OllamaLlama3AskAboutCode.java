package deors.training.langchain4j;

class OllamaLlama3AskAboutCode {

    void main() {
        // llama3:8b model running locally with Ollama
        OllamaBaseAskAboutCode.askAboutCode("http://localhost:11434", "llama3:8b");
    }
}
