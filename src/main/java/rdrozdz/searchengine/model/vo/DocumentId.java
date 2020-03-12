package rdrozdz.searchengine.model.vo;

import lombok.Value;

import static java.util.Objects.requireNonNull;

@Value
public class DocumentId {
    private String id;

    private DocumentId(String id) {
        this.id = requireNonNull(id);
    }

    public static DocumentId of(String id) {
        return new DocumentId(id);
    }
}
