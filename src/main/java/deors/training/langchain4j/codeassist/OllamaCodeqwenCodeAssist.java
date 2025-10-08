package deors.training.langchain4j.codeassist;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

class OllamaCodeqwenCodeAssist {

    void main() {
        // codeqwen:7b model running locally with Ollama
        ChatLanguageModel model = OllamaChatModel.builder()
            .baseUrl("http://localhost:11434")
            .modelName("codeqwen:7b")
            .temperature(0.0)
            .build();

        // simulating a code completion request
        String message = """
            <|im_start|>import java.time.LocalDate;
            import java.time.Period;

            class AgeCalculator {
                static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
                    if (birthDate != null && currentDate != null) {
                        <|im_end|>
                    } else {
                        return 0;
                    }
                }
            }
        """;
        System.out.println("\n>>>\n" + message);

        // code expected could be:
        // return Period.between(birthDate, currentDate).getYears();

        String answer = model.generate(message);
        System.out.println(answer);
    }
}
