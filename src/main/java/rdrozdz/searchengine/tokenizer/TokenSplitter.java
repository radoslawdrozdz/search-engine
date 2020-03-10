package rdrozdz.searchengine.tokenizer;

import rdrozdz.searchengine.model.Document;

import java.util.List;

public interface TokenSplitter {
    List<String> split(Document document);
}
