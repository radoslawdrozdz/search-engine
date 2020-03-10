package rdrozdz.searchengine.index;

import rdrozdz.searchengine.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RecordLevelInvertedIndex implements InvertedIndex {

    private Map<Token, Documents> index = new ConcurrentHashMap<>();

    @Override
    public void rebuild(DocumentTokens documentTokens) {
        final DocumentId documentId = documentTokens.getDocumentId();
        documentTokens.getTokens().forEach(token -> {
            if (index.containsKey(token)) {
                this.addDocuementId(token, documentId);
            } else {
                this.createIndex(token, documentId);
            }
        });
    }

    private void createIndex(Token token, DocumentId documentId) {
        Documents documents = Documents.of(documentId);
        index.put(token, documents);
    }

    private void addDocuementId(Token token, DocumentId documentId) {
        Documents documents = index.get(token);
        documents.addDocumentId(documentId);
    }

    @Override
    public List<DocumentId> find(Term term) {
        Token token = termToToken(term);
        return this.index
                .getOrDefault(token, Documents.EMPTY)
                .documentIds;
    }

    private Token termToToken(Term term) {
        return Token.of(term.getTerm());
    }

    private static class Documents {
        private static final Documents EMPTY = new Documents(List.of());

        private List<DocumentId> documentIds;

        private Documents(List<DocumentId> documentIds) {
            this.documentIds = documentIds;
        }

        public static Documents of(DocumentId documentId) {
            List<DocumentId> documentIds = new ArrayList<>();
            documentIds.add(documentId);
            return new Documents(documentIds);
        }

        private void addDocumentId(DocumentId documentId) {
            this.documentIds.add(documentId);
        }
    }
}
