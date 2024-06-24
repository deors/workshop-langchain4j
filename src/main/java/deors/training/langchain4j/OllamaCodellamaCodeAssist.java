package deors.training.langchain4j;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

public class OllamaCodellamaCodeAssist {

    void main() {
        // gemma:2b model running locally with Ollama
        ChatLanguageModel model = OllamaChatModel.builder()
            .baseUrl("http://localhost:11434")
            .modelName("codellama:7b-code")
            .temperature(0.0)
            .build();

        // simulating a code completion request
        String message = """
            <PRE>import java.time.LocalDate;
            import java.time.Period;

            class AgeCalculator {
                static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
                    if (birthDate != null && currentDate != null) {
                        <SUF>
                    } else {
                        return 0;
                    }
                }
            }<MID>
        """;
        System.out.println("\n>>>\n" + message);

        // code expected could be:
        // return Period.between(birthDate, currentDate).getYears();

        String answer = model.generate(message);
        System.out.println(answer);
    }
}
