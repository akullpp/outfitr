package de.akull.controller.exception;

import de.akull.utility.Messages;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@Slf4j
@ControllerAdvice
@SuppressWarnings("unused")
@Priority(HIGHEST_PRECEDENCE)
public class FeignExceptionHandler {

    private Messages messages;

    @Autowired
    public FeignExceptionHandler(Messages messages) {
        this.messages = messages;
    }

    @ResponseBody
    @ExceptionHandler(FeignException.class)
    public ExceptionResponse handleFeignException(FeignException exception, HttpServletResponse response) {
        UUID uuid = UUID.randomUUID();
        log.error("{}", uuid, exception);

        int status = exception.status();
        ExceptionResponse.ExceptionResponseBuilder builder = ExceptionResponse.builder();
        builder.uuid(uuid);

        switch (status) {
            case 404:
                response.setStatus(404);
                builder.message(messages.get("omw.exception.city"));
                break;
            default:
                response.setStatus(500);
                break;
        }
        return builder.build();
    }
}
