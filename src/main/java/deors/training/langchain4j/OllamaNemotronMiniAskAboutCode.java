package deors.training.langchain4j;

class OllamaNemotronMiniAskAboutCode {

    void main() {
        // nemotron-mini:4b model running locally with Ollama
        OllamaBaseAskAboutCode.askAboutCode("http://localhost:11434", "nemotron-mini:4b");
    }
}
