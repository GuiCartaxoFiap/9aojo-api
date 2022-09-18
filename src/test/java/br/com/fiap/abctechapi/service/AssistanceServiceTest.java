package br.com.fiap.abctechapi.service;

import br.com.fiap.abctechapi.model.Assistance;
import br.com.fiap.abctechapi.repository.AssistanceRepository;
import br.com.fiap.abctechapi.service.impl.AssistanceServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.when;

public class AssistanceServiceTest {

    @Mock
    private AssistanceRepository assistanceRepository;
    private AssistanceService assistanceService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        assistanceService = new AssistanceServiceImpl(assistanceRepository);
    }

    @Test
    @DisplayName("Listando assistências disponíveis :: Sucesso")
    public void list_success() {
        Assistance assistance1 = new Assistance(1L, "Mock assistance 1", "Description 1");
        Assistance assistance2 = new Assistance(2L, "Mock assistance 2", "Description 2");

        when(assistanceRepository.findAll()).thenReturn(List.of(assistance1, assistance2));

        List<Assistance> values = assistanceService.getAssistanceList();

        Assertions.assertEquals(values.size(), 2);
        Assertions.assertSame(values.get(0), assistance1);
        Assertions.assertSame(values.get(1), assistance1);
    }

    @Test
    @DisplayName("Listando assistências disponíveis :: Sucesso")
    public void list_error() {
        when(assistanceRepository.findAll()).thenReturn(List.of());

        List<Assistance> values = assistanceService.getAssistanceList();

        Assertions.assertEquals(0, values.size());
    }

}
