package classes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class CadastroAcessosTest {

    private CadastroAcessos cadastroAcessos;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /**
     * Configuração inicial para cada teste.
     * Inicializa o cadastro de acessos e redireciona a saída do console.
     */
    @BeforeEach
    public void setUp() {
        cadastroAcessos = new CadastroAcessos();
        System.setOut(new PrintStream(outContent)); // Redireciona a saída do console
    }

    /**
     * Testa se o método cadastrar adiciona um acesso à lista de acessos.
     */
    @Test
    public void cadastrar_DeveAdicionarAcesso() {
        Acesso acesso = new AcessoMensalista(); // Substitua pelo tipo de Acesso apropriado
        cadastroAcessos.cadastrar(acesso);

        List<Acesso> acessos = cadastroAcessos.getAcessos();
        assertTrue(acessos.contains(acesso), "O acesso deve estar na lista após ser cadastrado");
    }

    /**
     * Testa se o método remover exclui um acesso da lista de acessos.
     */
    @Test
    public void remover_DeveRemoverAcesso() {
        Acesso acesso = new AcessoMensalista(); // Substitua pelo tipo de Acesso apropriado
        cadastroAcessos.cadastrar(acesso);

        int index = cadastroAcessos.getAcessos().indexOf(acesso);
        cadastroAcessos.remover(index);
        List<Acesso> acessos = cadastroAcessos.getAcessos();
        assertFalse(acessos.contains(acesso), "O acesso não deve estar na lista após ser removido");
    }

    /**
     * Testa se o método buscar retorna o acesso correto com base no ID fornecido.
     */
    @Test
    public void buscar_DeveRetornarAcessoCorreto() {
        Acesso acesso = new AcessoMensalista(); // Substitua pelo tipo de Acesso apropriado
        cadastroAcessos.cadastrar(acesso);
    
        Acesso encontrado = cadastroAcessos.buscar(String.valueOf(acesso.getId()));
        assertEquals(acesso, encontrado, "O acesso buscado deve ser o mesmo que foi cadastrado");
    }    

    /**
     * Testa se o método buscar lança uma exceção ao tentar encontrar um acesso com um ID inexistente.
     */
    @Test
    public void buscar_DeveLancarExcecaoParaIdInexistente() {
        assertThrows(UnsupportedOperationException.class, () -> cadastroAcessos.buscar("idInexistente"),
                     "Deve lançar uma exceção ao tentar buscar um acesso com ID inexistente");
    }

    /**
     * Testa se o método listar exibe corretamente os acessos cadastrados.
     * Verifica se a saída do console contém as informações dos acessos adicionados.
     */
    @Test
    public void listar_DeveListarAcessos() {
        // Adiciona alguns acessos para teste
        Acesso acesso1 = new AcessoMensalista(); // Substitua pelo tipo de Acesso apropriado
        Acesso acesso2 = new AcessoMensalista(); // Substitua pelo tipo de Acesso apropriado
        cadastroAcessos.cadastrar(acesso1);
        cadastroAcessos.cadastrar(acesso2);

        cadastroAcessos.listar();

        // Verifica se a saída contém informações dos acessos
        String output = outContent.toString();
        assertTrue(output.contains(acesso1.toString()), "A lista deve conter informações do acesso1");
        assertTrue(output.contains(acesso2.toString()), "A lista deve conter informações do acesso2");
    }

    /**
     * Restaura a saída do console para o padrão após cada teste.
     */
    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut); // Restaura a saída do console
    }
}
