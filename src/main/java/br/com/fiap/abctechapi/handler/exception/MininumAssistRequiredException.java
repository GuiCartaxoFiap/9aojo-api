package br.com.fiap.abctechapi.handler.exception;

import lombok.Getter;

@Getter
public class MininumAssistRequiredException extends RuntimeException {

    private String description;

    public MininumAssistRequiredException(String message, String description) {
        super(message);
        this.description = description;
    }
}
