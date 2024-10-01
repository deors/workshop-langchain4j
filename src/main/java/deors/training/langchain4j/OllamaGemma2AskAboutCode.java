package deors.training.langchain4j;

public class OllamaGemma2AskAboutCode {

    void main() {
        // gemma2:9b model running locally with Ollama
        OllamaBaseAskAboutCode.askAboutCode("http://localhost:11434", "gemma2:9b");
    }
}
