package rdrozdz.searchengine.repository.exception;

import rdrozdz.searchengine.model.vo.DocumentId;

import static java.lang.String.format;

public class DocumentNotFoundExcpetion extends RuntimeException {
    private static final String ERR_MSG = "Cannot find document of id: %s";

    public DocumentNotFoundExcpetion(DocumentId documentId) {
        super(format(ERR_MSG, documentId.getId()));
    }
}
