package ru.kir.sm.sensormeasurementrestapp.handler;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Map;

public interface GlobalExceptionHandler {

    Map<String, String> handleConstraintViolationException(ConstraintViolationException ex);

    Map<String, String> handleOtherExceptions(Exception ex);

    ResponseEntity<ProblemDetail> handleValidationExceptions(MethodArgumentNotValidException ex);
}
