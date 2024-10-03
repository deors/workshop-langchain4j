package deors.training.langchain4j;

import java.time.Duration;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

public interface OllamaBaseAskAboutCode {

    static void askAboutCode(String baseUrl, String modelName) {
        // loads a model running locally with Ollama
        ChatLanguageModel model = OllamaChatModel.builder()
            .baseUrl(baseUrl)
            .modelName(modelName)
            .timeout(Duration.ofSeconds(120))
            .build();

        // the first prompt
        String message1 = """
            You are a senior software engineer, expert with open source technologies.
            Could you provide example code to sort a list of dates in Java and Go languages?
        """;
        System.out.println("\n>>>\n" + message1);

        String answer1 = model.generate(message1);
        System.out.println(answer1);

        // the second prompt
        String message2 = """
            You are a senior software engineer, expert with open source technologies,
            specially with Java and the Spring Boot framework.
            Consider a domain entity named Film with attributes id, title,
            and release date.
            Could you provide the simplest code to start a web server and return
            RESTful responses to the Film domain entity?
        """;
        System.out.println("\n>>>\n" + message2);

        String answer2 = model.generate(message2);
        System.out.println(answer2);
    }
}
