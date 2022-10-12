package br.com.fiap.abctechapi.handler.exception;

import lombok.Getter;

@Getter
public class MininumAssistRequiredException extends RuntimeException {

    private static final String message = "Invalid Assists";
    private final String description;

    public MininumAssistRequiredException(final String description) {
        super(message);
        this.description = description;
    }
}
