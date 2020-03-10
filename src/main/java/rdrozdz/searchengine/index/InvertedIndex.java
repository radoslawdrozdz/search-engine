package rdrozdz.searchengine.index;

import rdrozdz.searchengine.model.DocumentId;
import rdrozdz.searchengine.model.DocumentTokens;
import rdrozdz.searchengine.model.Term;

import java.util.List;

public interface InvertedIndex {
    void rebuild(DocumentTokens documentTokens);
    List<DocumentId> find(Term term);
}
