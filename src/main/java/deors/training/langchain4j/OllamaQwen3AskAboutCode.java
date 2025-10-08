package deors.training.langchain4j;

class OllamaQwen3AskAboutCode {

    void main() {
        // qwen3:14b model running locally with Ollama
        OllamaBaseAskAboutCode.askAboutCode("http://localhost:11434", "qwen3:14b");
    }
}
