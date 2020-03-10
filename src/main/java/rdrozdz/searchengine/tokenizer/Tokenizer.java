package rdrozdz.searchengine.tokenizer;

import rdrozdz.searchengine.model.Document;
import rdrozdz.searchengine.model.DocumentTokens;
import rdrozdz.searchengine.model.Token;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Tokenizer {

    private static final Predicate<Token> NOT_EMPTY_TOKEN = t -> t != Token.EMPTY;

    private WordCleaner wordCleaner;
    private TokenSplitter tokenSplitter;

    public Tokenizer(WordCleaner wordCleaner, TokenSplitter tokenSplitter) {
        this.wordCleaner = wordCleaner;
        this.tokenSplitter = tokenSplitter;
    }

    public DocumentTokens tokenize(Document document) {
        List<Token> tokens = tokenSplitter
                .split(document)
                .stream()
                .map(String::toLowerCase)
                .map(w -> wordCleaner.clean(w))
                .map(Token::of)
                .filter(NOT_EMPTY_TOKEN)
                .collect(Collectors.toList());
        return new DocumentTokens(document.getDocumentId(), tokens);
    }
}
