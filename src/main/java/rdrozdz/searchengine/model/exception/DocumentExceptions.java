package rdrozdz.searchengine.model.exception;

import rdrozdz.searchengine.model.vo.DocumentId;

import static java.lang.String.format;

public class DocumentExceptions {

    public static class DocumentCreationException extends RuntimeException {
        public DocumentCreationException(String message) {
            super(message);
        }
    }

    public static class DocumentNotFoundExcpetion extends RuntimeException {
        private static final String ERR_MSG = "Cannot find document of id: %s";

        public DocumentNotFoundExcpetion(DocumentId documentId) {
            super(format(ERR_MSG, documentId.getId()));
        }
    }
}
