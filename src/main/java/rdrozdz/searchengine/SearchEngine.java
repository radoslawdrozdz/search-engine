package rdrozdz.searchengine;

import java.util.List;

public interface SearchEngine {
    void applay(List<Document> documents);

    List<Document> search(Term term);
}
