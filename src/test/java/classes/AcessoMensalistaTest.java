package classes;

import classes.AcessoMensalista;
import exceptions.EstacionamentoFechadoException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

public class AcessoMensalistaTest {

    private AcessoMensalista acesso;

    @BeforeEach
    public void setUp() {
        // Configuração inicial para cada teste: cria uma instância de AcessoMensalista.
        acesso = new AcessoMensalista();
    }

    @Test
    public void testCalculaValor() {
        // Teste para verificar se o método calculaValor retorna 0 para um acesso mensalista,
        // conforme definido na implementação atual da classe.
        double resultado = acesso.calculaValor(Duration.ofMinutes(60), acesso.getTarifa());
        assertEquals(0.0, resultado, 0.0, "O valor calculado deve ser 0 para AcessoMensalista");
    }

    @Test
    public void testSetEntrada() {
        // Teste para verificar se o método setEntrada configura corretamente a entrada do acesso mensalista.
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
    public void setEntrada_DeveLancarExcecaoParaDataHoraInvalida() {
        // Teste para verificar se o método setEntrada lança uma EstacionamentoFechadoException
        // para uma data e hora consideradas inválidas.
        LocalDate dataInvalida = LocalDate.of(2023, 1, 1);
        LocalTime horaInvalida = LocalTime.of(23, 59); // Supondo que este seja um horário inválido

        assertThrows(EstacionamentoFechadoException.class, () -> acesso.setEntrada(dataInvalida, horaInvalida),
                     "Deve lançar EstacionamentoFechadoException para data e hora inválidas");
    }
}
