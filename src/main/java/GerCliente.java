//Kevin Luiz Botelho Lima - RA: 2266423

import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GerCliente{
    
    @SuppressWarnings("unused")
    private Cliente cliente; 
    private List<Cliente> bdCliente; 
    
    private static GerCliente gerClienteUnic; //1º passo singleton
    
    private GerCliente(){ //2º passo singleton
        cliente = new Cliente();
        bdCliente = new ArrayList<Cliente>(); 
    }
    
    public static GerCliente geraGerCliente(){//3º passo singleton
        
            if(gerClienteUnic == null){
                gerClienteUnic = new GerCliente();
            }
        
            return gerClienteUnic;
    }
    
    public List<Cliente> getBdCliente(){
            return bdCliente;
    }
	
	public Cliente consClienteCpf(Cliente cliente){
		for(int i = 0; i < bdCliente.size(); i++){
			if(cliente.getCpf() == bdCliente.get(i).getCpf()){
				return bdCliente.get(i);
			}
		}
                
		return null;
	}//fim consClienteCpf	
        
	public Cliente insCliente(Cliente cliente){
 
		if(consClienteCpf(cliente) == null){
                    
			bdCliente.add(cliente);
                        RelClientesCadastrados.geraRelClientesCadastrados().lisTab();
			return cliente;
		}
		else{
			return null;
		}
	}//fim insCliente

	public Cliente delClienteCpf(Cliente cliente){
            Cliente cliente1 = consClienteCpf(cliente);
            if(cliente1 != null){
                bdCliente.remove(cliente1);
                RelClientesCadastrados.geraRelClientesCadastrados().lisTab();
                return null;
            }
            else{
                return cliente;
            }
	}//fim removeClienteCpf

	public Cliente atualizaClienteCpf(Cliente cliente){ //Não atualiza o CPF, apenas os demais campos
		for(int i = 0; i < bdCliente.size(); i++){
			if(cliente.getCpf() == bdCliente.get(i).getCpf()){
                            
                            /*cliente = bdCliente.get(i);

                            String nome = JOptionPane.showInputDialog(
                                    null,
                                    "Informe o novo NOME",
                                    "Atulizar dados",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                            cliente.setNome(nome);
                            */
                            
                            bdCliente.set(i, cliente); 
                            RelClientesCadastrados.geraRelClientesCadastrados().lisTab();
                            return bdCliente.get(i);
			}
		}
                return null;
	}//fim atualizaClienteCpf
        
        public void atualizaSaldos(){
            //Atualiza o valor de compra de ações da petrobrás
            FormPrincipal.acoesPetrobras.atualizaValor();
            
            for(int i = 0; i < bdCliente.size(); i++){
                
                //Verifica se existe conta poupança cadastrada
                //Caso possua, atualiza o saldo desta conta utilizando o atributo que armazena a taxa de rendimento mensal
                if(bdCliente.get(i).getContaPoupanca() != null){
                    bdCliente.get(i).getContaPoupanca().rendimentoMensal();
                    bdCliente.get(i).getContaPoupanca().resetQuantSaques();
                }

                //Verifica se existe conta de investimento cadastrada
                //Caso possua, atualiza o saldo desta conta utilizando o atributo que armazena a taxa administrativa
                if(bdCliente.get(i).getContaInvestimento() != null){
                    bdCliente.get(i).getContaInvestimento().atualizaSaldo();
                }
            }
        }
}//fim da classe