package deors.training.langchain4j.image;

import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.openai.OpenAiImageModel;
import dev.langchain4j.model.output.Response;

public class OpenAIHelloImage {
    
    void main() {
        // OpenAI image model
        ImageModel model = OpenAiImageModel.builder()
            .apiKey(System.getenv("OPENAI_API_KEY"))
            .build();

        Response<Image> response = model.generate("""
            Software engineers of diverse background,
            origin and gender, attending a conference talk
            on a hot IT topic.
            The people is looking at the presenter, who is
            standing next to a tall table and laptop, with
            a giant screen behind the presenter showing the
            presentation content.""");

        System.out.println(response.content().url());
    }
}
