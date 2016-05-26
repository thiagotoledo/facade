 /**
 * Classe
 * Do pacote de exemplo SistemaTransacoesBancarias 
 * ( Pacote criado para realizar as operações "virtuais" bancárias de nosso exemplo ) 
 * Padrão de Projeto GOF - Facade
 * 
 * @author Thiago Toledo <javaephp@gmail.com>
 * 
 */
package SistemaTransacoesBancarias;

//Importando o Random para deixar nossos testes mais interessantes e imprevisíveis :)
import java.util.Random;


public class AvaliacaoSeguranca {
    
    //Apesar de não estarmos realmente usando os dados passados por parâmetro
    //Eles exemplificam dados reais passados a este tipo de implementação
    public boolean checaSegurancaOperacao(double valorOperacao, String codigoContaCliente){
    
        //Cria um evento randomico para aprovar ou não a operação, apenas para fins de exemplo, 
        //Pois em uma aplicação real, se fariam operações de banco de dados para avaliar isso, por exemplo.
        
        Random gerador = new Random();
        int numeroRandomico = gerador.nextInt() ;
        
        return numeroRandomico % 2 == 0;
    
    }
    
    
}
