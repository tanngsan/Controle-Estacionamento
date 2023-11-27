package app;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import classes.SistemaEstacionamento;
import classes.Veiculo;
import exceptions.DadosVeiculosIncompletosException;


@ExtendWith(MockitoExtension.class)
public class MainTest {

    private SistemaEstacionamento sisEstacionamentoMock;
    private Main main;

    @BeforeEach
    public void setUp() {
        sisEstacionamentoMock = mock(SistemaEstacionamento.class);
        main = new Main(sisEstacionamentoMock);
    }

    @Test
    public void testProcessaCadastroRotativoVeiculoNaoCadastrado() throws DadosVeiculosIncompletosException {
        // Configura o mock para retornar null, simulando um veículo não cadastrado
        when(sisEstacionamentoMock.buscarVeiculo("ABC1234")).thenReturn(null);

        Veiculo veiculo = main.processaCadastroRotativo("Marca", "Modelo", "ABC1234");

        assertNotNull(veiculo);
        assertEquals("Marca", veiculo.getMarca());
        assertEquals("Modelo", veiculo.getModelo());
        assertEquals("ABC1234", veiculo.getNumeroPlaca());

        // Verifica se o método cadastrarVeiculo foi chamado no mock
        verify(sisEstacionamentoMock).cadastrarVeiculo(veiculo);
    }

    @Test
    public void testProcessaCadastroRotativoVeiculoJaCadastrado() {
        // Configura o mock para retornar um veículo, simulando um veículo já cadastrado
        when(sisEstacionamentoMock.buscarVeiculo("ABC1234")).thenReturn(new Veiculo("Marca", "Modelo", "ABC1234"));

        Exception exception = assertThrows(DadosVeiculosIncompletosException.class, () -> {
            main.processaCadastroRotativo("Marca", "Modelo", "ABC1234");
        });

        String expectedMessage = "Veículo já cadastrado!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
