/**
 * 
 * Classe maravilhosa de Exception personalizada para nosso Facade
 * Implementando a definição do Facade inclusive para erros mais amigáveis e profissionais centralizados em nossa Facade
 * Lindo né?
 * 
 * Do pacote de exemplo SistemaTransacoesBancarias 
 * ( Pacote criado para realizar as operações "virtuais" bancárias de nosso exemplo ) 
 * Padrão de Projeto GOF - Facade
 * 
 * @author Thiago Toledo <javaephp@gmail.com>
 * 
 */
package SistemaTransacoesBancarias;

//Peguei leve e deixei em modo unchecked, não forçando você a usar um Try em métodos que a utilizam :P
public class FacadeTransacoesBancariasException extends RuntimeException {  
  
  FacadeTransacoesBancariasException(String message) {
    super(message);
  }
  
}
