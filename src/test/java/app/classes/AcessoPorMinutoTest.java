package app.classes;
import classes.AcessoPorMinuto;
import exceptions.EstacionamentoFechadoException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class AcessoPorMinutoTest {

    private AcessoPorMinuto acesso;

    @BeforeEach
    public void setUp() {
        acesso = new AcessoPorMinuto();
        acesso.setTarifa(0.5);
    }

    @Test
    public void testCalculaValor() {
        double resultado1 = acesso.calculaValor(Duration.ofMinutes(30), acesso.getTarifa());
        assertEquals(15.0, resultado1, 0.0);

        double resultado2 = acesso.calculaValor(Duration.ofMinutes(60), acesso.getTarifa());
        assertEquals(30.0, resultado2, 0.0);
    }

    @Test
    public void testSetEntrada() {
        try {
            acesso.setEntrada(LocalDate.now(), LocalTime.of(12, 0));
            assertNotNull(acesso.getEntrada());
        } catch (EstacionamentoFechadoException e) {
            fail("Não deveria lançar EstacionamentoFechadoException");
        }

        try {
            acesso.setEntrada(LocalDate.now(), LocalTime.of(4, 0));
            fail("Deveria lançar EstacionamentoFechadoException");
        } catch (EstacionamentoFechadoException e) {
            // Esperado
        }
    }

    @Test
    public void testIntegracao() {
        try {
            acesso.setEntrada(LocalDate.now(), LocalTime.of(12, 0));
        } catch (EstacionamentoFechadoException e) {
            fail("Não deveria lançar EstacionamentoFechadoException");
        }

        assertEquals(60.0, acesso.calculaValor(Duration.ofMinutes(60), acesso.getTarifa()), 0.0);
    }

    @Test
    public void testRegressao() {
        acesso.setTarifa(0.75);
        double resultado1 = acesso.calculaValor(Duration.ofMinutes(45), acesso.getTarifa());
        assertEquals(33.75, resultado1, 0.0);
    }
}