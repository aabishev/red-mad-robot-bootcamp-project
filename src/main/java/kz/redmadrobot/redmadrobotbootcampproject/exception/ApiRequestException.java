package kz.redmadrobot.redmadrobotbootcampproject.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApiRequestException extends RuntimeException {
    private final String message;
    private final HttpStatus status;

    public ApiRequestException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }
}
