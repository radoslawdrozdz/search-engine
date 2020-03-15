package rdrozdz.searchengine.model.vo

import rdrozdz.searchengine.model.vo.DocumentId
import spock.lang.Specification

class DocumentIdSpec extends Specification {

    def 'documentId should be equal' (){
        expect:
        DocumentId.of('1') == DocumentId.of('1')
    }
    def 'documentId should not be equal' (){
        expect:
        DocumentId.of('1') != DocumentId.of('2')
    }
}
