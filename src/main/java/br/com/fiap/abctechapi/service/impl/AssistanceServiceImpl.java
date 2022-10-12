package br.com.fiap.abctechapi.service.impl;

import br.com.fiap.abctechapi.model.Assistance;
import br.com.fiap.abctechapi.repository.AssistanceRepository;
import br.com.fiap.abctechapi.service.AssistanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssistanceServiceImpl implements AssistanceService {

    private final AssistanceRepository repository;

    @Override
    public List<Assistance> getAssistanceList() {
        return repository.findAll();
    }
}
