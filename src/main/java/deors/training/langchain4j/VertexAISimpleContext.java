package deors.training.langchain4j;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.vertexai.VertexAiGeminiChatModel;

import static dev.langchain4j.data.message.UserMessage.userMessage;

class VertexAISimpleContext {

    void main() {
        // Vertex AI model
        ChatLanguageModel model = VertexAiGeminiChatModel.builder()
            .project(System.getenv("VERTEXAI_PROJECT_ID"))
            .location("us-central1")
            .modelName("gemini-2.5-flash")
            .build();

        // define context window
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);

        // initial prompt with name and location
        String message = "Hello world! My name is Jorge and I'm writing this from the conference stage.";
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
        message = "What do you know about technical conferences in Spain?";
        chatMemory.add(userMessage(message));
        System.out.println("\n>>> " + message);

        answer = model.generate(chatMemory.messages()).content();
        System.out.println(answer.text());
        chatMemory.add(answer);
    }
}
