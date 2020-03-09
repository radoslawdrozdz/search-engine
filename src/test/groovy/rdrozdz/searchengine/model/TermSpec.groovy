package rdrozdz.searchengine.model

import spock.lang.Specification
import spock.lang.Unroll


class TermSpec extends Specification {


    def 'should create Term with trimmedContent'() {
        when:
            def term = new Term(sampleTerm)

        then:
            noExceptionThrown()
            term.getTerm() == trimmedContent

        where:
            sampleTerm   | trimmedContent
            "ok"         | "ok"
            "ok  "       | "ok"
            "  ok"       | "ok"
            "  ok  "     | "ok"
            "\tok"       | "ok"
            "\tok\n\t\r" | "ok"
            "\tok\r"     | "ok"
            "\nok\r"     | "ok"
    }

    @Unroll
    def 'should not create Term with illegal input=#illegalInput'() {
        when:
            new Term(illegalInput)

        then:
            def e = thrown(IllegalArgumentException)
            e.message == errMsq

        where:
            illegalInput || errMsq
            null         || "can not create Term with null content"
            ""           || "can not create Term with empty content"
            " "          || "can not create Term with blank content"
            "\n"         || "can not create Term with blank content"
            "\t"         || "can not create Term with blank content"
            "\r"         || "can not create Term with blank content"
            "!"          || "can not create Term with only specialCharacters content"
            "?\t"        || "can not create Term with only specialCharacters content"
    }

    def 'tearm with the same content shoud be equal'() {
        expect:
            new Term("aaa") == new Term("aaa")
    }

}
