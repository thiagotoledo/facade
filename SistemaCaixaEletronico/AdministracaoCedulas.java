/**
 * Classe
 * Do pacote de exemplo SistemaCaixaEletronico 
 * ( Pacote criado para realizar as operações "virtuais" bancárias de nosso exemplo ) 
 * Padrão de Projeto GOF - Facade
 * 
 * @author Thiago Toledo <javaephp@gmail.com>
 * 
 */
package SistemaCaixaEletronico;

//Importando o Random para deixar nossos testes mais interessantes e imprevisíveis :)
import java.util.Random;

public class AdministracaoCedulas {
    
    //Apesar de não estarmos realmente usando os dados passados por parâmetro
    //Eles exemplificam dados reais passados a este tipo de implementação
    public boolean checaCedulasMaquina(double valorOperacao, String codigoContaCliente, String codigoCaixaEletronico){
    
        Random gerador = new Random();
        int numeroRandomico = gerador.nextInt() ;
        
        return numeroRandomico % 2 == 0;
        
    }
    
    //Apesar de não estarmos realmente usando os dados passados por parâmetro
    //Eles exemplificam dados reais passados a este tipo de implementação
    public boolean entregaCedulas(double valorOperacao, String codigoContaCliente, String codigoCaixaEletronico){
    
        //Cria um evento randomico para aprovar ou não o crédito da operação, apenas para fins de exemplo, 
        //Pois em uma aplicação real, se fariam operações de banco de dados para avaliar isso, por exemplo.
        
        Random gerador = new Random();
        int numeroRandomico = gerador.nextInt() ;
        
        return numeroRandomico % 2 == 0;
        
    }
    
}
