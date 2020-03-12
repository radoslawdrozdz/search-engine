package rdrozdz.searchengine


import rdrozdz.searchengine.index.IndexManager
import rdrozdz.searchengine.index.IndexManagerImpl
import rdrozdz.searchengine.index.RecordLevelInvertedIndex
import rdrozdz.searchengine.index.TfIndexImpl
import rdrozdz.searchengine.index.tfidf.TfIdfComputator
import rdrozdz.searchengine.index.tfidf.TfIdfComputatorImpl
import rdrozdz.searchengine.model.vo.DocumentId
import rdrozdz.searchengine.model.vo.IndexEntryImpl
import rdrozdz.searchengine.repository.DocumentRepository
import rdrozdz.searchengine.repository.DocumentRepositoryImpl
import rdrozdz.searchengine.tokenizer.SimpleTokenSplitter
import rdrozdz.searchengine.tokenizer.TokenSplitter
import rdrozdz.searchengine.tokenizer.Tokenizer
import rdrozdz.searchengine.tokenizer.WordCleaner
import spock.lang.Specification

class SearchEngineSpec extends Specification {

    DocumentRepository repository = new DocumentRepositoryImpl()

    WordCleaner wordCleaner = new WordCleaner()
    TokenSplitter tokenSplitter = new SimpleTokenSplitter()
    Tokenizer tokenizer = new Tokenizer(wordCleaner, tokenSplitter)

    RecordLevelInvertedIndex invertedIndex = new RecordLevelInvertedIndex()
    TfIndexImpl tfIndex = new TfIndexImpl()
    TfIdfComputator tfIdfComputator = new TfIdfComputatorImpl(tfIndex, invertedIndex)

    IndexManager indexManager = new IndexManagerImpl(invertedIndex, tfIndex, tfIdfComputator, repository)

    def subject = new SearchEngineImpl(repository, tokenizer, indexManager)

    def 'should search given world supporting tfidf sorting' (){
        given:
        subject.indexDocument('1', 'the brown fox jumped over the brown dog')
        subject.indexDocument('2', 'the lazy brown dog sat in the corner')
        subject.indexDocument('3', 'the red fox bit the lazy dog')

        expect:
        subject.search('brown') == List.of(ie('1', 0.044), ie('2', 0.022) )
        subject.search('fox') == List.of(ie('3', 0.025), ie('1', 0.022) )
    }

    static IndexEntryImpl ie(String id, double score) {
        return new IndexEntryImpl(DocumentId.of(id), BigDecimal.valueOf(score))
    }
}