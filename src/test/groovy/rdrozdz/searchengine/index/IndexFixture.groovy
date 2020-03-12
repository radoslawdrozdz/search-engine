package rdrozdz.searchengine.index

import rdrozdz.searchengine.model.vo.DocumentId
import rdrozdz.searchengine.model.vo.DocumentTokens
import rdrozdz.searchengine.model.vo.Token

import java.util.stream.Collectors

class IndexFixture {
    static DocumentTokens documentTokens(String docuemtnId, String... args) {
        def documentId = DocumentId.of(docuemtnId)
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
