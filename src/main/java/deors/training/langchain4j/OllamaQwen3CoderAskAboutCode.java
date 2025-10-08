package deors.training.langchain4j;

class OllamaQwen3CoderAskAboutCode {

    void main() {
        // qwen3-coder:30b model running locally with Ollama
        OllamaBaseAskAboutCode.askAboutCode("http://localhost:11434", "qwen3-coder:30b");
    }
}
