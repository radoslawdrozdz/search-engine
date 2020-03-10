package rdrozdz.searchengine.index;

import rdrozdz.searchengine.model.DocumentId;
import rdrozdz.searchengine.model.DocumentTokens;
import rdrozdz.searchengine.model.Term;

import java.util.Set;

public interface InvertedIndex {
    void rebuild(DocumentTokens documentTokens);
    Set<DocumentId> find(Term term);
}
