
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.PeriodoInvalidoException;

public class PeriodoInvalidoExceptionTest {

    @Test
    public void testPeriodoInvalidoException() {
        try {
            throw new PeriodoInvalidoException();
        } catch (PeriodoInvalidoException e) {
            assertEquals("Exception in thread:com.grupo10.estacionamento.exceptions.PeriodoInvalidoException", e.getMessage());
        }
    }
}