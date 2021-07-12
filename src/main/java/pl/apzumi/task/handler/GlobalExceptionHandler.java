package pl.apzumi.task.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {ResponseStatusException.class})
    public String handleApiRequestException1(ResponseStatusException e) {
        return e.getMessage();
    }
}
