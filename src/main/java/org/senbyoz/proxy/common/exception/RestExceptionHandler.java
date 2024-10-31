package org.senbyoz.proxy.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.ResponseEntity.notFound;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity postNotFound(EntityNotFoundException ex) {
        log.debug("handling exception::" + ex);
        return notFound().build();
    }
}
