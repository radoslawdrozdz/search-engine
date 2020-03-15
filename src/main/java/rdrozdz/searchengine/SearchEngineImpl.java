package rdrozdz.searchengine;

import com.findwise.IndexEntry;
import com.findwise.SearchEngine;
import rdrozdz.searchengine.index.IndexManager;
import rdrozdz.searchengine.model.Document;
import rdrozdz.searchengine.model.vo.DocumentId;
import rdrozdz.searchengine.model.vo.DocumentTokens;
import rdrozdz.searchengine.model.vo.Term;
import rdrozdz.searchengine.repository.DocumentRepository;
import rdrozdz.searchengine.tokenizer.Tokenizer;

import java.util.List;

public class SearchEngineImpl implements SearchEngine {

    private DocumentRepository documentRepository;
    private Tokenizer tokenizer;
    private IndexManager indexManager;

    public SearchEngineImpl(DocumentRepository documentRepository,
                            Tokenizer tokenizer,
                            IndexManager indexManager) {
        this.documentRepository = documentRepository;
        this.tokenizer = tokenizer;
        this.indexManager = indexManager;
    }

    @Override
    public void indexDocument(String id, String content) {
        DocumentId documentId = DocumentId.of(id);
        Document document = new Document(documentId, content);
        documentRepository.save(document);
        DocumentTokens documentTokens = tokenizer.tokenize(document);
        indexManager.rebuild(documentTokens);
    }

    @Override
    public List<IndexEntry> search(String term) {
        Term searchTerm = new Term(term);
        return indexManager.search(searchTerm);
    }
}
