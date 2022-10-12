package br.com.fiap.abctechapi.controller;

import java.util.List;

import br.com.fiap.abctechapi.application.AssistanceApplication;
import br.com.fiap.abctechapi.application.dto.AssistDto;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assistance")
@RequiredArgsConstructor
public class AssistanceController {

    private final AssistanceApplication assistanceApplication;

    @GetMapping()
    public ResponseEntity<List<AssistDto>> getAssists() {
        List<AssistDto> list = assistanceApplication.getAssists();
        return ResponseEntity.ok(list);
    }

}
