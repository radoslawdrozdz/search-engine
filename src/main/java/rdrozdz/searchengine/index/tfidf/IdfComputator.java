package rdrozdz.searchengine.index.tfidf;

import rdrozdz.searchengine.model.vo.Term;

import java.math.BigDecimal;

public interface IdfComputator {
    BigDecimal idfScore(Term term, double allDocumentsCount);
}
