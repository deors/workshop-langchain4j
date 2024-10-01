package deors.training.langchain4j;

public class OllamaGemmaAskAboutCode {

    void main() {
        // gemma:2b model running locally with Ollama
        OllamaBaseAskAboutCode.askAboutCode("http://localhost:11434", "gemma:2b");
    }
}
