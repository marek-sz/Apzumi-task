package pl.apzumi.task.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import pl.apzumi.task.exception.CustomErrorMessage;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomErrorMessage entityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        return new CustomErrorMessage(HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                ex.getMessage());
    }

}
