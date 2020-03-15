package rdrozdz.searchengine.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import rdrozdz.searchengine.model.exception.TermExceptions.InvalidTermException;

@ControllerAdvice
public class TermAdvice {

    @ResponseBody
    @ExceptionHandler(InvalidTermException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String documentNotFoundHandler(InvalidTermException ex) {
        return ex.getMessage();
    }
}
