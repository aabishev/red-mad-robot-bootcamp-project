package kz.redmadrobot.redmadrobotbootcampproject.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import kz.redmadrobot.redmadrobotbootcampproject.exception.ApiRequestException;
import kz.redmadrobot.redmadrobotbootcampproject.exception.model.ErrorInfo;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class RestExceptionHandler {
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<ErrorInfo> handleCustomException(ApiRequestException exception, HttpServletRequest request) {
        ErrorInfo errorInfo = ErrorInfo.builder()
                .timestamp(new Date())
                .status(exception.getStatus().value())
                .error(exception.getStatus().getReasonPhrase())
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .exception(exception.getClass().getName())
                .method(request.getMethod())
                .url(request.getRequestURL().toString())
                .build();
        return new ResponseEntity<>(errorInfo, exception.getStatus());
    }
}
