import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.DadosVeiculosIncompletosException;

public class DadosVeiculosIncompletosExceptionTest {

    @Test
    public void testExceptionMessage() {
        DadosVeiculosIncompletosException exception = new DadosVeiculosIncompletosException();
        assertEquals("Exception in thread:com.grupo10.estacionamento.exceptions.DadosVeiculosIncompletosException",
                exception.getMessage());
    }

    @Test
    public void testExceptionClass() {
        DadosVeiculosIncompletosException exception = new DadosVeiculosIncompletosException();
        assertEquals(DadosVeiculosIncompletosException.class, exception.getClass());
    }

    @Test
    public void testExceptionInheritance() {
        DadosVeiculosIncompletosException exception = new DadosVeiculosIncompletosException();
        assertEquals(Exception.class, exception.getClass().getSuperclass());
    }
}