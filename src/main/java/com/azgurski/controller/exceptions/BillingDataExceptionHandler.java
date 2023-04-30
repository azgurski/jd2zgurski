package com.azgurski.controller.exceptions;

import com.azgurski.util.RandomValuesGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.azgurski.controller.response.ApplicationErrorCodes.ENTITY_NOT_CREATED;
import static com.azgurski.controller.response.ApplicationErrorCodes.ENTITY_NOT_FOUND;
import static com.azgurski.controller.response.ApplicationErrorCodes.FATAL_ERROR;
import static com.azgurski.controller.response.ApplicationErrorCodes.INVALID_INPUT_FORMAT_ERROR;

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
