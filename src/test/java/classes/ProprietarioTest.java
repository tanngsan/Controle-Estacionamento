package classes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import exceptions.DadosPessoaisIncompletosException;

public class ProprietarioTest {

    @Test
    public void criarProprietarioComDadosValidos() throws DadosPessoaisIncompletosException {
        Proprietario proprietario = new Proprietario();
        proprietario.setNome("João Silva");
        proprietario.setEndereco("Rua Exemplo, 123");
        proprietario.setnCelular("11999999999");
        proprietario.setnResidencial("1133333333");
        proprietario.setCnh("12345678901");

        assertEquals("João Silva", proprietario.getNome());
        assertEquals("Rua Exemplo, 123", proprietario.getEndereco());
        assertEquals("11999999999", proprietario.getnCelular());
        assertEquals("1133333333", proprietario.getnResidencial());
        assertEquals("12345678901", proprietario.getCnh());
    }

    @Test
    public void criarProprietarioComNomeVazioDeveLancarExcecao() {
        Proprietario proprietario = new Proprietario();
        assertThrows(DadosPessoaisIncompletosException.class, () -> {
            proprietario.setNome("");
        });
    }

    @Test
    public void criarProprietarioComEnderecoVazioDeveLancarExcecao() {
        Proprietario proprietario = new Proprietario();
        assertThrows(DadosPessoaisIncompletosException.class, () -> {
            proprietario.setEndereco("");
        });
    }

    @Test
    public void criarProprietarioComCelularVazioDeveLancarExcecao() {
        Proprietario proprietario = new Proprietario();
        assertThrows(DadosPessoaisIncompletosException.class, () -> {
            proprietario.setnCelular("");
        });
    }

    @Test
    public void criarProprietarioComCnhVazioDeveLancarExcecao() {
        Proprietario proprietario = new Proprietario();
        assertThrows(DadosPessoaisIncompletosException.class, () -> {
            proprietario.setCnh("");
        });
    }

    @Test
    public void criarProprietarioComTelefoneResidencialVazioDeveAtribuirValorPadrao() throws DadosPessoaisIncompletosException {
        Proprietario proprietario = new Proprietario();
        proprietario.setNome("João Silva");
        proprietario.setEndereco("Rua Exemplo, 123");
        proprietario.setnCelular("11999999999");
        proprietario.setnResidencial("");
        proprietario.setCnh("12345678901");

        assertEquals("**Não cadastrado**", proprietario.getnResidencial());
    }

    // Outros testes conforme necessário para cobrir todos os cenários
}
