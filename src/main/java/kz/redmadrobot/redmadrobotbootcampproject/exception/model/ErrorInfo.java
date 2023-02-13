package kz.redmadrobot.redmadrobotbootcampproject.exception.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class ErrorInfo {
    private final Date timestamp;
    private final int status;
    private final String error;
    private final String message;
    private final String path;
    private final String exception;
    private final String method;
    private final String url;
}
