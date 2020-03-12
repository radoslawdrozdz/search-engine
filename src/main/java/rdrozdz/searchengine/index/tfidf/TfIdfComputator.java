package rdrozdz.searchengine.index.tfidf;

import rdrozdz.searchengine.model.vo.DocumentId;
import rdrozdz.searchengine.model.vo.Term;

import java.math.BigDecimal;

public interface TfIdfComputator {
    Integer COMPUTATION_SCALE = 3;
    BigDecimal tfidfScore(Term term, DocumentId documentId, double allDocumentsCount);
}
