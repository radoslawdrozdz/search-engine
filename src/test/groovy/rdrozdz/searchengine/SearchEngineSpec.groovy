package rdrozdz.searchengine

import spock.lang.Ignore
import spock.lang.Specification

import static rdrozdz.searchengine.SearchEngineFixture.*

class SearchEngineSpec extends Specification {

    def searchEngine = new SearchEngineImpl()

    @Ignore(value = "TODO")
    def 'searching temrm'() {
        given: 'applay document to search engine'
        searchEngine.applay(DOCUMENTS)

        when: 'want to search term'
        def result = searchEngine.search(searchTerm)

        then: 'in result are expected document:'
        result == expectedResult

        where:
        searchTerm || expectedResult
        BROWN      || List.of(DOCUMENT_1, DOCUMENT_3)
        FOX        || List.of(DOCUMENT_3, DOCUMENT_1)
        DOG        || List.of(DOCUMENT_3, DOCUMENT_1, DOCUMENT_2)
    }
}