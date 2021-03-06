package rdrozdz.searchengine.index;

import rdrozdz.searchengine.model.vo.DocumentId;
import rdrozdz.searchengine.model.vo.DocumentTokens;
import rdrozdz.searchengine.model.vo.Term;

import java.util.Set;

public interface InvertedIndex {
    void rebuild(DocumentTokens documentTokens);
    Set<DocumentId> find(Term term);
}
