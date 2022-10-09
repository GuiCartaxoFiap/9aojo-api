package br.com.fiap.abctechapi.controller;

import br.com.fiap.abctechapi.application.dto.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.abctechapi.application.OrderApplication;
import br.com.fiap.abctechapi.application.dto.OrderDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private OrderApplication orderApplication;

    @Autowired
    public OrderController(OrderApplication orderApplication){
        this.orderApplication = orderApplication;
    }

    @PostMapping
    public ResponseEntity createOrder(@RequestBody OrderDto orderDto) throws Exception {
        orderApplication.createOrder(orderDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/operator/{operatorId}")
    public ResponseEntity<List<OrderResponseDto>> listOrdersOperator(@PathVariable Long operatorId){
        return ResponseEntity.ok(orderApplication.listOrderByOperatorId(operatorId));
    }
}
