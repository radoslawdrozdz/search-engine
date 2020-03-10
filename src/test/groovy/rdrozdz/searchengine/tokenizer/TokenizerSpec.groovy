package rdrozdz.searchengine.tokenizer

import rdrozdz.searchengine.model.Document
import rdrozdz.searchengine.model.DocumentId
import rdrozdz.searchengine.model.Token
import spock.lang.Specification

import static java.util.List.of


class TokenizerSpec extends Specification {

    private static final DocumentId DOCUMENT_ID = DocumentId.of(1);


    def wordCleaner = new WordCleaner()
    def tokenSplitter = new SimpleTokenSplitter()
    def tokenizer = new Tokenizer(wordCleaner, tokenSplitter)

    def 'should returns token representation of document'() {
        given:
            def document = new Document(DOCUMENT_ID, input)

        when:
            def tokens = tokenizer.tokenize(document)

        then:
            tokens == output
        where:
            input                                         || output
            'the quick brown fox jumps over the lazy dog' || of(t("the"), t("quick"), t("brown"), t("fox"), t("jumps"), t("over"), t("the"), t("lazy"), t("dog"))
    }

    def 'should returns token representation of document with special characters'() {
        given:
            def document = new Document(DOCUMENT_ID, input)

        when:
            def tokens = tokenizer.tokenize(document)

        then:
            tokens == output
        where:
            input                                        || output
            'the brown fox jumped over the brown dog.'   || of(t("the"), t("brown"), t("fox"), t("jumped"), t("over"), t("the"), t("brown"), t("dog"))
            '!the red fox bit the lazy dog!'             || of(t("the"), t("red"), t("fox"), t("bit"), t("the"), t("lazy"), t("dog"))
            'the brown fox jumped over the brown dog .'  || of(t("the"), t("brown"), t("fox"), t("jumped"), t("over"), t("the"), t("brown"), t("dog"))
            'the brown fox! jumped over the brown dog .' || of(t("the"), t("brown"), t("fox"), t("jumped"), t("over"), t("the"), t("brown"), t("dog"))
    }

    def 'should returns token representation of document when given upper case'() {
        given:
            def document = new Document(DOCUMENT_ID, input)

        when:
            def tokens = tokenizer.tokenize(document)

        then:
            tokens == output
        where:
            input                                      || output
            'The brown fox Jumped over the brown dog.' || of(t("the"), t("brown"), t("fox"), t("jumped"), t("over"), t("the"), t("brown"), t("dog"))
            'The Red Fox bit the lazy dog'             || of(t("the"), t("red"), t("fox"), t("bit"), t("the"), t("lazy"), t("dog"))
            'the brown fox JUMPED over the brown dog'  || of(t("the"), t("brown"), t("fox"), t("jumped"), t("over"), t("the"), t("brown"), t("dog"))
            'the bRoWn fox jumped over ThE brown dog'  || of(t("the"), t("brown"), t("fox"), t("jumped"), t("over"), t("the"), t("brown"), t("dog"))
    }

    private static Token t(String input) {
        return Token.of(input)
    }
}
