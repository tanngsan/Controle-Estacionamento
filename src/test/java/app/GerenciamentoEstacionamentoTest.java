package app;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import app.GerenciamentoEstacionamento;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import classes.Acesso;
import classes.Acesso;
import classes.AcessoPorHora;
import classes.AcessoPorMinuto;
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

    @Test
    public void testClassificaAcessoPorMinuto() {
    // Define um horário de entrada e saída com 10 minutos de diferença.
    LocalDateTime entrada = LocalDateTime.of(2022, 8, 15, 10, 0);
    LocalDateTime saida = LocalDateTime.of(2022, 8, 15, 10, 10); // 10 minutos depois

    // Chama o método classificaAcesso da classe GerenciamentoEstacionamento
    // passando os horários de entrada e saída definidos anteriormente.
    Acesso resultado = GerenciamentoEstacionamento.classificaAcesso(entrada, saida);

    // Verifica se o objeto retornado é uma instância da classe AcessoPorMinuto.
    // Isso é importante para assegurar que o método classificaAcesso está
    // identificando corretamente o tipo de acesso com base na duração.
    assertTrue(resultado instanceof AcessoPorMinuto);
}
}