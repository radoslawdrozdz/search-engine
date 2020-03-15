package rdrozdz.searchengine.model.exception;

public class TermExceptions {

    public static class InvalidTermException extends RuntimeException {
        public InvalidTermException(String message) {
            super(message);
        }
    }
}
