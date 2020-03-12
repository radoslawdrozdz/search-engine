package rdrozdz.searchengine.tokenizer

import rdrozdz.searchengine.model.Document
import rdrozdz.searchengine.model.vo.DocumentId
import rdrozdz.searchengine.model.vo.DocumentTokens
import rdrozdz.searchengine.model.vo.Token
import spock.lang.Specification

import static java.util.List.of


class TokenizerSpec extends Specification {

    private static final DocumentId DOCUMENT_ID = DocumentId.of('1');


    def wordCleaner = new WordCleaner()
    def tokenSplitter = new SimpleTokenSplitter()
    def tokenizer = new Tokenizer(wordCleaner, tokenSplitter)

    def 'should returns token representation of document'() {
        given:
        def document = new Document(DOCUMENT_ID, input)

        when:
        def documentTokens = tokenizer.tokenize(document)

        then:
        documentTokens == output

        where:
        input                 || output
        'the quick brown fox' || new DocumentTokens(DOCUMENT_ID, of(t("the"), t("quick"), t("brown"), t("fox")))
    }

    def 'should returns token representation of document with special characters'() {
        given:
        def document = new Document(DOCUMENT_ID, input)

        when:
        def documentTokens = tokenizer.tokenize(document)

        then:
        documentTokens == output

        where:
        input                 || output
        'over the brown dog.' || new DocumentTokens(DOCUMENT_ID, of(t("over"), t("the"), t("brown"), t("dog")))
        '!the lazy dog!'      || new DocumentTokens(DOCUMENT_ID, of(t("the"), t("lazy"), t("dog")))
        'the brown dog .'     || new DocumentTokens(DOCUMENT_ID, of(t("the"), t("brown"), t("dog")))
        'the fox! dog .'      || new DocumentTokens(DOCUMENT_ID, of(t("the"), t("fox"), t("dog")))
    }

    def 'should returns token representation of document when given upper case'() {
        given:
        def document = new Document(DOCUMENT_ID, input)

        when:
        def documentTokens = tokenizer.tokenize(document)

        then:
        documentTokens == output

        where:
        input                                     || output
        'The brown fox Jumped'                    || new DocumentTokens(DOCUMENT_ID, of(t("the"), t("brown"), t("fox"), t("jumped")))
        'The Red Fox'                             || new DocumentTokens(DOCUMENT_ID, of(t("the"), t("red"), t("fox")))
        'JUMPED over the brown dog'               || new DocumentTokens(DOCUMENT_ID, of(t("jumped"), t("over"), t("the"), t("brown"), t("dog")))
        'the bRoWn fox JUMpeD over ThE brown dog' || new DocumentTokens(DOCUMENT_ID, of(t("the"), t("brown"), t("fox"), t("jumped"), t("over"), t("the"), t("brown"), t("dog")))
    }

    private static Token t(String input) {
        return Token.of(input)
    }
}
