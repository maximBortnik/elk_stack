package com.example.springbootdockerelk.error;

import com.example.springbootdockerelk.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

/**
 * @author bortnik
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(final Exception exp) {
        return responseEntity(apiError(HttpStatus.INTERNAL_SERVER_ERROR, exp));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(final RuntimeException ex) {
        return responseEntity(apiError(HttpStatus.BAD_REQUEST, ex.getMessage(), ex));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(final EntityNotFoundException ex) {
        return responseEntity(apiError(HttpStatus.NOT_FOUND, ex.getMessage(), ex));
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(final Exception ex,
                                                             @Nullable final Object body,
                                                             final HttpHeaders headers,
                                                             final HttpStatus status,
                                                             final WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        return responseEntity(headers, apiError(status, ex.getMessage(), ex));
    }

    private ApiError apiError(final HttpStatus status,
                              final Throwable throwable) {
        LOGGER.error("Unexpected error", throwable);
        return new ApiError(status, "Unexpected error", throwable);
    }

    private ApiError apiError(final HttpStatus status,
                              final String message,
                              final Throwable throwable) {
        LOGGER.error(message, throwable);
        return new ApiError(status, message, throwable);
    }

    private ResponseEntity<Object> responseEntity(final ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }

    private ResponseEntity<Object> responseEntity(final HttpHeaders headers,
                                                  final ApiError apiError) {
        return new ResponseEntity<>(apiError, headers, apiError.getHttpStatus());
    }

}
