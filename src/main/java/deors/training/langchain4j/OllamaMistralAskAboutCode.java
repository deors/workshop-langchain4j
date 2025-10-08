package deors.training.langchain4j;

class OllamaMistralAskAboutCode {

    void main() {
        // mistral:7b model running locally with Ollama
        OllamaBaseAskAboutCode.askAboutCode("http://localhost:11434", "mistral:7b");
    }
}
