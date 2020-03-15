package rdrozdz.searchengine.tokenizer;

import rdrozdz.searchengine.model.Document;
import rdrozdz.searchengine.model.vo.DocumentTokens;
import rdrozdz.searchengine.model.vo.Token;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Tokenizer {

    private static final Predicate<Token> NOT_EMPTY_TOKEN = t -> t != Token.EMPTY;

    private TokenCleaner tokenCleaner;
    private TokenSplitter tokenSplitter;

    public Tokenizer(TokenCleaner tokenCleaner, TokenSplitter tokenSplitter) {
        this.tokenCleaner = tokenCleaner;
        this.tokenSplitter = tokenSplitter;
    }

    public DocumentTokens tokenize(Document document) {
        List<Token> tokens = tokenSplitter
                .split(document)
                .map(String::toLowerCase)
                .map(w -> tokenCleaner.clean(w))
                .map(Token::of)
                .filter(NOT_EMPTY_TOKEN)
                .collect(Collectors.toList());
        return new DocumentTokens(document.getDocumentId(), tokens);
    }
}
