package rdrozdz.searchengine;

import rdrozdz.searchengine.model.Document;
import rdrozdz.searchengine.model.Term;

import java.util.List;

public interface SearchEngine {
    void applay(List<Document> documents);

    List<Document> search(Term term);
}
