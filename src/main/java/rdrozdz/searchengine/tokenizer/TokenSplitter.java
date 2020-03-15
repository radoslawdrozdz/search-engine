package rdrozdz.searchengine.tokenizer;

import rdrozdz.searchengine.model.Document;

import java.util.stream.Stream;

public interface TokenSplitter {
    Stream<String> split(Document document);
}
