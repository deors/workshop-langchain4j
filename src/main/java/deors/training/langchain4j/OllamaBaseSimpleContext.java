package deors.training.langchain4j;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

import static dev.langchain4j.data.message.UserMessage.userMessage;

public class OllamaBaseSimpleContext {

    static void simpleContext(String baseUrl, String modelName) {
        // loads a model running locally with Ollama
        ChatLanguageModel model = OllamaChatModel.builder()
            .baseUrl(baseUrl)
            .modelName(modelName)
            .build();

        // define context window
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);

        // initial prompt with name and location
        String message = "Hello world! My name is Jorge and I'm writing this from Commit Conf.";
        chatMemory.add(userMessage(message));
        System.out.println("\n>>> " + message);

        AiMessage answer = model.generate(chatMemory.messages()).content();
        System.out.println(answer.text());
        chatMemory.add(answer);

        // ask for the name
        message = "What is my name?";
        chatMemory.add(userMessage(message));
        System.out.println("\n>>> " + message);

        answer = model.generate(chatMemory.messages()).content();
        System.out.println(answer.text());
        chatMemory.add(answer);

        // ask for the location
        message = "From where I'm writing this message?";
        chatMemory.add(userMessage(message));
        System.out.println("\n>>> " + message);

        answer = model.generate(chatMemory.messages()).content();
        System.out.println(answer.text());
        chatMemory.add(answer);

        // ask about the event
        message = "What do you know about Commit Conf?";
        chatMemory.add(userMessage(message));
        System.out.println("\n>>> " + message);

        answer = model.generate(chatMemory.messages()).content();
        System.out.println(answer.text());
        chatMemory.add(answer);
    }
}
