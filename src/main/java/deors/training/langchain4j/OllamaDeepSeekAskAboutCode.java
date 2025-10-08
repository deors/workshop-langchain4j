package deors.training.langchain4j;

class OllamaDeepSeekAskAboutCode {

    void main() {
        // deepseek-r1:14b model running locally with Ollama
        OllamaBaseAskAboutCode.askAboutCode("http://localhost:11434", "deepseek-r1:14b");
    }
}
