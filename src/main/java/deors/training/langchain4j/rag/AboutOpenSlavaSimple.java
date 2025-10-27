package deors.training.langchain4j.rag;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

import static dev.langchain4j.data.message.UserMessage.userMessage;

class AboutOpenSlavaSimple {

    void main() {
        // qwen3-coder:30b model running locally with Ollama
        ChatLanguageModel model = OllamaChatModel.builder()
            .baseUrl("http://localhost:11434")
            .modelName("qwen3-coder:30b")
            .temperature(0.0)
            .build();

        // define context window
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);

        // ask about OpenSlava 2025
        String[] questions = {
            "When is OpenSlava 2025 going to be?",
            "Do you know what are the main topics scheduled?",
            "What do you know about the venue and how to get there?",
        };

        for (var q : questions) {
            System.out.println("\n>>> " + q);
            chatMemory.add(userMessage(q));
            AiMessage a = model.generate(chatMemory.messages()).content();;
            chatMemory.add(a);
            System.out.println("\n" + a.text());
        }
    }
}
