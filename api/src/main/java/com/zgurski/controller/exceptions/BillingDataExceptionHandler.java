package com.zgurski.controller.exceptions;

import com.zgurski.util.RandomValuesGenerator;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.zgurski.controller.response.ApplicationErrorCodes.ENTITY_NOT_CREATED;
import static com.zgurski.controller.response.ApplicationErrorCodes.ENTITY_NOT_FOUND;

@ControllerAdvice
@RequiredArgsConstructor
public class BillingDataExceptionHandler {

    private static final Logger log = Logger.getLogger(BillingDataExceptionHandler.class);

    private final RandomValuesGenerator generator;

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(BillingDataNotFoundException e) {

        String exceptionUniqueId = generator.uuidGenerator();

        log.error(exceptionUniqueId + e.getMessage(), e);

        return new ResponseEntity<>(
                new ErrorMessage(
                        exceptionUniqueId,
                        ENTITY_NOT_FOUND.getCodeId(),
                        e.getMessage() + " [thanks to Billing data exception handler]"
                        //e.getMessage()
                ),
                HttpStatus.NOT_FOUND); // 404
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(BillingDataNotCreatedException e) {
        String exceptionUniqueId = generator.uuidGenerator();

        log.error(exceptionUniqueId + e.getMessage(), e);

        return new ResponseEntity<>(
                new ErrorMessage(
                        exceptionUniqueId,
                        ENTITY_NOT_CREATED.getCodeId(),
                        e.getMessage() + " [thanks to Billing data exception handler]"
                        //e.getMessage()
                ),
                HttpStatus.BAD_REQUEST); // 400
    }

}
