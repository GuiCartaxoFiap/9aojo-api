package br.com.fiap.abctechapi.application.impl;

import br.com.fiap.abctechapi.application.AssistanceApplication;
import br.com.fiap.abctechapi.application.dto.AssistDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssistanceApplicationImpl implements AssistanceApplication {

    @Override
    public List<AssistDto> getAssists() {
        return null;
    }
}
