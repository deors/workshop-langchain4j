package deors.training.langchain4j;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.TokenWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;
import dev.langchain4j.model.openai.OpenAiTokenizer;

import static dev.langchain4j.data.message.UserMessage.userMessage;

public class OpenAISimpleContext {

    void main() {
        // OpenAI model
        ChatLanguageModel model = OpenAiChatModel.builder()
            .apiKey(System.getenv("OPENAI_API_KEY"))
            .build();

        // define context window
        ChatMemory chatMemory = TokenWindowChatMemory.withMaxTokens(
            300, new OpenAiTokenizer(OpenAiChatModelName.GPT_4_O_MINI));

        // initial prompt with name and location
        String message1 = "Hello world! My name is Jorge and I'm writing this from the conference stage.";
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
        String message4 = "What do you know about technical conferences in Spain?";
        chatMemory.add(userMessage(message4));
        System.out.println("\n>>> " + message4);

        AiMessage answerAboutOSC = model.generate(chatMemory.messages()).content();
        System.out.println(answerAboutOSC.text());
        chatMemory.add(answerAboutOSC);
    }
}
