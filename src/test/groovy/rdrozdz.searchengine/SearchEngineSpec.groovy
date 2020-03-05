package rdrozdz.searchengine

import spock.lang.Specification


class SearchEngineSpec extends Specification {

    SearchEngine searchEngine = new SearchEngineImpl()

    def 'searching temrm' () {
        given: 'i have defined list of documents'
        def docsuments = List.of(
                new Document("The brown fox jumped over the brown dog."),
                new Document("The lazy brown dog, sat in the other corner"),
                new Document("The Red Fox bit the lazy dog!")
        )

        and: 'applay document to search engine'
        searchEngine.applay(docsuments)

        when: 'want to search world brown'
        def result = searchEngine.search(new Term("brown"))

        then: 'in result are document: 1 and 2'
        result[0] == new Document("The brown fox jumped over the brown dog.")
        result[1] == new Document("The Red Fox bit the lazy dog!")
    }
}