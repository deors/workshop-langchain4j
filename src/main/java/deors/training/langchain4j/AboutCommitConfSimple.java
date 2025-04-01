package deors.training.langchain4j;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

import static dev.langchain4j.data.message.UserMessage.userMessage;

public class AboutCommitConfSimple {

    void main() {
        // deepseek-r1:8b model running locally with Ollama
        ChatLanguageModel model = OllamaChatModel.builder()
            .baseUrl("http://localhost:11434")
            .modelName("deepseek-r1:8b")
            .build();

        // define context window
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);

        // ask about Commit Conf 2025
        String messageAskAbout = "When is Commit Conf 2025 going to be? Do you know what are the main topics scheduled?";
        chatMemory.add(userMessage(messageAskAbout));
        System.out.println("\n>>> " + messageAskAbout);

        AiMessage answerAskAbout = model.generate(chatMemory.messages()).content();
        System.out.println(answerAskAbout.text());
        chatMemory.add(answerAskAbout);
    }
}
