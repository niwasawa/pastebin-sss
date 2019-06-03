package info.maigo.lab.pastebin.application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handlePastebinException(Exception e, WebRequest request) {
        return handleExceptionInternal(e, "Error: " + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
