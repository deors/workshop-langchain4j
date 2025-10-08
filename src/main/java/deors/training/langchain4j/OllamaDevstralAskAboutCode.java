package deors.training.langchain4j;

class OllamaDevstralAskAboutCode {

    void main() {
        // devstral:24b model running locally with Ollama
        OllamaBaseAskAboutCode.askAboutCode("http://localhost:11434", "devstral:24b");
    }
}
