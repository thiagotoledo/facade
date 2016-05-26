/**
 * 
 * Classe de Facade
 * Do pacote de exemplo SistemaCaixaEletronico 
 * ( Pacote criado para realizar as operações "físicas" em um caixa eletrônico bancário de nosso exemplo ) 
 * Padrão de Projeto GOF - Facade
 * 
 * @author Thiago Toledo <javaephp@gmail.com>
 * 
 */
package SistemaCaixaEletronico;


public class FacadeCaixaEletronico {

    // Propriedade privada que guarda a mensagem de erro
    // Ou seja, uma segunda forma de tratamento de erros bem comum em implementações
    // Em códigos por aí.
    private String msgErro = "";
    
    //Método principal de nossa Facade
    public boolean sacarCedulaMaquina(double valorOperacao, String codigoContaCliente, String codigoCaixaEletronico){
    
        AdministracaoCedulas AdminCedulas = new AdministracaoCedulas();
        AdministraComprovante AdminComprovante = new AdministraComprovante();
    
        //Tratando erros com return false e guardando informações dentro de nossa propriedade de mensagem de erro
        if(AdminCedulas.checaCedulasMaquina(valorOperacao, codigoContaCliente, codigoCaixaEletronico) == false){
            msgErro = "Erro: A maquina está sem cédulas para realização no momento. Operação cancelada.";
            return false;
        }else if(AdminCedulas.entregaCedulas(valorOperacao, codigoContaCliente, codigoCaixaEletronico) == false){
            msgErro = "Erro: O dispensor de cédulas está com problemas na máquina. Operação cancelada.";
            return false;
            
        //Nos últimos else if importante ressaltar que mesmo retornando true, a mensagem de erro é gravada para ser mostrada
        //Em nosso aplicativo cliente
        }else if(AdminComprovante.checaPapelMaquina(valorOperacao, codigoContaCliente, codigoCaixaEletronico) == false){
            msgErro = "Erro: O comprovante não pode ser impresso, pois acabou o papel da máquina.";
            //A falta do comprovante não impede que a transação seja terminada
            return true;
        }else if(AdminComprovante.imprimeComprovante(valorOperacao, codigoContaCliente, codigoCaixaEletronico)){
            msgErro = "Erro: O comprovante não pode ser impresso, por problemas no papel da máquina.";
            //A falta do comprovante não impede que a transação seja terminada
            return true;
        }
        
        return true;
        
    }
    
    //Metodo Padrão de retorno de valor de propriedade privada
    public String getMsgErro(){
        return msgErro; 
    }
    
}
