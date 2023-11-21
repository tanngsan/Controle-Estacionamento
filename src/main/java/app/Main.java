package app;

import classes.Acesso;
import classes.AcessoMensalista;
import classes.SistemaEstacionamento;
import classes.Proprietario;
import classes.Veiculo;
import classes.VeiculoMensalista;
import exceptions.DadosPessoaisIncompletosException;
import exceptions.DadosVeiculosIncompletosException;
import exceptions.EstacionamentoFechadoException;
import exceptions.PeriodoInvalidoException;

//import java.text.spi.NumberFormatProvider;
//import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
//import javax.swing.JOptionPane;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * <p>
 * Classe <b>Main</b> Recebe o método main que é o runner de toda a
 * aplicação</p>
 *
 * @author Lucas Ramon
 * @since may 2021
 * @version 1.0
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static SistemaEstacionamento sisEstacionamento = new SistemaEstacionamento();
    static Scanner teclado = new Scanner(System.in);
    
    public static void main(String[] args) {
        menu();
      
    
    }
    private static void menuCadastrarRotativo() {
        Veiculo veiculo = new Veiculo();
    
        try {
            System.out.println("Digite a marca do veículo:");
            String strMarca = teclado.nextLine();
            veiculo.setMarca(strMarca);
    
            System.out.println("Digite o modelo do veículo:");
            String strModelo = teclado.nextLine();
            veiculo.setModelo(strModelo);
    
            System.out.println("Digite a placa do veículo:");
            String strPlaca = teclado.nextLine();
            veiculo.setNumeroPlaca(strPlaca);
    
            if (sisEstacionamento.buscarVeiculo(strPlaca) == null) {
                sisEstacionamento.cadastrarVeiculo(veiculo);
            } else {
                System.out.println("Veículo já cadastrado!");
            }
        } catch (DadosVeiculosIncompletosException erroDadosVeiculosIncompleto) {
            System.out.println(erroDadosVeiculosIncompleto.getMessage());
            System.out.println("Veículo com dados incompletos");
        }
    }
    private static void menuCadastrarProprietario() {
        Proprietario p = new Proprietario();
        try {
            System.out.println("Digite o nome do proprietário:");
            String strNome = teclado.nextLine();
            p.setNome(strNome);

            System.out.println("Digite o número da CNH do proprietário:");
            String strCnh = teclado.nextLine();
            p.setCnh(strCnh);

            System.out.println("Digite o endereço do proprietário:");
            String strEndereco = teclado.nextLine();
            p.setEndereco(strEndereco);

            System.out.println("Digite o número de telefone celular do proprietário:");
            String strNcelular = teclado.nextLine();
            p.setnCelular(strNcelular);

            System.out.println("Digite o numero do telefone residencial do proprietário:");
            String strNresidencial = teclado.nextLine();
            p.setnResidencial(strNresidencial);
            System.out.println(strCnh);

            if (sisEstacionamento.buscarProprietario(strCnh) == null) {
            	sisEstacionamento.cadastrarProprietario(p);
            } else {
                System.out.println("Proprietário já cadastrado!");
            }

        } catch (DadosPessoaisIncompletosException erroDadosProprietarioIncompleto) {
            System.out.println(erroDadosProprietarioIncompleto.getMessage());
            System.out.println("Proprietário com dados pessoais incompletos");
        }
    }
    private static void menuCadastrarMensalista() {
    	

        /*
        Criando um novo registro de veiculo mensalista
        1-Instaciar um novo veiculo mensalista;
        2-Setar tudo referente ao veiculo;
        3-Se o veiculo já estiver registrado,lançar a exception veiculo já cadastrado;
        4-Adicionar veiculo a lista;
        5-Criar um objeto Proprietario;
        6-Setar tudo do proprietario e adicionar vincular o veiculo
        */
       
      
        try {

        /*
        -Instanciando o Veiculo com o método de substuição de Liskov;
        -Polimorfismo;
         */
        Veiculo vm = new VeiculoMensalista();

        System.out.println("Digite a marca do veículo:");
        String strMarcaMensalista = teclado.nextLine();
        vm.setMarca(strMarcaMensalista);

        System.out.println("Digite o modelo do veículo:");
        String strModeloMensalista = teclado.nextLine();
        vm.setModelo(strModeloMensalista);

        System.out.println("Digite a placa do veículo:");
        String strPlacaMensalista = teclado.nextLine();
        vm.setNumeroPlaca(strPlacaMensalista);
        
        System.out.println("Digite o número da CNH do proprietário:");
        String strCnhMensalista = teclado.nextLine();

        Proprietario proprietario = sisEstacionamento.buscarProprietario(strCnhMensalista);

        if (proprietario == null) {
            System.out.println("Proprietário não cadastrado!");
        }
        else {
            if (sisEstacionamento.buscarVeiculo(strPlacaMensalista) == null) {
                sisEstacionamento.cadastrarVeiculo(vm);
            } else {
                 System.out.println( "Veículo já cadastrado!");
            }
        }
        } catch (DadosVeiculosIncompletosException erroDadosVeiculosIncompleto) {
        	System.out.println(erroDadosVeiculosIncompleto.getMessage());
        	 System.out.println( "Veículo com dados incompletos");
        }
    }
    private static void menuAcessoRotativo() {
        /*
        1-Buscar a referencia de veiculo pela placa;
        2-Setar a hora e data de entrada do veiculo;
        3-Verificar o tipo de acesso;
        4-Realizar o cálculo do valor a ser pago;
        5-adicionar o acesso a lista de acessos;
        6-Adicionar o custo do acesso a lista de faturamento;
         */
        Veiculo comparacao = new Veiculo(); 
        System.out.println("Digite a placa do veículo:");
        String strPlacaRotativo = teclado.nextLine();
        Veiculo veiculoRotativo = sisEstacionamento.buscarVeiculo(strPlacaRotativo);
        if (veiculoRotativo == null) {
             System.out.println( "Veículo não cadastrado!");
        }
        else {
            if(veiculoRotativo.getClass()!= comparacao.getClass()){
                 System.out.println( "Não é possivel acessar como usuário rotativo: Veículo cadastrado como mensalista!");
            }
            else {
                try {
                    System.out.println( "Digite a data da entrada:\n(Use o formato DD/MM/AA)");
                    String strDataEntradaRotativo = teclado.nextLine();
                    int[] inputDataEntradaRotativo = GerenciamentoEstacionamento.lerData(strDataEntradaRotativo);
                    LocalDate dataEntradaRotativo = LocalDate.of(inputDataEntradaRotativo[2], inputDataEntradaRotativo[1], inputDataEntradaRotativo[0]);

                    System.out.println("Digite a hora da entrada:\n(Use o formato HH:MM)");
                    String strHoraEntradaRotativo = teclado.nextLine();
                    int[] inputHoraEntradaRotativo = GerenciamentoEstacionamento.lerHora(strHoraEntradaRotativo);
                    LocalTime horaEntradaRotativo = LocalTime.of(inputHoraEntradaRotativo[0], inputHoraEntradaRotativo[1]);

                    System.out.println("Digite a data da saída:\n(Use o formato DD/MM/AA)");
                    String strDataSaidaRotativo = teclado.nextLine();
                    int[] inputDataSaidaRotativo = GerenciamentoEstacionamento.lerData(strDataSaidaRotativo);
                    LocalDate dataSaidaRotativo = LocalDate.of(inputDataSaidaRotativo[2], inputDataSaidaRotativo[1], inputDataSaidaRotativo[0]);

                    System.out.println("Digite a hora da saída:\n(Use o formato HH:MM)");
                    String strHoraSaidaRotativo = teclado.nextLine();
                    int[] inputHoraSaidaRotativo = GerenciamentoEstacionamento.lerHora(strHoraSaidaRotativo);
                    LocalTime horaSaidaRotativo = LocalTime.of(inputHoraSaidaRotativo[0], inputHoraSaidaRotativo[1]);

                    LocalDateTime entradaRotativo = LocalDateTime.of(dataEntradaRotativo, horaEntradaRotativo);
                    LocalDateTime saidaRotativo = LocalDateTime.of(dataSaidaRotativo, horaSaidaRotativo);

                    Acesso acessoRotativo = GerenciamentoEstacionamento.classificaAcesso(entradaRotativo, saidaRotativo);
                    sisEstacionamento.cadastrarAcesso(acessoRotativo);
                    veiculoRotativo.setAcesso(acessoRotativo);
                } catch (NullPointerException nullPointerException) {
                    System.out.println( "ERROR!.");
                } catch (NumberFormatException numberFormatException) {
                    System.out.println( "Formato ilegal para data ou hora.");
                }
            }
        }
    }
    private static void menuAcessoMensalista() {
        /*
        1-Perguntar para o usuario qual é a placa do veiculo;
        2-Colocar em uma variável local o retorno da consulta feita;
        3-Instanciar um acesso de acordo com o tempo de permanênica 
        no estacionamento (acesso: minuto,por 15 minutos,por hora,por diaria,pernoite);
        4-Setar atributos do acesso;
        3-Colocar o acesso na referencia de acessos de veiculo;
         */
         /*
        1-O método buscar procura na lista de veículos a referência pela a placa,atribuindo como resultado
        da busca o objeto procurado haja vista que o retorno do metódo é capaz de retornar um veículo;
         */
        VeiculoMensalista veiculoMensalista = new VeiculoMensalista();
        System.out.println("Digite a placa do veículo:");
        String strPlaca =  teclado.nextLine();
        Veiculo vm = sisEstacionamento.buscarVeiculo(strPlaca);
        if (vm == null) {
             System.out.println( "Veículo não cadastrado!");
            return;
        }
        if (vm.getClass()!= veiculoMensalista.getClass()){

             System.out.println( "O veículo da placa número: "+ strPlaca+" não tem o plano de mensalista");

           return;

        }
        /*
        A instâcia "acesso" será utilizada para compor o acesso ao veículo;
         */
        Acesso acesso = new AcessoMensalista();

        /*
        O método lerData é utlizado para quebrar a String que é retornada pelo JOptionPane.showInputDialog;
        Ele basicamente retorna um vetor de inteiros de três posições(inputDataEntrada) que é utilizado para 
        setar a data no Objeto dataEntrada;
         */
        try {
            System.out.println( "Digite a data da entrada:\n(Use o formato DD/MM/AA)");
            String strDataEntrada = teclado.nextLine();
            int[] inputDataEntrada = GerenciamentoEstacionamento.lerData(strDataEntrada);
            LocalDate dataEntrada = LocalDate.of(inputDataEntrada[2], inputDataEntrada[1], inputDataEntrada[0]);

            /*
        O método lerHora é utilizado para quebrar a String que é retornada pelo JOptionPane.showInputDialog;
        Ele basicamente retorna um vetor de inteiros de duas posições(inputHoraEntrada) que é utlizado para
        setar a hora no Objeto horaEntrada;
             */
            System.out.println("Digite a hora da entrada:\n(Use o formato HH:MM)");
            String strHoraEntrada = teclado.nextLine();
            int[] inputHoraEntrada = GerenciamentoEstacionamento.lerHora(strHoraEntrada);
            LocalTime horaEntrada = LocalTime.of(inputHoraEntrada[0], inputHoraEntrada[1]);

            /*
        O método setEntrada utiliza uma data e uma hora para setar a entrada; 
        Esta data e hora foram obtidas pelo processos descritos acima.
             */
            System.out.println( "Digite a data da saída:\n(Use o formato DD/MM/AA)");
            String strDataSaida = teclado.nextLine();
            int[] inputDataSaida = GerenciamentoEstacionamento.lerData(strDataSaida);
            LocalDate dataSaida = LocalDate.of(inputDataSaida[2], inputDataSaida[1], inputDataSaida[0]);

            System.out.println("Digite a hora da saída:\n(Use o formato HH:MM)");
            String strHoraSaida = teclado.nextLine();
            int[] inputHoraSaida = GerenciamentoEstacionamento.lerHora(strHoraSaida);
            LocalTime horaSaida = LocalTime.of(inputHoraSaida[0], inputHoraSaida[1]);

            acesso.setEntrada(dataEntrada, horaEntrada);
            acesso.setSaida(dataSaida, horaSaida);
        } catch (PeriodoInvalidoException erroDePeriodoInvalido) {
            System.out.println(erroDePeriodoInvalido.getMessage());
            System.out.println( "Erro: Período Inválido.");
            return;
        } catch (EstacionamentoFechadoException erroDeEstacionamentoFechado) {
            System.out.println(erroDeEstacionamentoFechado.getMessage());
            System.out.println( "Estacionamento Fechado.");
        } catch (NullPointerException nullPointerException) {
            System.out.println( "ERROR.");
        } catch (NumberFormatException numberFormatException) {
            System.out.println( "Formato ilegal para data ou hora.");
        }

        /*
        O processo de setar a saída é exatamente igual ao de setar a entrada  
         */
        acesso.calculaDuracao();
        /*
        O método calculaDuracao é basicamente um setter especial. Ele utiliza os atributos entrada e saída
        para calcular a duração e setar no atributo duracao do objeto acesso
         */

        /*
        O calculaPeriodo é basciamente um setter especial. Ele utiliza os atributos entrada e saída para calcular
        
         */
        acesso.caculaPeriodo();

        vm.setAcesso(acesso);

        sisEstacionamento.cadastrarAcesso(acesso);
    }
    public static void menu() {
        int x;
        do {
            /*
            1-Fazer um menu textual para que o usuário possa escolher qual operação
            deseja realizar;
            2-Implementar os casos no switch case
             */

           System.out.println("Digite\n"
           + "1-Cadastrar veículo rotativo;\n"
                + "2-Cadastrar proprietário;\n"
                + "3-Cadastrar veículo mensalista;\n"
                + "4-Registrar acesso de usuário rotativo;\n"
                + "5-Registrar acesso de usuário mensalista;\n"
                + "6-Exibir veículos cadastrados;\n"
                + "7-Exibir proprietários;\n"
                + "8-Exibir listas de acessos;\n"
                + "9-Exibir faturamento.\n"
                + "0-Sair"
           );
            /*
              
              1-Ler a opção do usuário
             */
/*            *//*
              A string indisponivel será utilizada temporariamente para reportar 
              indisponibilidade de alguma funcionalidade;
             *//*
            String indisponivel = "Funcionalidade não implementada";*/

            try{
                /*System.out.println("escolha uma opção: ");
                strx = teclado.nextLine();*/
                x = Integer.parseInt(teclado.nextLine());

            }catch(NumberFormatException e){
                x = -1;
                System.out.println("Entrada invalida. Digite um numero. ");
            }

            switch (x) {
                case 1:
                	menuCadastrarRotativo();
                    break;
                case 2:
                	menuCadastrarProprietario();
                    break;
                case 3:
                	menuCadastrarMensalista();
                	break;
                case 4:
                	menuAcessoRotativo();
                    break;
                case 5:
                	menuAcessoMensalista();
                    break;
                case 6:
                    /* 1-Chamar o método listar do atributo veic1ulos
                         que é uma lista guarda objetos da classe Veiculo
                         por meio desta referênica;                        
                     */
                	sisEstacionamento.listarVeiculos();
                    break;
                case 7:

                    /* 1-Chamar o método listar do atributo proprietarios
                         que é uma lista guarda objetos da classe Proprietario
                         por meio desta referênica;                        
                     */
                	sisEstacionamento.listarProprietarios();
                    break;
                case 8:
                	sisEstacionamento.listarAcessos();
                    break;
                case 9:
                    exibeFaturamento();
                    break;
                case 0:
                    /*
                     1-Exibir mensagem de despedida amigável ao usuário;
                     */
                    System.out.println( "         UNB © GRUPO10  "
                            + "\n"
                            + "\nLucas- Paulo- Adrian- Arthur");
                    x = 0;
                    break;
                default:
                    System.out.println( "Opção Inválida! Tente novamente ou digite 0 para sair.");
                    teclado.nextInt();

                    
                }
                
        } while (x != 0);
            
        //Scanner.close();


    }
     

    public static void exibeFaturamento() {
        StringBuilder listaFaturamento = new StringBuilder("Faturamento:\n\n");
        double total = 0;

        Veiculo vmensalista = new VeiculoMensalista();
        listaFaturamento.append("Receita de mensalidades: \n\n");
        for (int i = 0; i < sisEstacionamento.getVeiculos().size(); i++) {
            if(sisEstacionamento.getVeiculos().get(i).getClass()== vmensalista.getClass()){
            	listaFaturamento.append("Mensalidade: ").append(i + 1)
                        .append("\nPlaca do veículo mensalista : ").append(sisEstacionamento.getVeiculos().get(i).getNumeroPlaca())
                        .append("\nValor da mensalidade: 500 R$\n\n");
            	total += 500;
            }
        }
        listaFaturamento.append("Receitas de acessos rotativos: \n");
        for (int i = 0; i < sisEstacionamento.getAcessos().size(); i++) {
            if (sisEstacionamento.getAcessos().get(i).getValor() != 0) {
                long hours = sisEstacionamento.getAcessos().get(i).getDuracao().toHours();
                long minutes = sisEstacionamento.getAcessos().get(i).getDuracao().toMinutes() % 60;
                listaFaturamento.append("Tempo de permanência: ").append(hours).append("h ")
                        .append(minutes).append("min")
                        .append("\n Valor: ").append(sisEstacionamento.getAcessos().get(i).getValor()).append(" R$");
                total += sisEstacionamento.getAcessos().get(i).getValor();
            }
        }

        listaFaturamento.append("\n\nFaturamento Total: ").append(total).append(" R$");
        System.out.println(listaFaturamento.toString());
    }
}


