import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import classes.AcessoPorMinuto;
import exceptions.EstacionamentoFechadoException;

public class AcessoPorMinutoTest {

    @Test
    public void testCalculaValor() {
        AcessoPorMinuto acesso = new AcessoPorMinuto();
        acesso.setTarifa(0.5);

        // Testa o cálculo para 30 minutos com tarifa de 0.5
        double resultado1 = acesso.calculaValor(Duration.ofMinutes(30), acesso.getTarifa());
        assertEquals(15.0, resultado1, 0.0);

        // Testa o cálculo para 60 minutos com tarifa de 0.5
        double resultado2 = acesso.calculaValor(Duration.ofMinutes(60), acesso.getTarifa());
        assertEquals(30.0, resultado2, 0.0);
    }

    @Test
    public void testSetEntrada() {
        AcessoPorMinuto acesso = new AcessoPorMinuto();
        acesso.setTarifa(0.5);

        // Testa a entrada dentro do horário permitido (entre 6h e 20h)
        try {
            acesso.setEntrada(LocalDate.now(), LocalTime.of(12, 0));
            assertNotNull(acesso.getEntrada());
        } catch (EstacionamentoFechadoException e) {
            fail("Não deveria lançar EstacionamentoFechadoException");
        }

        // Testa a entrada fora do horário permitido (deve lançar EstacionamentoFechadoException)
        try {
            acesso.setEntrada(LocalDate.now(), LocalTime.of(4, 0));
            fail("Deveria lançar EstacionamentoFechadoException");
        } catch (EstacionamentoFechadoException e) {
            // Esperado
        }
    }

    @Test
    public void testIntegracao() {
        // Teste de integração, verificando se o cálculo de valor e o setEntrada funcionam juntos.
        AcessoPorMinuto acesso = new AcessoPorMinuto();
        acesso.setTarifa(1.0);

        try {
            acesso.setEntrada(LocalDate.now(), LocalTime.of(12, 0));
        } catch (EstacionamentoFechadoException e) {
            fail("Não deveria lançar EstacionamentoFechadoException");
        }

        assertEquals(60.0, acesso.calculaValor(Duration.ofMinutes(60), acesso.getTarifa()), 0.0);
    }

    @Test
    public void testRegressao() {
        // Adicione casos de teste de regressão conforme necessário
        AcessoPorMinuto acesso = new AcessoPorMinuto();
        acesso.setTarifa(0.75);

        // Testa o cálculo para 45 minutos com tarifa de 0.75
        double resultado1 = acesso.calculaValor(Duration.ofMinutes(45), acesso.getTarifa());
        assertEquals(33.75, resultado1, 0.0);

        // Adicione mais casos de teste conforme necessário
    }
}