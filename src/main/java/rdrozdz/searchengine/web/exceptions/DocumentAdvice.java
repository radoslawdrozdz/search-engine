package rdrozdz.searchengine.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import rdrozdz.searchengine.model.exception.DocumentExceptions;

@ControllerAdvice
public class DocumentAdvice {

    @ResponseBody
    @ExceptionHandler(DocumentExceptions.DocumentNotFoundExcpetion.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String documentNotFoundHandler(DocumentExceptions.DocumentNotFoundExcpetion ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(DocumentExceptions.DocumentCreationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String documentCreationExceptionHandler(DocumentExceptions.DocumentCreationException ex) {
        return ex.getMessage();
    }
}
