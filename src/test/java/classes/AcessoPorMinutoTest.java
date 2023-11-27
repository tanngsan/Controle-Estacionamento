package classes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import exceptions.EstacionamentoFechadoException;

public class AcessoPorMinutoTest {

    @Mock
    private AcessoPorMinuto acessoMock;

    @InjectMocks
    private AcessoPorMinuto acesso;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configuração inicial para cada teste: define a tarifa no mock.
        when(acessoMock.getTarifa()).thenReturn(0.5);
    }

    @Test
    public void testCalculaValor() {
        // Teste para verificar se o método calculaValor está calculando corretamente o valor com base na duração e tarifa.
        double resultado1 = acesso.calculaValor(Duration.ofMinutes(30), acessoMock.getTarifa());
        assertEquals(15.0, resultado1, 0.0);

        double resultado2 = acesso.calculaValor(Duration.ofMinutes(60), acessoMock.getTarifa());
        assertEquals(30.0, resultado2, 0.0);
    }

    @Test
    public void testSetEntrada() {
        // Teste para verificar se o método setEntrada está configurando corretamente a entrada dentro do horário permitido.
        try {
            when(acessoMock.getEntrada()).thenReturn(LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0)));
            acesso.setEntrada(LocalDate.now(), LocalTime.of(12, 0));
            assertNotNull(acesso.getEntrada());
        } catch (EstacionamentoFechadoException e) {
            fail("Não deveria lançar EstacionamentoFechadoException");
        }

        // Teste para verificar se o método setEntrada lança uma exceção quando a entrada é fora do horário permitido.
        try {
            acesso.setEntrada(LocalDate.now(), LocalTime.of(4, 0));
            fail("Deveria lançar EstacionamentoFechadoException");
        } catch (EstacionamentoFechadoException e) {
            // Comportamento esperado, o teste passa se a exceção for lançada.
        }
    }

    @Test
    public void testIntegracao() {
        // Teste de integração para verificar se os métodos setEntrada e calculaValor funcionam corretamente juntos.
        try {
            when(acessoMock.getEntrada()).thenReturn(LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0)));
            acesso.setEntrada(LocalDate.now(), LocalTime.of(12, 0));
        } catch (EstacionamentoFechadoException e) {
            fail("Não deveria lançar EstacionamentoFechadoException");
        }

        assertEquals(60.0, acesso.calculaValor(Duration.ofMinutes(60), acessoMock.getTarifa()), 0.0);
    }

    @Test
    public void testRegressao() {
        // Teste de regressão para verificar se alterações recentes no código não quebraram a funcionalidade existente.
        // Aqui, testamos o cálculo do valor com uma tarifa diferente.
        when(acessoMock.getTarifa()).thenReturn(0.75);
        double resultado1 = acesso.calculaValor(Duration.ofMinutes(45), acessoMock.getTarifa());
        assertEquals(33.75, resultado1, 0.0);
    }
}
