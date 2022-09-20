package br.com.fiap.abctechapi.handler;

import br.com.fiap.abctechapi.handler.exception.MaxAssistsException;
import br.com.fiap.abctechapi.handler.exception.MininumAssistRequiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MininumAssistRequiredException.class)
    public ResponseEntity<ErrorMessageResponse> errorMinAssistRequired(MininumAssistRequiredException exception) {
        return getErrorMessageResponseResponseEntity(exception.getMessage(), exception.getDescription(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MaxAssistsException.class)
    public ResponseEntity<ErrorMessageResponse> errorMaxAssistance(MaxAssistsException exception) {
        return getErrorMessageResponseResponseEntity(exception.getMessage(), exception.getDescription(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorMessageResponse> getErrorMessageResponseResponseEntity(String message, String description, HttpStatus statusCode) {
        ErrorMessageResponse error = new ErrorMessageResponse();
        error.setMessage(message);
        error.setDescription(description);
        error.setTimeStamp(new Date());
        error.setStatusCode(statusCode.value());

        return new ResponseEntity<ErrorMessageResponse>(error, HttpStatus.BAD_REQUEST);
    }
}
