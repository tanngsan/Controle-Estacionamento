package classes;

import classes.AcessoPorPernoite;
import exceptions.EstacionamentoFechadoException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

public class AcessoPorPernoiteTest {

    private AcessoPorPernoite acesso;

    @BeforeEach
    public void setUp() {
        // Configuração inicial para cada teste: cria uma instância de AcessoPorPernoite.
        acesso = new AcessoPorPernoite();
    }

    @Test
    public void testCalculaValorMenorQueDezenoveHoras() {
        // Teste para verificar se o método calculaValor retorna o valor correto
        // para um acesso por pernoite com duração menor que 19 horas.
        double resultado = acesso.calculaValor(Duration.ofHours(15), acesso.getTarifa());
        assertEquals(45.0, resultado, 0.0, "O valor calculado deve ser 45.0 para um acesso por pernoite com duração de 15 horas");
    }

    @Test
    public void testCalculaValorMaiorQueDezenoveHoras() {
        // Teste para verificar se o método calculaValor retorna o valor correto
        // para um acesso por pernoite com duração maior que 19 horas.
        double resultado = acesso.calculaValor(Duration.ofHours(25), acesso.getTarifa());
        assertEquals(50.0, resultado, 0.0, "O valor calculado deve ser 50.0 para um acesso por pernoite com duração de 25 horas");
    }

    @Test
    public void testSetEntradaHoraFechada() {
        // Teste para verificar se o método setEntrada lança uma EstacionamentoFechadoException
        // quando a hora de entrada está fora do horário de funcionamento do estacionamento.
        LocalDate dia = LocalDate.now();
        LocalTime hora = LocalTime.of(5, 0); // Hora de entrada antes do horário de funcionamento (6:00)

        assertThrows(EstacionamentoFechadoException.class, () -> acesso.setEntrada(dia, hora),
                     "Deve lançar EstacionamentoFechadoException para hora de entrada fora do horário de funcionamento");
    }

    @Test
    public void testSetEntradaHoraAberta() {
        // Teste para verificar se o método setEntrada configura corretamente a entrada do acesso por pernoite.
        LocalDate dia = LocalDate.now();
        LocalTime hora = LocalTime.of(12, 0); // Hora de entrada dentro do horário de funcionamento

        try {
            acesso.setEntrada(dia, hora);
            assertNotNull(acesso.getEntrada(), "A entrada não deve ser nula após ser configurada");
            assertEquals(LocalDateTime.of(dia, hora), acesso.getEntrada(), "A entrada deve corresponder aos valores configurados");
        } catch (EstacionamentoFechadoException e) {
            fail("Não deveria lançar EstacionamentoFechadoException para um horário válido");
        }
    }
}