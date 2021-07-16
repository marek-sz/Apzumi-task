package pl.apzumi.task.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.MethodNotAllowedException;
import pl.apzumi.task.exception.CustomErrorMessage;

import javax.persistence.EntityNotFoundException;
import java.net.ConnectException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomErrorMessage entityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        return new CustomErrorMessage(HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false));
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public CustomErrorMessage bodyIsMissingException(MethodNotAllowedException ex, WebRequest request) {
        return new CustomErrorMessage(HttpStatus.METHOD_NOT_ALLOWED.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
    }

    @ExceptionHandler(ConnectException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public CustomErrorMessage connectionFailed(ConnectException ex, WebRequest request) {
        return new CustomErrorMessage(HttpStatus.SERVICE_UNAVAILABLE.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
    }
}