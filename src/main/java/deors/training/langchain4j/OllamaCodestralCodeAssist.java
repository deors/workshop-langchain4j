package deors.training.langchain4j;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

public class OllamaCodestralCodeAssist {

    void main() {
        // gemma:2b model running locally with Ollama
        ChatLanguageModel model = OllamaChatModel.builder()
            .baseUrl("http://localhost:11434")
            .modelName("codestral:22b")
            .temperature(0.0)
            .build();

        // simulating a code completion request
        String message = """
            [PREFIX]import java.time.LocalDate;
            import java.time.Period;

            class AgeCalculator {
                static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
                    if (birthDate != null && currentDate != null) {
                        [SUFFIX]
                    } else {
                        return 0;
                    }
                }
            }[MIDDLE]
        """;
        System.out.println("\n>>>\n" + message);

        // code expected could be:
        // return Period.between(birthDate, currentDate).getYears();

        String answer = model.generate(message);
        System.out.println(answer);
    }
}
