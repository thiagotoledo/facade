/**
 * 
 * Classe de Facade
 * Do pacote de exemplo SistemaTransacoesBancarias 
 * ( Pacote criado para realizar as operações "virtuais" bancárias de nosso exemplo ) 
 * Padrão de Projeto GOF - Facade
 * 
 * @author Thiago Toledo <javaephp@gmail.com>
 * 
 */
package SistemaTransacoesBancarias;

//Importa o pacote de caixa eletrônico para caso precise utilizá-lo
import SistemaCaixaEletronico.FacadeCaixaEletronico;


public class FacadeTransacoesBancarias {
    
    //Aqui a brincadeira começa :P
    //implementacao do método principal de nossa Facade
    //Perceba que coisa linda -> Apenas dois valores sendo passados por parâmetro
    public void sacarDinheiroConta(double valorOperacao, String codigoContaCliente){
        
        //Método com a operação "virtual" de nossa funcionalidade de sacar dinheiro
        //Perceba o true do final, que indica que o método de movimentação de conta será realmente utilizado, pois a operação acaba por aqui
        SacarDinheiroContaVirtual(valorOperacao, codigoContaCliente, true);
        
    }
    
    //Ué? Mesmo método? Olha a coisa linda da Sobrecarga ai gente!! ( passando três valores passados por parâmetro )
    //Adicionado mais um valor relativo ao código do Caixa utilizado
    public void sacarDinheiroConta(double valorOperacao, String codigoContaCliente, String codigoCaixaEletronico){
        
        //Método com a operação "virtual" de nossa funcionalidade de sacar dinheiro
        //Perceba o false do final, que indica que o método de movimentação de conta será realmente utilizado, pois a operação não acaba por aqui
        SacarDinheiroContaVirtual(valorOperacao, codigoContaCliente, false);
        
        FacadeCaixaEletronico FacadeCaixa = new FacadeCaixaEletronico();
        
        //Checando se algum dos métodos dá erro e gerando uma excessão caso isso ocorra
        if(FacadeCaixa.sacarCedulaMaquina(valorOperacao, codigoContaCliente, codigoCaixaEletronico) == false){
            throw new FacadeTransacoesBancariasException("Transação cancelada. " + FacadeCaixa.getMsgErro());
        }else if(!FacadeCaixa.getMsgErro().contentEquals("")){
            //Tratamento especial para quando o método gerar um erro parcial, completando a operação de movimentação de dinheiro
            throw new FacadeTransacoesBancariasException("Transação completa com ressalvas: " + FacadeCaixa.getMsgErro());
        }
        
    }
    
    private void SacarDinheiroContaVirtual(double valorOperacao, String codigoContaCliente, Boolean realizaMovimento){
        
        //Declarando os recursos necessários
        MovimentaConta movimentaConta = new MovimentaConta();
        AvaliacaoSeguranca avaliaSeguranca = new AvaliacaoSeguranca(); 
        AvaliacaoDeCredito avaliaCredito = new AvaliacaoDeCredito();
        
        
        //Tratando com exceptions personalizadas cada tentativa sem sucesso
        if(avaliaCredito.possuiCreditoSuficiente(valorOperacao, codigoContaCliente) == false){
            throw new FacadeTransacoesBancariasException("Transação cancelada. Credito insuficiente para realizar a operação.");
        }else if(avaliaSeguranca.checaSegurancaOperacao(valorOperacao, codigoContaCliente) == false){
            throw new FacadeTransacoesBancariasException("Transação cancelada. Essa transação foi classificada como insegura e por isso não será realizada.");
        
        // Só executa se realizaMovimento estiver como true, evitando que a movimentação seja feita neste ponto, quando não desejada    
        }else if(realizaMovimento == true && movimentaConta.movimentarConta(valorOperacao, codigoContaCliente) == false){
            throw new FacadeTransacoesBancariasException("Transação cancelada. Não foi possível realizar a movimentação bancária. Tente mais tarde");
        }
        
    }
    
}