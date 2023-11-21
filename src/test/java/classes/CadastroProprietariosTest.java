package classes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import exceptions.DadosPessoaisIncompletosException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class CadastroProprietariosTest {

    private CadastroProprietarios cadastroProprietarios;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        cadastroProprietarios = new CadastroProprietarios();
        System.setOut(new PrintStream(outContent)); // Redireciona a saída do console
    }

    private Proprietario criarProprietario(String nome, String cnh, String nCelular, String nResidencial, String endereco) throws DadosPessoaisIncompletosException {
        Proprietario proprietario = new Proprietario();
        proprietario.setNome(nome);
        proprietario.setCnh(cnh);
        proprietario.setnCelular(nCelular);
        proprietario.setnResidencial(nResidencial);
        proprietario.setEndereco(endereco);
        return proprietario;
    }

    @Test
    public void cadastrar_DeveAdicionarProprietario() throws DadosPessoaisIncompletosException {
        Proprietario proprietario = criarProprietario("Nome", "CNH", "nCelular", "nResidencial", "Endereco");
        cadastroProprietarios.cadastrar(proprietario);

        List<Proprietario> proprietarios = cadastroProprietarios.getProprietarios();
        assertTrue(proprietarios.contains(proprietario), "O proprietário deve estar na lista após ser cadastrado");
    }

    @Test
    public void remover_DeveRemoverProprietario() throws DadosPessoaisIncompletosException {
        Proprietario proprietario = criarProprietario("Nome", "CNH", "nCelular", "nResidencial", "Endereco");
        cadastroProprietarios.cadastrar(proprietario);

        int index = cadastroProprietarios.getProprietarios().indexOf(proprietario);
        cadastroProprietarios.remover(index);

        List<Proprietario> proprietarios = cadastroProprietarios.getProprietarios();
        assertFalse(proprietarios.contains(proprietario), "O proprietário não deve estar na lista após ser removido");
    }

    @Test
    public void buscar_DeveRetornarProprietarioCorreto() throws DadosPessoaisIncompletosException {
        Proprietario proprietario = criarProprietario("Nome", "CNH123", "nCelular", "nResidencial", "Endereco");
        cadastroProprietarios.cadastrar(proprietario);

        Proprietario encontrado = cadastroProprietarios.buscar("CNH123");
        assertEquals(proprietario, encontrado, "O proprietário buscado deve ser o mesmo que foi cadastrado");
    }

    @Test
    public void buscar_DeveRetornarNullParaCnhInexistente() {
        Proprietario encontrado = cadastroProprietarios.buscar("CNHInexistente");
        assertNull(encontrado, "Deve retornar null para uma CNH inexistente");
    }

    @Test
    public void listar_DeveListarProprietarios() throws DadosPessoaisIncompletosException {
        Proprietario proprietario1 = criarProprietario("Nome1", "CNH1", "nCelular1", "nResidencial1", "Endereco1");
        Proprietario proprietario2 = criarProprietario("Nome2", "CNH2", "nCelular2", "nResidencial2", "Endereco2");
        cadastroProprietarios.cadastrar(proprietario1);
        cadastroProprietarios.cadastrar(proprietario2);

        cadastroProprietarios.listar();

        String output = outContent.toString();
        assertTrue(output.contains("Nome1") && output.contains("CNH1"), "A lista deve conter informações do proprietario1");
        assertTrue(output.contains("Nome2") && output.contains("CNH2"), "A lista deve conter informações do proprietario2");
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut); // Restaura a saída do console
    }
}
