package deors.training.langchain4j;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

public class OpenAIHelloWorld {

    void main() {
        // OpenAI model
        ChatLanguageModel model = OpenAiChatModel.withApiKey(System.getenv("OPENAI_API_KEY"));

        // the first prompt
        String message = "Hello world!";
        System.out.println("\n>>> " + message);

        String answer = model.generate(message);
        System.out.println(answer);
    }
}
