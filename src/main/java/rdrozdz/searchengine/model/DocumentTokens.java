package rdrozdz.searchengine.model;

import lombok.Getter;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

@Getter
public class DocumentTokens {
    private DocumentId documentId;
    private List<Token> tokens;

    public DocumentTokens(DocumentId documentId, List<Token> tokens) {
        this.documentId = requireNonNull(documentId);
        this.tokens = requireNonNull(tokens);
    }
}
