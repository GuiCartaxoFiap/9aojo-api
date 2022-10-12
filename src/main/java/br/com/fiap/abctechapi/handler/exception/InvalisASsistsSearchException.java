package br.com.fiap.abctechapi.handler.exception;

import lombok.Getter;

@Getter
public class InvalisASsistsSearchException extends RuntimeException {

    private static final String message = "Invalid Assists";
    private final String description;

    public InvalisASsistsSearchException(final String description) {
        super(message);
        this.description = description;
    }
}