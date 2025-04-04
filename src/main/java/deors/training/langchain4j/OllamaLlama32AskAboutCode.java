package deors.training.langchain4j;

public class OllamaLlama32AskAboutCode {

    void main() {
        // llama3.2:3b model running locally with Ollama
        OllamaBaseAskAboutCode.askAboutCode("http://localhost:11434", "llama3.2:3b");
    }
}
