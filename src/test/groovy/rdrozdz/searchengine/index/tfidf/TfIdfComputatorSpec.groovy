package rdrozdz.searchengine.index.tfidf

import rdrozdz.searchengine.index.RecordLevelInvertedIndex
import rdrozdz.searchengine.index.TfIndexImpl
import rdrozdz.searchengine.model.vo.DocumentId
import rdrozdz.searchengine.model.vo.Term
import spock.lang.Specification

import static rdrozdz.searchengine.index.IndexFixture.documentTokens

class TfIdfComputatorSpec extends Specification {

    def tfComputator = new TfIndexImpl()
    def idfComputator = new RecordLevelInvertedIndex()
    def subject = new TfIdfComputatorImpl(tfComputator, idfComputator)

    def 'should compute tfidf for documents tokens' (){
        given:
        def doc1 = documentTokens('1', 'a', 'this', 'is', 'a', 'sample')
        def doc2 = documentTokens('2', 'this', 'is','example', 'another', 'another', 'example', 'example')

        and:
        tfComputator.rebuild(doc1)
        tfComputator.rebuild(doc2)
        idfComputator.rebuild(doc1)
        idfComputator.rebuild(doc2)

        and:
        def storedDocumentsCount = 2D

        expect:
        subject.tfidfScore(new Term('this'), DocumentId.of('1'), storedDocumentsCount) == 0
        subject.tfidfScore(new Term('this'), DocumentId.of('2'), storedDocumentsCount) == 0
        subject.tfidfScore(new Term('example'), DocumentId.of('1'), storedDocumentsCount) == 0
        subject.tfidfScore(new Term('example'), DocumentId.of('2'), storedDocumentsCount) == 0.129
    }
}