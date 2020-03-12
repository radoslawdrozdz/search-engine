package rdrozdz.searchengine.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import rdrozdz.searchengine.repository.exception.DocumentNotFoundExcpetion;

@ControllerAdvice
public class DocumentNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(DocumentNotFoundExcpetion.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(DocumentNotFoundExcpetion ex) {
        return ex.getMessage();
    }
}
