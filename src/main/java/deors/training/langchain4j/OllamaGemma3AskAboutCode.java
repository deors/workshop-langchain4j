package deors.training.langchain4j;

public class OllamaGemma3AskAboutCode {

    void main() {
        // gemma3:12b model running locally with Ollama
        OllamaBaseAskAboutCode.askAboutCode("http://localhost:11434", "gemma3:12b");
    }
}
