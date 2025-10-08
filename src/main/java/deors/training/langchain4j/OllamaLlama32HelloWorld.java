package deors.training.langchain4j;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

class OllamaLlama32HelloWorld {

    void main() {
        // llama3.2:3b model running locally with Ollama
        ChatLanguageModel model = OllamaChatModel.builder()
            .baseUrl("http://localhost:11434")
            .modelName("llama3.2:3b")
            .build();

        // the first prompt
        String message = "Hello world!";
        System.out.println("\n>>> " + message);

        String answer = model.generate(message);
        System.out.println(answer);
    }
}
