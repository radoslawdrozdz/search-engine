package rdrozdz.searchengine.index

import rdrozdz.searchengine.model.DocumentId
import rdrozdz.searchengine.model.DocumentTokens
import rdrozdz.searchengine.model.Term
import rdrozdz.searchengine.model.Token
import spock.lang.Specification

import static java.util.List.of


class RecordLevelInvertedIndexSpec extends Specification {

    private static final DocumentId DOCUMENT_ID_1 = DocumentId.of(1)
    private static final DocumentId DOCUMENT_ID_2 = DocumentId.of(2)
    private static final DocumentTokens DOCUMENT_TOKENS_1 = new DocumentTokens(
            DOCUMENT_ID_1,
            of(Token.of("the"), Token.of("brown"), Token.of("fox"))
    )
    private static final DocumentTokens DOCUMENT_TOKENS_2 = new DocumentTokens(
            DOCUMENT_ID_2,
            of(Token.of("the"), Token.of("lazy"), Token.of("brown"), Token.of("dog"))
    )

    def invertedIndex = new RecordLevelInvertedIndex()

    def 'should find all terms after rebuild index'() {
        when:
        invertedIndex.rebuild(DOCUMENT_TOKENS_1)

        then:
        invertedIndex.find(term) == result

        where:
        term              || result
        new Term("the")   || Set.of(DOCUMENT_ID_1)
        new Term("brown") || Set.of(DOCUMENT_ID_1)
        new Term("fox")   || Set.of(DOCUMENT_ID_1)
    }

    def 'should return empty list when asking for not existing term'() {
        when:
        invertedIndex.rebuild(DOCUMENT_TOKENS_1)

        then:
        invertedIndex.find(new Term("dog")) == Set.of()
    }

    def 'should find all terms after rebuild index based on two documents'() {
        when:
        invertedIndex.rebuild(DOCUMENT_TOKENS_1)
        invertedIndex.rebuild(DOCUMENT_TOKENS_2)

        then:
        invertedIndex.find(term) == result

        where:
        term              || result
        new Term("the")   || Set.of(DOCUMENT_ID_1, DOCUMENT_ID_2)
        new Term("brown") || Set.of(DOCUMENT_ID_1, DOCUMENT_ID_2)
        new Term("fox")   || Set.of(DOCUMENT_ID_1)
        new Term("lazy")  || Set.of(DOCUMENT_ID_2)
        new Term("dog")   || Set.of(DOCUMENT_ID_2)
    }

    def 'should find only one document when given with two same worlds' (){
        given:
        def documentTokens = new DocumentTokens(DOCUMENT_ID_1, of(Token.of("the"), Token.of("the")))

        when:
        invertedIndex.rebuild(documentTokens)

        then:
        invertedIndex.find(new Term("the")) == Set.of(DOCUMENT_ID_1)
    }
}