package deors.training.langchain4j;

public class OllamaDeepSeekAskAboutCode {

    void main() {
        // deepseek-r1:8b model running locally with Ollama
        OllamaBaseAskAboutCode.askAboutCode("http://localhost:11434", "deepseek-r1:8b");
    }
}
