package rdrozdz.searchengine.model;

import lombok.Value;

import static java.lang.String.format;

@Value
public class DocumentId {
    private static final String ID_MUST_BE_POSITIVE_TEMPLATE = "cannot create DocumentId with id=%d, Id must be positive";

    private int id;

    private DocumentId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(format(ID_MUST_BE_POSITIVE_TEMPLATE, id));
        }
        this.id = id;
    }

    public static DocumentId of(int id) {
        return new DocumentId(id);
    }
}
