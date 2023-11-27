package classes;

import classes.AcessoPorDiaria;
import exceptions.EstacionamentoFechadoException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

public class AcessoPorDiariaTest {

    private AcessoPorDiaria acesso;

    @BeforeEach
    public void setUp() {
        // Configuração inicial para cada teste: cria uma instância de AcessoPorDiaria.
        acesso = new AcessoPorDiaria();
    }

    @Test
    public void testCalculaValor() {
        // Teste para verificar se o método calculaValor retorna o valor esperado para um acesso por diária.
        // Neste caso, estamos assumindo que a tarifa para um período de 10 horas é de 130.
        double resultado = acesso.calculaValor(Duration.ofHours(10), acesso.getTarifa());
        assertEquals(130.0, resultado, 0.0, "O valor calculado deve ser 130 para AcessoPorDiaria");
    }

    @Test
    public void testSetEntrada() {
        // Teste para verificar se o método setEntrada configura corretamente a entrada do acesso por diária.
        // Este teste também verifica se a entrada não é nula e corresponde aos valores configurados.
        LocalDate dia = LocalDate.now();
        LocalTime hora = LocalTime.of(12, 0);

        try {
            acesso.setEntrada(dia, hora);
            assertNotNull(acesso.getEntrada(), "A entrada não deve ser nula após ser configurada");
            assertEquals(LocalDateTime.of(dia, hora), acesso.getEntrada(), "A entrada deve corresponder aos valores configurados");
        } catch (EstacionamentoFechadoException e) {
            fail("Não deveria lançar EstacionamentoFechadoException para um horário válido");
        }
    }

    @Test
    public void setEntrada_DeveLancarExcecaoParaHorarioFechado() {
        // Teste para verificar se o método setEntrada lança uma EstacionamentoFechadoException
        // para um horário considerado fechado (neste caso, após as 20:00).
        LocalDate dia = LocalDate.now();
        LocalTime horaFechada = LocalTime.of(22, 0); // Supondo que este seja um horário fechado

        assertThrows(EstacionamentoFechadoException.class, () -> acesso.setEntrada(dia, horaFechada),
                     "Deve lançar EstacionamentoFechadoException para horário fechado");
    }
}
