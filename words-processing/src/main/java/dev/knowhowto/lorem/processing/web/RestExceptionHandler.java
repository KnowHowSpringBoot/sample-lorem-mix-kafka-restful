package dev.knowhowto.lorem.processing.web;

import jakarta.validation.constraints.NotNull;
import java.util.List;

import dev.knowhowto.lorem.shared.web.dto.Error;
import dev.knowhowto.lorem.shared.web.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
@ConditionalOnMissingBean(RestControllerAdvice.class)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
  @NotNull
  protected ResponseEntity<Object> handleExceptionInternal(
      Exception ex,
      Object body,
      @NotNull HttpHeaders headers,
      @NotNull HttpStatus status,
      @NotNull WebRequest request
  ) {
    final var errorResponse = new ErrorResponse(List.of(new Error(ex.getMessage())));
    return new ResponseEntity<>(errorResponse, headers, status);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleIllegalEnumParameterException(@NotNull MethodArgumentTypeMismatchException exception) {
    return new ErrorResponse(List.of(new Error(exception.getCause().getCause().getMessage())));
  }
}
