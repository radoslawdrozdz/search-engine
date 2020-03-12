package rdrozdz.searchengine.index;

import rdrozdz.searchengine.model.vo.DocumentTokens;

public interface TfIndex {
    void rebuild(DocumentTokens documentTokens);
}
