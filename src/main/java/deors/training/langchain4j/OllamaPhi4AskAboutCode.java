package deors.training.langchain4j;

class OllamaPhi4AskAboutCode {

    void main() {
        // phi4:14b model running locally with Ollama
        OllamaBaseAskAboutCode.askAboutCode("http://localhost:11434", "phi4:14b");
    }
}
