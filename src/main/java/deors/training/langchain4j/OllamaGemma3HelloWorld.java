package deors.training.langchain4j;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

public class OllamaGemma3HelloWorld {

    void main() {
        // gemma3:12b model running locally with Ollama
        ChatLanguageModel model = OllamaChatModel.builder()
            .baseUrl("http://localhost:11434")
            .modelName("gemma3:12b")
            .build();

        // the first prompt
        String message = "Hello world!";
        System.out.println("\n>>> " + message);

        String answer = model.generate(message);
        System.out.println(answer);
    }
}
