package rdrozdz.searchengine.index;

import io.vavr.control.Try;
import lombok.Getter;
import rdrozdz.searchengine.index.tfidf.TfComputator;
import rdrozdz.searchengine.model.vo.DocumentId;
import rdrozdz.searchengine.model.vo.DocumentTokens;
import rdrozdz.searchengine.model.vo.Term;
import rdrozdz.searchengine.model.vo.Token;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static rdrozdz.searchengine.index.tfidf.TfIdfComputator.COMPUTATION_SCALE;

public class TfIndexImpl implements TfIndex, TfComputator {

    private Map<DocumentId, Document> index = new ConcurrentHashMap<>();

    @Override
    public void rebuild(DocumentTokens documentTokens) {
        DocumentId documentId = documentTokens.getDocumentId();
        List<Token> tokens = documentTokens.getTokens();
        Document document = new Document(tokens);
        index.put(documentId, document);
    }

    @Override
    public BigDecimal tfScore(Term term, DocumentId documentId) {
        Document document = index.get(documentId);
        double numberOfOccursTerm = document.occurs(term);
        return Try.of(() -> numberOfOccursTerm / document.tokensCount)
                .mapTry(BigDecimal::new)
                .mapTry(bd -> bd.setScale(COMPUTATION_SCALE, RoundingMode.HALF_UP))
                .getOrElse(BigDecimal.ZERO);
    }

    private static class Document {

        private Map<Token, TokenCounter> tokens = new HashMap<>();
        private int tokensCount;

        private Document(List<Token> tokens) {
            this.tokensCount = tokens.size();
            tokens.forEach(token -> {
                this.tokens.computeIfPresent(token, (k, v) -> v.increment());
                this.tokens.computeIfAbsent(token, k -> TokenCounter.init());
            });
        }

        private double occurs(Term term) {
            Token token = Token.of(term.getTerm());
            return tokens.getOrDefault(token, TokenCounter.ZERO)
                    .getCouner();
        }

        @Getter
        private static class TokenCounter {
            private static final TokenCounter ZERO = new TokenCounter(0);

            private int couner;

            private TokenCounter(int couner) {
                this.couner = couner;
            }

            private static TokenCounter init() {
                return new TokenCounter(1);
            }

            private TokenCounter increment() {
                couner++;
                return this;
            }
        }
    }
}
