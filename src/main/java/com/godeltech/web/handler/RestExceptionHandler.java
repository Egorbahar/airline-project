package com.godeltech.web.handler;

import com.godeltech.exception.AbsenceAircraftException;
import com.godeltech.exception.DateTimeMismatchException;
import com.godeltech.exception.ResourceNotFoundException;
import com.godeltech.exception.UnderstaffedFlightException;
import com.godeltech.web.dto.response.ErrorResponseDto;
import com.godeltech.web.dto.response.ExceptionResponseDto;
import com.godeltech.web.validator.Violation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatus httpStatus,
                                                                  WebRequest request) {
        final List<Violation> violations = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        log.error(exception.getMessage());
        return new ResponseEntity<>(new ErrorResponseDto(httpStatus, violations), httpStatus);
    }
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        final ExceptionResponseDto errorResponseDto = new ExceptionResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        log.error(exception.getMessage());
        return handleExceptionInternal(exception, errorResponseDto, new HttpHeaders(), errorResponseDto.getHttpStatus(), request);
    }
    @ExceptionHandler(value = UnderstaffedFlightException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(UnderstaffedFlightException exception, WebRequest request) {
        final ExceptionResponseDto errorResponseDto = new ExceptionResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        log.error(exception.getMessage());
        return handleExceptionInternal(exception, errorResponseDto, new HttpHeaders(), errorResponseDto.getHttpStatus(), request);
    }
    @ExceptionHandler(value = AbsenceAircraftException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(AbsenceAircraftException exception, WebRequest request) {
        final ExceptionResponseDto errorResponseDto = new ExceptionResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        log.error(exception.getMessage());
        return handleExceptionInternal(exception, errorResponseDto, new HttpHeaders(), errorResponseDto.getHttpStatus(), request);
    }
    @ExceptionHandler(value = DateTimeMismatchException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(DateTimeMismatchException exception, WebRequest request) {
        final ExceptionResponseDto errorResponseDto = new ExceptionResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        log.error(exception.getMessage());
        return handleExceptionInternal(exception, errorResponseDto, new HttpHeaders(), errorResponseDto.getHttpStatus(), request);
    }
}
