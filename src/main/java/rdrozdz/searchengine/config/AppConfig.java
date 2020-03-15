package rdrozdz.searchengine.config;

import com.findwise.SearchEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rdrozdz.searchengine.SearchEngineImpl;
import rdrozdz.searchengine.index.IndexManager;
import rdrozdz.searchengine.index.IndexManagerImpl;
import rdrozdz.searchengine.index.RecordLevelInvertedIndex;
import rdrozdz.searchengine.index.TfIndexImpl;
import rdrozdz.searchengine.index.tfidf.IdfComputator;
import rdrozdz.searchengine.index.tfidf.TfComputator;
import rdrozdz.searchengine.index.tfidf.TfIdfComputator;
import rdrozdz.searchengine.index.tfidf.TfIdfComputatorImpl;
import rdrozdz.searchengine.repository.DocumentRepository;
import rdrozdz.searchengine.repository.DocumentRepositoryImpl;
import rdrozdz.searchengine.tokenizer.SimpleTokenSplitter;
import rdrozdz.searchengine.tokenizer.TokenCleaner;
import rdrozdz.searchengine.tokenizer.TokenSplitter;
import rdrozdz.searchengine.tokenizer.Tokenizer;

@Configuration
public class AppConfig {

    @Bean
    DocumentRepository documentRepository() {
        return new DocumentRepositoryImpl();
    }

    @Bean
    SearchEngine searchEngine(DocumentRepository repository) {
        TokenCleaner tokenCleaner = new TokenCleaner();
        TokenSplitter tokenSplitter = new SimpleTokenSplitter();
        Tokenizer tokenizer = new Tokenizer(tokenCleaner, tokenSplitter);

        RecordLevelInvertedIndex recordLevelInvertedIndex = new RecordLevelInvertedIndex();
        TfIndexImpl tfIndex = new TfIndexImpl();
        TfComputator tfComputator = tfIndex;
        IdfComputator idfComputator = recordLevelInvertedIndex;
        TfIdfComputator tfIdfComputator = new TfIdfComputatorImpl(tfComputator, idfComputator);

        IndexManager indexManager = new IndexManagerImpl(recordLevelInvertedIndex, tfIndex, tfIdfComputator, repository);

        return new SearchEngineImpl(repository, tokenizer, indexManager);
    }
}
