package rdrozdz.searchengine.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

import static java.util.Objects.requireNonNull;

@Getter
@EqualsAndHashCode
public class DocumentTokens {
    private DocumentId documentId;
    private List<Token> tokens;

    public DocumentTokens(DocumentId documentId, List<Token> tokens) {
        this.documentId = requireNonNull(documentId);
        this.tokens = requireNonNull(tokens);
    }
}
