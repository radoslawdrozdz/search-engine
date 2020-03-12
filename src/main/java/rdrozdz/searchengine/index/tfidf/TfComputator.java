package rdrozdz.searchengine.index.tfidf;

import rdrozdz.searchengine.model.vo.DocumentId;
import rdrozdz.searchengine.model.vo.Term;

import java.math.BigDecimal;

public interface TfComputator {
    BigDecimal tfScore(Term term, DocumentId documentId);
}
