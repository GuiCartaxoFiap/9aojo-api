package br.com.fiap.abctechapi.service.impl;

import br.com.fiap.abctechapi.handler.exception.MaxAssistsException;
import br.com.fiap.abctechapi.handler.exception.MininumAssistRequiredException;
import br.com.fiap.abctechapi.model.Order;
import br.com.fiap.abctechapi.repository.AssistanceRepository;
import br.com.fiap.abctechapi.repository.OrderRepository;
import br.com.fiap.abctechapi.service.OrderService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private static final String INVALID_ASSISTS = "Invalid Assists";
    private final OrderRepository orderRepository;
    private final AssistanceRepository assistanceRepository;

    @Override
    public void saveOrder(Order order, List<Long> assistIds) throws Exception {
        final var assistances = assistanceRepository.findByIdIn(assistIds);

        order.setAssists(assistances);

        validateOrder(order);

        orderRepository.save(order);
    }

    private void validateOrder(Order order) throws Exception {
        if (!order.hasMinAssists()) {
            throw new MininumAssistRequiredException(INVALID_ASSISTS, "Necessário no mínimo uma assistência");
        }

        if (order.exceedsMaxAssists()) {
            throw new MaxAssistsException(INVALID_ASSISTS, "Número máximo de assists é 15");
        }
    }

    @Override
    public List<Order> listOrderByOperator(Long operatorId) {
        return orderRepository.getOrdersByOperatorId(operatorId);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow();
    }
}
