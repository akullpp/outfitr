package de.akull.controller.exception;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@SuppressWarnings("WeakerAccess")
public class ExceptionResponse {

    private UUID uuid;

    private String message;
}
