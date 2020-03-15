package rdrozdz.searchengine.model

import rdrozdz.searchengine.model.exception.DocumentExceptions
import spock.lang.Specification
import spock.lang.Unroll

import static rdrozdz.searchengine.model.vo.DocumentIdFixture.DOCUMENT_1

class DocumentSpec extends Specification {

    private static final String CORRECT_CONNTENT = 'The brown fox jumped over the brown dog.'

    @Unroll
    def 'should create Document with contents #content'() {
        when:
            new Document(DOCUMENT_1, content)
        then:
            noExceptionThrown()
        where:
            content                                       | _
            'The brown fox jumped over the brown dog.'    | _
            'The lazy brown dog, sat in the other corner' | _
            'The Red Fox bit the lazy dog!'               | _
            'hello'                                       | _
            '   ok'                                       | _
            'ok  \t'                                      | _
            'ok  \n'                                      | _
            'ok  \r'                                      | _
            'ok'                                          | _
            'ok!'                                         | _
    }

    def 'should not create Document when content is null'() {
        when:
            new Document(DOCUMENT_1, null)
        then:
            def e = thrown(DocumentExceptions.DocumentCreationException)
            e.message == 'can not create Document with null content'
    }

    def 'should not create Document when content is empty'() {
        when:
            new Document(DOCUMENT_1, "")
        then:
            def e = thrown(DocumentExceptions.DocumentCreationException)
            e.message == 'can not create Document with empty content'
    }

    def 'should not create Document when documentId id is null'() {
        when:
            new Document(null, CORRECT_CONNTENT)
        then:
            def e = thrown(DocumentExceptions.DocumentCreationException)
            e.message == 'can not create Document with null documentId id'
    }

    def 'shoud not create Document when content has only white spaces'() {
        when:
            new Document(DOCUMENT_1, whietSpace)
        then:
            def e = thrown(DocumentExceptions.DocumentCreationException)
            e.message == 'can not create Document with only white spaces content'

        where:
            whietSpace | _
            " "        | _
            "\t"       | _
            "\n"       | _
            "\r"       | _
            "  "       | _
    }

    @Unroll
    def 'shoud not create Document whne it contain only special character #specialCharacter'() {
        when:
            new Document(DOCUMENT_1, specialCharacter)
        then:
            def e = thrown(DocumentExceptions.DocumentCreationException)
            e.message == 'can not create Document with only special characters content'

        where:
            specialCharacter | _
            "!"              | _
            "!  "            | _
            "?  "            | _
            "  }"            | _

    }
}
