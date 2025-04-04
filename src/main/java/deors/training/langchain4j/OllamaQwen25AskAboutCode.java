package deors.training.langchain4j;

public class OllamaQwen25AskAboutCode {

    void main() {
        // qwen2.5:7b model running locally with Ollama
        OllamaBaseAskAboutCode.askAboutCode("http://localhost:11434", "qwen2.5:7b");
    }
}
