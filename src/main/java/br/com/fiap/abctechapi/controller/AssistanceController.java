package br.com.fiap.abctechapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.abctechapi.model.Assistance;
import br.com.fiap.abctechapi.service.AssistanceService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/assistance")
@RequiredArgsConstructor
public class AssistanceController {

    private final AssistanceService service;

    @GetMapping()
    public ResponseEntity<List<Assistance>> getAssists(){

        List<Assistance> list = service.getAssistanceList();
        return ResponseEntity.ok(list);
    }

}
