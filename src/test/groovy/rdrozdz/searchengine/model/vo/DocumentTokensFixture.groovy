package rdrozdz.searchengine.model.vo

import java.util.stream.Collectors

class DocumentTokensFixture {
    static DocumentTokens documentTokens(DocumentId documentId, String... args) {
        def tokens = args.toList()
                .stream()
                .map(Token::of)
                .collect(Collectors.toList())

        return new DocumentTokens(
                documentId,
                tokens
        )
    }
}
