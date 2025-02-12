//Kevin Luiz Botelho Lima - RA: 2266423

//Uma conta do tipo investimento, na vida real, pode se referir a vários
//tipos de investimento, como: ações, fundos de investimento, derivativos, etc.
//Para a elaboração deste sistema, será considerado o comportamento de um investimento em ações.

//Existem vários tipos de ações, porém, de forma simplista, tem-se lucro neste tipo de investimento de duas maneiras
//1 - valorização das ações
//Fórmula geral para valorização:
//Rendimento=Quantidade de Ações × (Preç​o Atual − Preç​o de Compra)
//Para ter este rendimento, o cliente estaria vendendo suas ações pelo preço atual delas
//Desta forma, seu lucro é exatamente a diferença entre o valor pelo qual ele venderá suas ações e o 
//valor investido originalmente.

//2 - dividendos pagos pela empresa da qual se adquire as ações
//Caso a empresa tenha a política de pagamento de dividendos aos acionistas, estas distribuirão uma porcentagem
//do lucro liquido. A periodicidade de pagamento dos dividendos pode variar de empresa para empresa.


//Para este sistema, em carater ilustrativo, considerarei o comportamento da Petrobrás, que costuma pagar
//dividendos trimestralmente. O valor do pagamento de dividendos será de R$1,50 por ação.
//O valor da ação terá uma valorização de 0,05% ao mês.

import java.time.LocalDate;

public class ContaInvestimento extends ContaBancaria{
    //taxa cobrada mensalmente pelo banco.
    private double taxaAdministracao;
    
    //atributoque armazenará uma compra de ações pelo cliente 
    private AcoesCliente compraAcoes1;

    //Construtor da classe filha - Estou usando a palavra-chave super para que, assim que houver a instanciação
    //de um objeto do tipo ContaCorrente, o construtor da classe mãe "ContaBancaria" inicialize o atributo 
    //numeroConta com um valor unico
    public ContaInvestimento(){
        super();
        compraAcoes1 = new AcoesCliente();
    }

    //getters e setters

    public double getTaxaAdministracao() {
        return taxaAdministracao;
    }
    
    public void setTaxaAdministracao(double taxaAdministracao) {
        this.taxaAdministracao = taxaAdministracao;
    }
    /*---------------------------------------------- */
    
    public AcoesCliente getCompraAcoes1() {
        return compraAcoes1;
    }
    
    public void setCompraAcoes1(AcoesCliente compraAcoes1) {
        this.compraAcoes1 = compraAcoes1;
    }
    /*---------------------------------------------- */

    //Polimorfismo por sobrescrita método abstrato da classe ContaBancária
    //Serve apenas como lembrete da necessidade de implementação de um método de saque em todas as classes filhas
    public void sacar(){}

    //polimorfismo por sobrecarga
    public void sacar(double valor) throws SemSaldoException{
        if(getSaldo()>= valor){
            setSaldo(getSaldo()-valor);
        }else{
            throw new SemSaldoException();
        }
        
    }

    //Método para realizar uma compra de ações
    public void comprarAcoes(int quantAcoes, double valorCompra, LocalDate dataCompra) throws SemSaldoException, JaPossuiAcoesException{
        
        if(compraAcoes1.getCompraRealizada() == true){
            throw new JaPossuiAcoesException();
        }else{
            if(getSaldo() >= quantAcoes*valorCompra){
                
                setSaldo(getSaldo()-quantAcoes*valorCompra);
                
                compraAcoes1.setQuantAcoes(quantAcoes);
                compraAcoes1.setValorCompra(valorCompra);
                compraAcoes1.setDataCompra(dataCompra);
                compraAcoes1.setCompraRealizada(true);
            }else{
                throw new SemSaldoException();
            }
        }
    }

    //Metodo para vender as ações
    public void venderAcoes(double valorAtual){
        double valorDeVenda;

        if(compraAcoes1.getCompraRealizada() == true){
            valorDeVenda = compraAcoes1.getQuantAcoes()*valorAtual;

            //Adiciona ao saldo o valor de venda das ações.
            depositar(valorDeVenda);

            //Muda o estado do atributo CompraRealizada para false, mostrando que o cliente não tem
            //mais ações, logo, pode realizar uma nova compra caso deseje.
            compraAcoes1.setCompraRealizada(false);
        }
    }

    //Método para retornar o lucro caso deseje vender as ações
    public double verificarLucro(double valorAtual){
        double lucro;

        lucro = (compraAcoes1.getQuantAcoes()*valorAtual) - (compraAcoes1.getQuantAcoes()*compraAcoes1.getValorCompra());
        
        return lucro;
    }

    //metodo para atualizar o saldo (dedução da taxa administrativa mensal)
    public void atualizaSaldo(){
        setSaldo(getSaldo() - taxaAdministracao);
    }
}