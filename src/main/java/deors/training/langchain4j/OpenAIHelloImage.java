package deors.training.langchain4j;

import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.openai.OpenAiImageModel;
import dev.langchain4j.model.output.Response;

public class OpenAIHelloImage {
    
    void main() {
        ImageModel model = OpenAiImageModel.withApiKey(System.getenv("OPENAI_API_KEY"));

        Response<Image> response = model.generate("""
            Software engineers of diverse background,
            origin and gender, attending
            a conference talk on a hot IT topic.""");

        System.out.println(response.content().url());
    }
}
