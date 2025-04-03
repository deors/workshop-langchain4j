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
            origin and gender, attending a conference talk
            on a hot IT topic.
            The people is looking at the presenter, who is
            standing next to a tall table and laptop, with
            a giant screen in her back showing the
            presentation content.
            The conference is named OpenSouthCode, so please
            add the name of the conference to the content of
            the presentation.""");

        System.out.println(response.content().url());
    }
}
