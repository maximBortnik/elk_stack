package com.example.springbootdockerelk.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 * @author bortnik
 */
@Data
public class ApiError {

    private String message;
    private Throwable throwable;
    private HttpStatus httpStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private ZonedDateTime timestamp;

    public ApiError(HttpStatus httpStatus, String message, Throwable throwable) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.throwable = throwable;
        this.timestamp = ZonedDateTime.now();
    }

}
