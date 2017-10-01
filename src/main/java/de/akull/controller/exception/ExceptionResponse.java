package de.akull.controller.exception;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * Default format of an exception response for a consumer.
 * <p>
 * Saves a random UUID for lookup in the logs and a reader-friendly message.
 * <p>
 * Needs to be extended with an fields array once validation comes into play.
 */
@Data
@Builder
@SuppressWarnings("WeakerAccess")
public class ExceptionResponse {

    private UUID uuid;

    private String message;
}
