package br.com.fiap.abctechapi.service;

import br.com.fiap.abctechapi.handler.exception.InvalisASsistsSearchException;
import br.com.fiap.abctechapi.handler.exception.MaxAssistsException;
import br.com.fiap.abctechapi.handler.exception.MininumAssistRequiredException;
import br.com.fiap.abctechapi.model.Assistance;
import br.com.fiap.abctechapi.model.Order;
import br.com.fiap.abctechapi.repository.AssistanceRepository;
import br.com.fiap.abctechapi.repository.OrderRepository;
import br.com.fiap.abctechapi.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private AssistanceRepository assistanceRepository;

    private OrderService orderService;

    @BeforeEach
    public void init() {
        this.orderService = new OrderServiceImpl(orderRepository, assistanceRepository);
    }

    @Test
    public void order_service_not_null() {
        Assertions.assertNotNull(orderService);
    }

    @Test
    public void create_order_error_minimum() {
        final var order = new Order();

        when(assistanceRepository.findByIdIn(Collections.emptyList())).thenReturn(Collections.emptyList());

        assertThrows(
                MininumAssistRequiredException.class,
                () -> orderService.saveOrder(order, Collections.emptyList()));
        verify(orderRepository, times(0)).save(order);
    }

    @Test
    public void create_order_error_maximum() {
        Order newOrder = new Order();

        final var ids = generateMocksIds(20);
        final var assistances = generateAssistanceMockList(ids);

        when(assistanceRepository.findByIdIn(ids)).thenReturn(assistances);

        assertThrows(MaxAssistsException.class, () -> orderService.saveOrder(newOrder, ids));
        verify(orderRepository, times(0)).save(newOrder);
    }

    @Test
    public void create_order_error_find_assistance_match() {
        final var order = new Order();
        final var ids = generateMocksIds(2);
        final var assistance = generateMockAssistance(ids.get(0));

        when(assistanceRepository.findByIdIn(ids)).thenReturn(List.of(assistance));

        assertThrows(InvalisASsistsSearchException.class, () -> orderService.saveOrder(order, ids));
        verify(orderRepository, times(0)).save(order);
    }

    @Test
    public void create_order_success() throws Exception {
        Order newOrder = new Order();

        final var ids = generateMocksIds(5);
        final var assistances = generateAssistanceMockList(ids);

        when(assistanceRepository.findByIdIn(ids)).thenReturn(assistances);

        orderService.saveOrder(newOrder, ids);
        verify(orderRepository, times(1)).save(newOrder);
    }

    private List<Long> generateMocksIds(int number) {
        final var listOfIds = new ArrayList<Long>();
        for (int x = 1; x <= number; x++) {
            listOfIds.add(Integer.toUnsignedLong(x));
        }
        return listOfIds;
    }

    private List<Assistance> generateAssistanceMockList(final List<Long> ids) {
        return ids.stream()
                .map(this::generateMockAssistance)
                .collect(Collectors.toList());
    }

    private Assistance generateMockAssistance(final Long id) {
        final var description = String.format("Teste description %d", id);
        final var name = String.format("Teste %d", id);
        return new Assistance(id, name, description);
    }

}
