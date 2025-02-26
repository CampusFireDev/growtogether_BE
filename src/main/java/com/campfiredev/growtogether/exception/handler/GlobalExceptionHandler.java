package com.campfiredev.growtogether.exception.handler;

import static com.campfiredev.growtogether.exception.response.ErrorCode.INTERNAL_SERVER_ERROR;
import static com.campfiredev.growtogether.exception.response.ErrorCode.INVALID_INPUT_DATA;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.campfiredev.growtogether.exception.custom.CustomException;
import com.campfiredev.growtogether.exception.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * CustomException 처리 핸들러
   * @param e 발생한 CustomException
   * @return ErrorResponse
   */
  @ExceptionHandler(CustomException.class)
  public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
    return ResponseEntity.status(e.getStatus()).body(new ErrorResponse(e.getErrorCode()));
  }

  /**
   * @Valid 유효성 검사 실패 (MethodArgumentNotValidException) 처리 핸들러
   * @param e 발생한 MethodArgumentNotValidException
   * @return ErrorResponse (잘못된 입력 데이터 관련 에러 메시지 포함)
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException e) {

    String description = e.getBindingResult().getFieldErrors().stream()
        .map(error -> error.getField() + ": " + error.getDefaultMessage())
        .findFirst().orElse(INVALID_INPUT_DATA.getDescription());

    return ResponseEntity.status(BAD_REQUEST)
        .body(new ErrorResponse(INVALID_INPUT_DATA, description));
  }

  /**
   * JSON 요청 데이터 파싱 오류 처리 핸들러
   *
   * 클라이언트가 잘못된 형식의 JSON 데이터를 전송했을때 발생하는 예외처이
   * ex) Integer 필드에서 문자가 포함되었을때 발생하는 에러
   *
   * @param e 발생한 `HttpMessageNotReadableException`
   * @return 400 Bad Request 응답과 함께 에러 메시지 반환
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorResponse> handleHttpMessageNotReadeableException(HttpMessageNotReadableException e) {

    return ResponseEntity.status(BAD_REQUEST).body(new ErrorResponse(INVALID_INPUT_DATA, e.getMessage()));
  }

  /**
   * 예상하지 못한 예외(Exception) 처리 핸들러
   * @param e 발생한 예외
   * @return ErrorResponse
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception e) {
    return ResponseEntity.status(INTERNAL_SERVER_ERROR.getStatus()).body(new ErrorResponse(INTERNAL_SERVER_ERROR));
  }

}
