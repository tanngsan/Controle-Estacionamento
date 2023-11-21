package classes;

import exceptions.EstacionamentoFechadoException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

public class AcessoPorQuinzeTest {

    private AcessoPorQuinze acesso;

    @BeforeEach
    public void setUp() {
        // Inicializa uma nova instância de AcessoPorQuinze antes de cada teste.
        acesso = new AcessoPorQuinze();
    }

    @Test
    public void testCalculaValor() {
        // Verifica se o valor calculado para um acesso de 15 minutos é correto.
        double tarifa = 10.0; // Tarifa de R$10 por hora.
        Duration duracao = Duration.ofMinutes(15); // Duração do acesso.

        double resultado = acesso.calculaValor(duracao, tarifa);
        assertEquals(9.5, resultado, 0.0, "O valor calculado deve ser R$9,50 para um acesso de 15 minutos com tarifa de R$10,00 por hora.");
    }

    @Test
    public void testSetEntrada() {
        // Verifica se a entrada é configurada corretamente para um horário válido.
        LocalDate dia = LocalDate.now();
        LocalTime hora = LocalTime.of(12, 0); // Horário dentro do período de funcionamento.

        try {
            acesso.setEntrada(dia, hora);
            assertNotNull(acesso.getEntrada(), "A entrada não deve ser nula após ser configurada.");
            assertEquals(LocalDateTime.of(dia, hora), acesso.getEntrada(), "A entrada deve corresponder aos valores configurados.");
        } catch (EstacionamentoFechadoException e) {
            fail("Não deveria lançar EstacionamentoFechadoException para um horário válido.");
        }
    }

    @Test
    public void setEntrada_DeveLancarExcecaoParaHorarioFechado() {
        // Verifica se uma exceção é lançada ao tentar configurar a entrada para um horário fechado.
        LocalDate dia = LocalDate.now();
        LocalTime horaFechada = LocalTime.of(22, 0); // Horário após o fechamento do estacionamento.

        assertThrows(EstacionamentoFechadoException.class, () -> acesso.setEntrada(dia, horaFechada),
                     "Deve lançar EstacionamentoFechadoException para um horário após o fechamento do estacionamento.");
    }
}
