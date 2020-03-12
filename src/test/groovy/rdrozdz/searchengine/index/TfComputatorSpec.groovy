package rdrozdz.searchengine.index

import rdrozdz.searchengine.model.vo.DocumentId
import rdrozdz.searchengine.model.vo.Term
import spock.lang.Specification

import static rdrozdz.searchengine.index.IndexFixture.documentTokens

class TfComputatorSpec extends Specification {

    def subject = new TfIndexImpl()

    def 'should compute tf for documents tokens' (){
        given:
        def doc1 = documentTokens('1', 'a', 'this', 'is', 'a', 'sample')
        def doc2 = documentTokens('2', 'this', 'is','example', 'another', 'another', 'example', 'example')

        when:
        subject.rebuild(doc1)
        subject.rebuild(doc2)

        then:
        subject.tfScore(new Term('this'), DocumentId.of('1')) == 0.200
        subject.tfScore(new Term('this'), DocumentId.of('2')) == 0.143
        subject.tfScore(new Term('example'), DocumentId.of('1')) == 0
        subject.tfScore(new Term('example'), DocumentId.of('2')) == 0.429

    }


}