package classes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class VeiculoMensalistaTest {

    @Test
    public void criarVeiculoMensalistaComProprietario() {
        Proprietario proprietario = new Proprietario();
        VeiculoMensalista veiculo = new VeiculoMensalista(proprietario);
        assertEquals(proprietario, veiculo.getProprietario());
    }

    @Test
    public void criarVeiculoMensalistaComProprietarioEDetalhes() {
        Proprietario proprietario = new Proprietario();
        VeiculoMensalista veiculo = new VeiculoMensalista(proprietario, "Marca", "Modelo", "Placa");
        assertEquals(proprietario, veiculo.getProprietario());
        assertEquals("Marca", veiculo.getMarca());
        assertEquals("Modelo", veiculo.getModelo());
        assertEquals("Placa", veiculo.getNumeroPlaca());
    }

    @Test
    public void criarVeiculoMensalistaComConstrutorPadrao() {
        VeiculoMensalista veiculo = new VeiculoMensalista();
        assertNull(veiculo.getProprietario());
    }

    @Test
    public void alterarProprietarioVeiculoMensalista() {
        Proprietario proprietario1 = new Proprietario();
        Proprietario proprietario2 = new Proprietario();
        VeiculoMensalista veiculo = new VeiculoMensalista(proprietario1);
        veiculo.setProprietario(proprietario2);
        assertEquals(proprietario2, veiculo.getProprietario());
    }
}
