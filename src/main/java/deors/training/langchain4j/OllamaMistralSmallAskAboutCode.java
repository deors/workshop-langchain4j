package deors.training.langchain4j;

class OllamaMistralSmallAskAboutCode {

    void main() {
        // mistral-small:22b model running locally with Ollama
        OllamaBaseAskAboutCode.askAboutCode("http://localhost:11434", "mistral-small:22b");
    }
}
