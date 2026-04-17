package deors.training.langchain4j;

class OllamaGemma4AskAboutCode {

    void main() {
        // gemma4:e4b model running locally with Ollama
        OllamaBaseAskAboutCode.askAboutCode("http://localhost:11434", "gemma4:e4b");
    }
}
