package github.Jimoou.departmentservice.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorDetails> handleResourceNotFound(
      ResourceNotFoundException exception, WebRequest webRequest) {
    ErrorDetails errorDetails =
        new ErrorDetails(
            LocalDateTime.now(),
            exception.getMessage(),
            webRequest.getDescription(false),
            "부서를 찾을 수 없습니다.");
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDetails> handleGlobalException(
      Exception exception, WebRequest webRequest) {
    ErrorDetails errorDetails =
        new ErrorDetails(
            LocalDateTime.now(),
            exception.getMessage(),
            webRequest.getDescription(false),
            "INTERNAL_SERVER_ERROR");
    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
