package classes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class CadastroVeiculosTest {

    private CadastroVeiculos cadastroVeiculos;

    @BeforeEach
    public void setUp() {
        cadastroVeiculos = new CadastroVeiculos();
    }

    @Test
    public void testCadastrar() {
        Veiculo veiculo = new Veiculo("ABC123", "Toyota", "Corolla");
        cadastroVeiculos.cadastrar(veiculo);

        List<Veiculo> veiculosList = cadastroVeiculos.getVeiculos();
        assertEquals(1, veiculosList.size());
        assertEquals(veiculo, veiculosList.get(0));
    }

    @Test
    public void testRemover() {
        Veiculo veiculo = new Veiculo("ABC123", "Toyota", "Corolla");
        cadastroVeiculos.cadastrar(veiculo);

        cadastroVeiculos.remover(0);
        List<Veiculo> veiculosList = cadastroVeiculos.getVeiculos();
        assertEquals(0, veiculosList.size());
    }

    @Test
    public void testBuscar() {
        Veiculo veiculo = new Veiculo("ABC123", "Toyota", "Corolla");
        cadastroVeiculos.cadastrar(veiculo);

        Veiculo encontrado = cadastroVeiculos.buscar("ABC123");
        assertEquals(veiculo, encontrado);

        Veiculo naoEncontrado = cadastroVeiculos.buscar("XYZ789");
        assertNull(naoEncontrado);
    }

    @Test
    public void testListar() {
        Veiculo veiculo1 = new Veiculo("ABC123", "Toyota", "Corolla");
        Veiculo veiculo2 = new Veiculo("XYZ789", "Honda", "Civic");

        cadastroVeiculos.cadastrar(veiculo1);
        cadastroVeiculos.cadastrar(veiculo2);

        // Captura a saída do console
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOut = System.out;
        System.setOut(printStream);

        try {
            cadastroVeiculos.listar();
            printStream.flush();

            // Obtém a saída do console
            String consoleOutput = outputStream.toString();

            // Verifica se a saída contém as informações esperadas
            assertTrue(consoleOutput.contains("Veiculo 0:"));
            assertTrue(consoleOutput.contains("Veiculo 1:"));
            assertTrue(consoleOutput.contains("Marca: Toyota"));
            assertTrue(consoleOutput.contains("Marca: Honda"));
            assertTrue(consoleOutput.contains("Modelo: Corolla"));
            assertTrue(consoleOutput.contains("Modelo: Civic"));
            assertTrue(consoleOutput.contains("Placa: ABC123"));
            assertTrue(consoleOutput.contains("Placa: XYZ789"));
        } finally {
            System.setOut(originalOut);
        }
    }
}