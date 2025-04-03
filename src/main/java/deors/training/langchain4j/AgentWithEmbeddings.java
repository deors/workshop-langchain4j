package deors.training.langchain4j;

import java.net.URISyntaxException;
import java.nio.file.Path;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.apache.tika.ApacheTikaDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

public class AgentWithEmbeddings {
    
    void main() throws URISyntaxException {
        Logger rootLogger = (Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        rootLogger.setLevel(Level.INFO);

        // an embedding model good for simple documents
        EmbeddingModel embModel = new AllMiniLmL6V2EmbeddingModel();

        // an in-memory embedding store
        EmbeddingStore<TextSegment> embStore = new InMemoryEmbeddingStore<>();

        // load a PDF file from the classpath
        Path path = Path.of(ClassLoader.getSystemResource("the-role-of-a-platform.pdf").toURI());
        Document document = FileSystemDocumentLoader.loadDocument(path, new ApacheTikaDocumentParser());
        DocumentSplitter splitter = DocumentSplitters.recursive(256, 0);

        // ingest the document into the embedding store
        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
            .documentSplitter(splitter)
            .embeddingModel(embModel)
            .embeddingStore(embStore)
            .build();
        ingestor.ingest(document);

        // define the content retriever connecting everything together
        ContentRetriever retriever = EmbeddingStoreContentRetriever.builder()
            .embeddingModel(embModel)
            .embeddingStore(embStore)
            .maxResults(1)
            .minScore(0.8)
            .build();
        
        // llama3:8b model running locally with Ollama
        ChatLanguageModel chatModel = OllamaChatModel.builder()
            .baseUrl("http://localhost:11434")
            .modelName("llama3:8b")
            .build();

        // define context window
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(100);

        Agent agent = AiServices.builder(Agent.class)
            .chatLanguageModel(chatModel)
            .chatMemory(chatMemory)
            .contentRetriever(retriever)
            .build();
        
        String message1 = "Could you summarize in 50 words the main concepts about platform engineering?";
        System.out.println("\n>>> " + message1);

        String answer1 = agent.answer(message1);
        System.out.println("\n" + answer1);
    
        String message2 = "Could you describe the high-level process to adopt platform engineering in an organization?";
        System.out.println("\n>>> " + message2);

        String answer2 = agent.answer(message2);
        System.out.println("\n" + answer2);
    
        String message3 = "What are the main benefits that can be expected after adopting a platform engineering approach?";
        System.out.println("\n>>> " + message3);

        String answer3 = agent.answer(message3);
        System.out.println("\n" + answer3);
    }

    interface Agent {
        @SystemMessage("""
            You are an expert in information technologies and software engineering.
            """)
        String answer(String inputMessage);
    }
}
