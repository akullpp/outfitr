package de.akull.controller.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Handles all RuntimeExceptions which are not handled anywhere else.
 * <p>
 * The assumption is that we provide a public API where we don't want to expose internals about the system when, e.g.
 * a NullPointerException occurs.
 */
@Slf4j
@ControllerAdvice
@SuppressWarnings("unused")
public class RuntimeExceptionHandler {

    /**
     * Doesn't expose the message of the RuntimeException only the UUID and an unspecific 500 status code.
     */
    @ResponseBody
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ExceptionResponse handleRuntimeException(RuntimeException exception) {
        UUID uuid = UUID.randomUUID();
        log.error("{}", uuid, exception);

        return ExceptionResponse.builder()
                .uuid(uuid)
                .build();
    }
}
