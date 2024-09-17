package deors.training.langchain4j;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

import static dev.langchain4j.data.message.UserMessage.userMessage;

public class OllamaPhi3SimpleContext {

    void main() {
        // phi3:3.8b model running locally with Ollama
        ChatLanguageModel model = OllamaChatModel.builder()
            .baseUrl("http://localhost:11434")
            .modelName("phi3:3.8b")
            .build();

        // define context window
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);

        // initial prompt with name and location
        String message1 = "Hello world! My name is Jorge and I'm writing this from OpenSouthCode.";
        chatMemory.add(userMessage(message1));
        System.out.println("\n>>> " + message1);

        AiMessage answer = model.generate(chatMemory.messages()).content();
        System.out.println(answer.text());
        chatMemory.add(answer);

        // ask for the name
        String message2 = "What is my name?";
        chatMemory.add(userMessage(message2));
        System.out.println("\n>>> " + message2);

        AiMessage answerWithName = model.generate(chatMemory.messages()).content();
        System.out.println(answerWithName.text());
        chatMemory.add(answerWithName);

        // ask for the location
        String message3 = "From where I'm writing this message?";
        chatMemory.add(userMessage(message3));
        System.out.println("\n>>> " + message3);

        AiMessage answerWithLocation = model.generate(chatMemory.messages()).content();
        System.out.println(answerWithLocation.text());
        chatMemory.add(answerWithLocation);

        // ask about OpenSouthCode
        String message4 = "What do you know about OpenSouthCode?";
        chatMemory.add(userMessage(message4));
        System.out.println("\n>>> " + message4);

        AiMessage answerAboutOSC = model.generate(chatMemory.messages()).content();
        System.out.println(answerAboutOSC.text());
        chatMemory.add(answerAboutOSC);
    }
}
