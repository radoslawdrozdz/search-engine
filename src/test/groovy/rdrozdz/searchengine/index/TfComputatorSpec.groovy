package rdrozdz.searchengine.index


import rdrozdz.searchengine.model.vo.Term
import spock.lang.Specification

import static rdrozdz.searchengine.model.vo.DocumentIdFixture.DOCUMENT_1
import static rdrozdz.searchengine.model.vo.DocumentIdFixture.DOCUMENT_2
import static rdrozdz.searchengine.model.vo.DocumentTokensFixture.documentTokens

class TfComputatorSpec extends Specification {

    def subject = new TfIndexImpl()

    def 'should compute tf for documents tokens' (){
        given:
        def doc1 = documentTokens(DOCUMENT_1, 'a', 'this', 'is', 'a', 'sample')
        def doc2 = documentTokens(DOCUMENT_2, 'this', 'is','example', 'another', 'another', 'example', 'example')

        and:
        subject.rebuild(doc1)
        subject.rebuild(doc2)

        expect:
        subject.tfScore(new Term('this'), DOCUMENT_1) == 0.200
        subject.tfScore(new Term('this'), DOCUMENT_2) == 0.143
        subject.tfScore(new Term('example'), DOCUMENT_1) == 0
        subject.tfScore(new Term('example'), DOCUMENT_2) == 0.429

    }


}