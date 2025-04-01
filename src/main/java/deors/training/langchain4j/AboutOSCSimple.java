package deors.training.langchain4j;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

import static dev.langchain4j.data.message.UserMessage.userMessage;

public class AboutOSCSimple {

    void main() {
        // deepseek-r1:8b model running locally with Ollama
        ChatLanguageModel model = OllamaChatModel.builder()
            .baseUrl("http://localhost:11434")
            .modelName("deepseek-r1:8b")
            .build();

        // define context window
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);

        // ask about OpenSouthCode 2024
        String messageAboutOSC = "When is OpenSouthCode 2024 going to be? Do you know what are the main topics scheduled?";
        chatMemory.add(userMessage(messageAboutOSC));
        System.out.println("\n>>> " + messageAboutOSC);

        AiMessage answerAboutOSC = model.generate(chatMemory.messages()).content();
        System.out.println(answerAboutOSC.text());
        chatMemory.add(answerAboutOSC);
    }
}
