package rdrozdz.searchengine.model.vo

import rdrozdz.searchengine.model.vo.DocumentId
import spock.lang.Specification

class DocumentIdSpec extends Specification {

    def 'two document id shoudul be equal' (){
        expect:
        DocumentId.of('1') == DocumentId.of('1')
    }
}
