/**
 * 
 * Classe, implementação de Aplicação Cliente do Tipo Console Java
 * Do pacote de exemplo OperacaoBancaria 
 * ( Pacote da aplicação cliente criada para testar os demais pacotes criando uma interface simples de entrada de dados ) 
 * Padrão de Projeto GOF - Facade
 * 
 * @author Thiago Toledo <javaephp@gmail.com>
 * 
 */

package aplicacaoCliente;

//Importando os packages que utilizaresmos + classes de Scaneamento de dados digitados pelo cliente no console
import SistemaTransacoesBancarias.FacadeTransacoesBancarias;
import SistemaTransacoesBancarias.FacadeTransacoesBancariasException;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class OperacaoBancaria {

    /**
     * Método Main da Aplicação ( Primeiro a ser executado )
     */
    public static void main(String[] args) {
        
        //Saidas de texto de Boas vindas do programa :)
        System.out.println("Seja bem vindo a aplicação de teste do Pacote de Classes de Operacoes Bancarias.");
        System.out.println("Essa aplicação é um exemplo fictício de utilização do Design Pattern Estrutural Facade.");
        System.out.println("**************************************************************************************");
        
        //Chamada do método que continua a execução da interface
        sacarDinheiro();
        
    }
    
    public static void sacarDinheiro(){
    
        // Classe responsável por receber os dados inseridos no console
        BufferedReader input = new BufferedReader( new InputStreamReader(System.in));
        String valorDigitado;
        String opcaoSaque;
        
        //Tratamento de entrada de dados a nível de Aplicação Cliente
        //Utilizando o Try para controlar Exceções em tempo de execução de maneira amigável
        
        try{
        
            //Rcebendo valor
            System.out.println("Digite o valor desejado que deseja sacar (ex: 101,22):");
        
            //Scaneando a interação do usuário e transformando para ser convertido em decimal
            valorDigitado = input.readLine().toLowerCase();
            String valorDigitadoFormatado = valorDigitado.replaceAll(",",".");
            Double valorDigitadoDecimal = Double.parseDouble(valorDigitadoFormatado);
            
            //Scaneando a interação do usuário
            System.out.println("Digite o número da conta do cliente:");
            valorDigitado = input.readLine().toLowerCase();
           
            //Scaneando a interação do usuário
            System.out.println("Se desejar sacar o dinheiro no caixa eletrônico digite o código do caixa abaixo. Caso contrário deixe em branco e dê enter para prosseguir:");
            opcaoSaque = input.readLine().toLowerCase();
            
            //Checando se o usuário optou por sacar somente virtualmente o valor, ou se essa operação
            //Envolverá um caixa eletrônico físico
            
            //Testando de maneira segura no java se a entrada foi vazia
            if(opcaoSaque.contentEquals("")){ 
                //Seguindo a execução para saques virtuais
                System.out.println("Opção de sacar dinheiro sem caixa escolhida.");
                FacadeTransacoesBancarias facade = new FacadeTransacoesBancarias();
                facade.sacarDinheiroConta(valorDigitadoDecimal, valorDigitado);
                System.out.println("Transação realizada com sucesso!");
            }else{
                System.out.println("Opção de sacar dinheiro com caixa escolhida.");
                FacadeTransacoesBancarias facade = new FacadeTransacoesBancarias();
                // Coisa linda esta sobrecarga do método com opcaoSaque para passar o codigo do caixa :) :)
                facade.sacarDinheiroConta(valorDigitadoDecimal, valorDigitado,opcaoSaque); 
                System.out.println("Transação realizada com sucesso no caixa!");
            }
            
            
        // Tratando erro gerado pela entrada de caracteres invalidos no valor que deve ser decimal    
        }catch (java.lang.NumberFormatException e) {
            
            System.out.println("Erro: Por gentileza verifique se o valor digitado está no formato correto e reinicie a operação");
            sacarDinheiro();
        
        // Tratando as exceptions personalizadas da classe de Facade ( Profissa )    
        }catch (FacadeTransacoesBancariasException e) {    
            
            System.out.println("Erro durante a transação. Motivo:");
            System.out.println(e.getMessage());
            
            sacarDinheiro();
        
        // Tratando erros não esperados na aplicação    
        }catch (Exception e){
           
            System.out.println("Erro: ocorreu um erro inesperado. Por favor contacte nossa área de suporte com a seguinte mensagem caso o erro continue acontecendo: ");
            System.out.println(e);
            
            
            sacarDinheiro();
            
        }
        
    } 
    
}
