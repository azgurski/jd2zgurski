package com.azgurski.controller.exceptions;

import com.azgurski.util.RandomValuesGenerator;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.azgurski.controller.response.ApplicationErrorCodes.ENTITY_NOT_FOUND;
import static com.azgurski.controller.response.ApplicationErrorCodes.FATAL_ERROR;

@ControllerAdvice
@RequiredArgsConstructor
public class DefaultExceptionHandler {
    private static final Logger log = Logger.getLogger(DefaultExceptionHandler.class);

    private final RandomValuesGenerator generator;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleOthersException(Exception e) {
        /* Handles all other exceptions. Status code 500. */

        String exceptionUniqueId = generator.uuidGenerator();

        log.error(exceptionUniqueId + e.getMessage(), e);

        return new ResponseEntity<>(
                new ErrorMessage(
                        exceptionUniqueId,
                        FATAL_ERROR.getCodeId(),
                        e.getMessage() + " by checked exception handler."
                ),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> handleRuntimeException(RuntimeException e) {
        /* Handles all other exceptions. Status code 500. */

        String exceptionUniqueId = generator.uuidGenerator();

        log.error(exceptionUniqueId + e.getMessage(), e);

        return new ResponseEntity<>(
                new ErrorMessage(
                        exceptionUniqueId,
                        FATAL_ERROR.getCodeId(),
                        e.getMessage() + " by Runtime exception handler."
                ),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}