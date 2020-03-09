package rdrozdz.searchengine

import rdrozdz.searchengine.model.Document
import rdrozdz.searchengine.model.DocumentId
import rdrozdz.searchengine.model.Term

class SearchEngineFixture {
    static final DocumentId DOCUMENT_ID_1 = DocumentId.of(1)
    static final DocumentId DOCUMENT_ID_2 = DocumentId.of(2)
    static final DocumentId DOCUMENT_ID_3 = DocumentId.of(3)
    static final Document DOCUMENT_1 = new Document(DOCUMENT_ID_1, "The brown fox jumped over the brown dog.")
    static final Document DOCUMENT_2 = new Document(DOCUMENT_ID_2, "The lazy brown dog, sat in the other corner")
    static final Document DOCUMENT_3 = new Document(DOCUMENT_ID_3, "The Red Fox bit the lazy dog!")
    static final List<Document> DOCUMENTS = List.of(DOCUMENT_1, DOCUMENT_2, DOCUMENT_3)
    static final Term BROWN = new Term("brown")
    static final Term FOX = new Term("fox")
    static final Term DOG = new Term("dog")
}
