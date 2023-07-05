package com.development.ssosung.global.exception;

import com.development.ssosung.global.common.SsoSungApiResponse;
import com.development.ssosung.global.common.SsoSungStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class RestControllerExceptionHandler {


    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<SsoSungApiResponse> handleApiRequestException(BadRequestException e){
        SsoSungApiResponse ssoSungApiException = new SsoSungApiResponse(
                SsoSungStatus.BAD_REQUEST,
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(ssoSungApiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleApiRequestException(MethodArgumentNotValidException e) {
        SsoSungApiResponse ssoSungApiException = new SsoSungApiResponse(
                SsoSungStatus.SYSTEM_ERROR,
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(ssoSungApiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
