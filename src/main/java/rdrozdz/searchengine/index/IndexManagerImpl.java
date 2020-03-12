package rdrozdz.searchengine.index;

import com.findwise.IndexEntry;
import rdrozdz.searchengine.index.tfidf.TfIdfComputator;
import rdrozdz.searchengine.model.vo.DocumentTokens;
import rdrozdz.searchengine.model.vo.IndexEntryImpl;
import rdrozdz.searchengine.model.vo.Term;
import rdrozdz.searchengine.repository.DocumentRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
        Set<IndexEntryImpl> result = new TreeSet<>(INDEX_ENTRY_COMPARATOR);
        long allDocumentsCount = this.repository.allCount();

        invertedIndex.find(searchTerm)
                .forEach(id -> {
            BigDecimal tfidf = this.tfIdfComputator.tfidfScore(searchTerm, id, allDocumentsCount);
            result.add(new IndexEntryImpl(id, tfidf));
        });
        return new ArrayList<>(result);
    }
}
