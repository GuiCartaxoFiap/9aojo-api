package br.com.fiap.abctechapi.handler.exception;

import lombok.Getter;

@Getter
public class MaxAssistsException extends  RuntimeException{

    private static final String message = "Invalid Assists";
    private final String description;

    public MaxAssistsException(final String description) {
        super(message);
        this.description = description;
    }
}
