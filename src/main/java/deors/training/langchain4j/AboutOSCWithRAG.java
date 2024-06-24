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
import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

public class AboutOSCWithRAG {
    
    void main() throws URISyntaxException {
        Logger rootLogger = (Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        rootLogger.setLevel(Level.INFO);

        // an embedding model good for simple documents
        EmbeddingModel embModel = new AllMiniLmL6V2EmbeddingModel();

        // an in-memory embedding store
        EmbeddingStore<TextSegment> embStore = new InMemoryEmbeddingStore<>();

        // load a PDF file from the classpath
        Path path = Path.of(ClassLoader.getSystemResource("OpenSouthCode24-Press-Release-June.pdf").toURI());
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
        
            // ask about OpenSouthCode 2024
            String messageAboutOSC = "When is OpenSouthCode 2024 going to be? Do you know what are the main topics scheduled?";
            System.out.println("\n>>> " + messageAboutOSC);

            String answerAboutOSC = agent.answer(messageAboutOSC);
            System.out.println("\n" + answerAboutOSC);

            messageAboutOSC = "What is the location for OpenSouthCode 2024?";
            System.out.println("\n>>> " + messageAboutOSC);

            answerAboutOSC = agent.answer(messageAboutOSC);
            System.out.println("\n" + answerAboutOSC);

            messageAboutOSC = "What is OpenSouthKids and when is happening?";
            System.out.println("\n>>> " + messageAboutOSC);

            answerAboutOSC = agent.answer(messageAboutOSC);
            System.out.println("\n" + answerAboutOSC);
    }

    interface Agent {
        @SystemMessage("""
            You are an expert in technology events that is providing guidance to
            people willing to attend IT events in Spain. You have a great knowledge
            on certain events for which you have the press release.
            """)
        String answer(String inputMessage);
    }
}
