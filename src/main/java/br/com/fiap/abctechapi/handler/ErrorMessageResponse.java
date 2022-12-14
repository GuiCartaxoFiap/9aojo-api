package br.com.fiap.abctechapi.handler;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorMessageResponse {

    private Integer statusCode;
    private Date timeStamp;
    private String message;
    private String description;
}
