package deors.training.langchain4j.rag;

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

public class AboutCommitConfWithRAG {
    
    void main() throws URISyntaxException {
        Logger rootLogger = (Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        rootLogger.setLevel(Level.ERROR);

        // an embedding model good for simple documents
        EmbeddingModel embModel = new AllMiniLmL6V2EmbeddingModel();

        // an in-memory embedding store
        EmbeddingStore<TextSegment> embStore = new InMemoryEmbeddingStore<>();

        // a document splitter (to convert content to tokens)
        DocumentSplitter splitter = DocumentSplitters.recursive(256, 0);

        // the ingestor which combines the previous components
        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
            .documentSplitter(splitter)
            .embeddingModel(embModel)
            .embeddingStore(embStore)
            .build();

        // load external documents
        String[] docs = {
            "commit-conf-2025-info.pdf",
            "commit-conf-2025-como-llegar.pdf",
        };

        // ingest the documents into the embedding store
        for (var d : docs) {
            Path path = Path.of(ClassLoader.getSystemResource(d).toURI());
            Document document = FileSystemDocumentLoader.loadDocument(
                path, new ApacheTikaDocumentParser());
            ingestor.ingest(document);
        }

        // define the content retriever connecting everything together
        ContentRetriever retriever = EmbeddingStoreContentRetriever.builder()
            .embeddingModel(embModel)
            .embeddingStore(embStore)
            .maxResults(1)
            .minScore(0.8)
            .build();
        
        // deepseek-r1:8b model running locally with Ollama
        ChatLanguageModel chatModel = OllamaChatModel.builder()
            .baseUrl("http://localhost:11434")
            .modelName("deepseek-r1:8b")
            .temperature(0.0)
            .build();

        // define context window
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(100);

        Agent agent = AiServices.builder(Agent.class)
            .chatLanguageModel(chatModel)
            .chatMemory(chatMemory)
            .contentRetriever(retriever)
            .build();
        
        // ask about Commit Conf 2025
        String[] questions = {
            "When is Commit Conf 2025 going to be?",
            "Do you know what are the main topics scheduled?",
            "What are the metro ligero and bus options to get to the venue?",
        };

        for (var q : questions) {
            System.out.println("\n>>> " + q);
            var a = agent.answer(q);
            System.out.println("\n" + a);
        }
    }

    interface Agent {
        @SystemMessage("""
            You are an expert in technology events that is providing guidance to
            people willing to attend IT events in Spain. You have a great knowledge
            on certain events for which you have specific announcements published
            by the event organizers.
            """)
        String answer(String inputMessage);
    }
}
