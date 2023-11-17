package app;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import app.GerenciamentoEstacionamento;

import java.time.LocalDateTime;
import classes.Acesso;
import classes.Acesso;
import classes.AcessoPorHora;
import exceptions.EstacionamentoFechadoException;
import exceptions.PeriodoInvalidoException;

public class GerenciamentoEstacionamentoTest {

    @Test
    public void testLerHora() {
        int[] expected = {10, 30};
        int[] result = GerenciamentoEstacionamento.lerHora("10:30");
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testLerData() {
        int[] expected = {15, 8, 2022};
        int[] result = GerenciamentoEstacionamento.lerData("15/08/2022");
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testClassificaAcesso() {
        try{
        LocalDateTime entrada = LocalDateTime.of(2022, 8, 15, 10, 0);
        LocalDateTime saida = LocalDateTime.of(2022, 8, 15, 11, 0);

        Acesso expected = new AcessoPorHora();
        expected.setEntrada(entrada.toLocalDate(), entrada.toLocalTime());
        expected.setSaida(saida.toLocalDate(), saida.toLocalTime());
        expected.setDuracao(expected.calculaDuracao());
        expected.caculaPeriodo();
        expected.setValor(expected.calculaValor(expected.getDuracao(), 0.5));

        Acesso result = GerenciamentoEstacionamento.classificaAcesso(entrada, saida);

        Assertions.assertEquals(expected.getClass(), result.getClass());
        Assertions.assertEquals(expected.getEntrada(), result.getEntrada());
        Assertions.assertEquals(expected.getSaida(), result.getSaida());
        Assertions.assertEquals(expected.getDuracao(), result.getDuracao());
        Assertions.assertEquals(expected.getPeriodo(), result.getPeriodo());
        Assertions.assertEquals(expected.getValor(), result.getValor());
    
    } catch (EstacionamentoFechadoException | PeriodoInvalidoException e) {
        e.printStackTrace();
        Assertions.fail("Exceção lançada durante o teste: " + e.getMessage());
    }
}
}