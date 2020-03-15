package rdrozdz.searchengine.tokenizer

import rdrozdz.searchengine.model.Document
import spock.lang.Specification

import static rdrozdz.searchengine.model.vo.DocumentIdFixture.DOCUMENT_1
import static rdrozdz.searchengine.model.vo.DocumentTokensFixture.documentTokens

class TokenizerSpec extends Specification {

    def wordCleaner = new TokenCleaner()
    def tokenSplitter = new SimpleTokenSplitter()
    def tokenizer = new Tokenizer(wordCleaner, tokenSplitter)

    def 'should returns token representation of document'() {
        given:
        def document = new Document(DOCUMENT_1, input)

        when:
        def dt = tokenizer.tokenize(document)

        then:
        dt == output

        where:
        input                 || output
        'the quick brown fox' || documentTokens(DOCUMENT_1, 'the', 'quick', 'brown', 'fox')
    }

    def 'should returns token representation of document with special characters'() {
        given:
        def document = new Document(DOCUMENT_1, input)

        when:
        def dt = tokenizer.tokenize(document)

        then:
        dt == output

        where:
        input                 || output
        'over the brown dog.' || documentTokens(DOCUMENT_1, 'over', 'the', 'brown', 'dog')
        '!the lazy dog!'      || documentTokens(DOCUMENT_1, 'the', 'lazy', 'dog')
        'the brown dog .'     || documentTokens(DOCUMENT_1, 'the', 'brown', 'dog')
        'the fox! dog .'      || documentTokens(DOCUMENT_1, 'the', 'fox', 'dog')
    }

    def 'should returns token representation of document when given upper case'() {
        given:
        def document = new Document(DOCUMENT_1, input)

        when:
        def dt = tokenizer.tokenize(document)

        then:
        dt == output

        where:
        input                                     || output
        'The brown fox Jumped'                    || documentTokens(DOCUMENT_1,'the', 'brown', 'fox', 'jumped')
        'The Red Fox'                             || documentTokens(DOCUMENT_1, 'the', 'red', 'fox')
        'JUMPED over the brown dog'               || documentTokens(DOCUMENT_1, 'jumped', 'over', 'the', 'brown', 'dog')
        'the bRoWn fox JUMpeD over ThE brown dog' || documentTokens(DOCUMENT_1, 'the', 'brown', 'fox', 'jumped', 'over', 'the', 'brown', 'dog')
    }
}
