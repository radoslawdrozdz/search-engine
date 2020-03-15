package rdrozdz.searchengine.index


import rdrozdz.searchengine.model.vo.Term
import spock.lang.Specification

import static rdrozdz.searchengine.model.vo.DocumentIdFixture.DOCUMENT_1
import static rdrozdz.searchengine.model.vo.DocumentIdFixture.DOCUMENT_2
import static rdrozdz.searchengine.model.vo.DocumentTokensFixture.documentTokens

class IdfComputatorSpec extends Specification {

    def subject = new RecordLevelInvertedIndex()

    def 'should compute tf for documents tokens' (){
        given:
        def doc1 = documentTokens(DOCUMENT_1, 'a', 'this', 'is', 'a', 'sample')
        def doc2 = documentTokens(DOCUMENT_2, 'this', 'is','example', 'another', 'another', 'example', 'example')
        def storedDocumentsCount = 2D

        when:
        subject.rebuild(doc1)
        subject.rebuild(doc2)

        then:
        subject.idfScore(new Term('this'), storedDocumentsCount) == 0
        subject.idfScore(new Term('example'), storedDocumentsCount) == 0.301

    }
}