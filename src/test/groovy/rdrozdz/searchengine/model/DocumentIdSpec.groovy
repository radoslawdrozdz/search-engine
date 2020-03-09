package rdrozdz.searchengine.model

import spock.lang.Specification
import spock.lang.Unroll


class DocumentIdSpec extends Specification {

    @Unroll
    def 'should create DocumentId of id=#correct'() {
        when:
            def documentId = DocumentId.of(correct)

        then:
            noExceptionThrown()
            documentId == DocumentId.of(correct)

        where:
            correct           | _
            1                 | _
            100               | _
            Integer.MAX_VALUE | _

    }

    @Unroll
    def 'should not create DocumentId when id=#negative, becouse it is not positive'() {
        when:
        DocumentId.of(negative)

        then:
            def e = thrown(IllegalArgumentException)
            e.message == expectedErrorMessage

        where:
            negative          || expectedErrorMessage
            0                 || 'cannot create DocumentId with id=0, Id must be positive'
            -1                || 'cannot create DocumentId with id=-1, Id must be positive'
            Integer.MIN_VALUE || 'cannot create DocumentId with id=-2147483648, Id must be positive'
    }

    def 'two document id shoudul be equal' (){
        expect:
        DocumentId.of(1) == DocumentId.of(1)
    }
}
