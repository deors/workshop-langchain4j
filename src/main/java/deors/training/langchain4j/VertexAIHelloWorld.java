package deors.training.langchain4j;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.vertexai.VertexAiGeminiChatModel;

public class VertexAIHelloWorld {

    void main() {
        // Vertex AI model
        ChatLanguageModel model = VertexAiGeminiChatModel.builder()
            .project(System.getenv("VERTEXAI_PROJECT_ID"))
            .location("us-central1")
            .modelName("gemini-2.5-flash")
            .build();

        // the first prompt
        String message = "Hello world!";
        System.out.println("\n>>> " + message);

        String answer = model.generate(message);
        System.out.println(answer);
    }
}
