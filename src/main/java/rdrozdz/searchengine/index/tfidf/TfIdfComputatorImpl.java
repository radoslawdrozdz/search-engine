package rdrozdz.searchengine.index.tfidf;

import rdrozdz.searchengine.model.vo.DocumentId;
import rdrozdz.searchengine.model.vo.Term;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static rdrozdz.searchengine.utils.ComputationUtil.COMPUTATION_SCALE;

public class TfIdfComputatorImpl implements TfIdfComputator {

    private TfComputator tfComputator;
    private IdfComputator idfComputator;

    public TfIdfComputatorImpl(TfComputator tfComputator, IdfComputator idfComputator) {
        this.tfComputator = tfComputator;
        this.idfComputator = idfComputator;
    }

    @Override
    public BigDecimal tfidfScore(Term term, DocumentId documentId, double allDocumentsCount) {
        BigDecimal tf = this.tfComputator.tfScore(term, documentId);
        BigDecimal idf = this.idfComputator.idfScore(term, allDocumentsCount);
        BigDecimal multiply = tf.multiply(idf);
        return multiply.setScale(COMPUTATION_SCALE, RoundingMode.HALF_UP);
    }
}
