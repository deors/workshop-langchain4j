package deors.training.langchain4j;

public class OllamaPhi3AskAboutCode {

    void main() {
        // phi3:3.8b model running locally with Ollama
        OllamaBaseAskAboutCode.askAboutCode("http://localhost:11434", "phi3:3.8b");
    }
}
