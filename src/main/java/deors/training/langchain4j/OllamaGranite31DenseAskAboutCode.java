package deors.training.langchain4j;

class OllamaGranite31DenseAskAboutCode {

    void main() {
        // granite3.1-dense:8b model running locally with Ollama
        OllamaBaseAskAboutCode.askAboutCode("http://localhost:11434", "granite3.1-dense:8b");
    }
}
