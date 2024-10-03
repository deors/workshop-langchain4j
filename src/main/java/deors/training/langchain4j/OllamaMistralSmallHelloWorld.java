package deors.training.langchain4j;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

public class OllamaMistralSmallHelloWorld {

    void main() {
        // mistral-small:22b model running locally with Ollama
        ChatLanguageModel model = OllamaChatModel.builder()
            .baseUrl("http://localhost:11434")
            .modelName("mistral-small:22b")
            .build();

        // the first prompt
        String message = "Hello world!";
        System.out.println("\n>>> " + message);

        String answer = model.generate(message);
        System.out.println(answer);
    }
}
