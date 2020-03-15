package rdrozdz.searchengine.index;

import com.findwise.IndexEntry;
import rdrozdz.searchengine.index.tfidf.TfIdfComputator;
import rdrozdz.searchengine.model.vo.DocumentId;
import rdrozdz.searchengine.model.vo.DocumentTokens;
import rdrozdz.searchengine.model.vo.IndexEntryImpl;
import rdrozdz.searchengine.model.vo.Term;
import rdrozdz.searchengine.repository.DocumentRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static rdrozdz.searchengine.model.vo.IndexEntryImpl.INDEX_ENTRY_COMPARATOR;

public class IndexManagerImpl implements IndexManager {

    private InvertedIndex invertedIndex;
    private TfIndex tfIndex;
    private TfIdfComputator tfIdfComputator;
    private DocumentRepository repository;

    public IndexManagerImpl(InvertedIndex invertedIndex, TfIndex tfIndex,
                            TfIdfComputator tfIdfComputator, DocumentRepository repository) {
        this.invertedIndex = invertedIndex;
        this.tfIndex = tfIndex;
        this.tfIdfComputator = tfIdfComputator;
        this.repository = repository;
    }

    @Override
    public void rebuild(DocumentTokens documentTokens) {
        invertedIndex.rebuild(documentTokens);
        tfIndex.rebuild(documentTokens);
    }

    @Override
    public List<IndexEntry> search(Term searchTerm) {
        long numberOfAll = this.repository.numberOfAll();

        return invertedIndex.find(searchTerm)
                .stream()
                .map(mapToIndexEntry(searchTerm, numberOfAll))
                .sorted(INDEX_ENTRY_COMPARATOR)
                .collect(Collectors.toList());
    }

    private Function<DocumentId, IndexEntryImpl> mapToIndexEntry(Term searchTerm, long numberOfAll) {
        return documentId -> {
            BigDecimal tfidfScore = this.tfIdfComputator.tfidfScore(searchTerm, documentId, numberOfAll);
            return new IndexEntryImpl(documentId, tfidfScore);
        };
    }
}
