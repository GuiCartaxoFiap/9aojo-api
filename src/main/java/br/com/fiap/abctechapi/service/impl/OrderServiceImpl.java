package br.com.fiap.abctechapi.service.impl;

import br.com.fiap.abctechapi.handler.exception.InvalisASsistsSearchException;
import br.com.fiap.abctechapi.handler.exception.MaxAssistsException;
import br.com.fiap.abctechapi.handler.exception.MininumAssistRequiredException;
import br.com.fiap.abctechapi.model.Assistance;
import br.com.fiap.abctechapi.model.Order;
import br.com.fiap.abctechapi.repository.AssistanceRepository;
import br.com.fiap.abctechapi.repository.OrderRepository;
import br.com.fiap.abctechapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final AssistanceRepository assistanceRepository;

    @Override
    public void saveOrder(Order order, List<Long> assistIds) {
        final var assistances = assistanceRepository.findByIdIn(assistIds);

        validateAssistanceSearch(assistIds, assistances);

        order.setAssists(assistances);

        validateOrder(order);

        orderRepository.save(order);
    }

    private void validateAssistanceSearch(List<Long> ids, List<Assistance> assistances) {
        final var assistancesFound = assistances.stream()
                .map(Assistance::getName)
                .collect(Collectors.joining(",", "(", ")"));

        if (ids.size() != assistances.size()) {
            final var message = String.format(
                    "Nem todas as assistencias foram encontradas, apenas %d foram sendo elas %s",
                    assistances.size(),
                    assistancesFound);
            log.warn(message);
            throw new InvalisASsistsSearchException(message);
        }
    }

    private void validateOrder(Order order) {
        if (!order.hasMinAssists()) {
            throw new MininumAssistRequiredException("Necessário no mínimo uma assistência");
        }

        if (order.exceedsMaxAssists()) {
            throw new MaxAssistsException("Número máximo de assists é 15");
        }
    }

    @Override
    public List<Order> listOrderByOperator(Long operatorId) {
        return orderRepository.getOrdersByOperatorId(operatorId);
    }
}
