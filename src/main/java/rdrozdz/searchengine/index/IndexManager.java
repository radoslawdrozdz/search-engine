package rdrozdz.searchengine.index;

import com.findwise.IndexEntry;
import rdrozdz.searchengine.model.vo.DocumentTokens;
import rdrozdz.searchengine.model.vo.Term;

import java.util.List;

public interface IndexManager {
    void rebuild(DocumentTokens documentTokens);

    List<IndexEntry> search(Term searchTerm);
}
