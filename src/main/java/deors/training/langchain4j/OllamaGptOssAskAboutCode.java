package deors.training.langchain4j;

class OllamaGptOssAskAboutCode {

    void main() {
        // gpt-oss:20b model running locally with Ollama
        OllamaBaseAskAboutCode.askAboutCode("http://localhost:11434", "gpt-oss:20b");
    }
}
