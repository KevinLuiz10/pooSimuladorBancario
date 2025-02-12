//Kevin Luiz Botelho Lima - RA: 2266423

public class ContaCorrente extends ContaBancaria{
    private double limiteChequeEspecial;

    //Quantidade de transferências gratuitas no mês. Em todo aniversário da conta, é resetado para 5.
    private int numeroTransferenciasGratuitas; 

    //Caso a quantidade de transferências gratuitas seja esgotada, este valor será deduzido para a realização de novas transferências.
    private double valorTarifaTransferencia; 

    //Construtor da classe filha - Estou usando a palavra-chave super para que, assim que houver a instanciação
    //de um objeto do tipo ContaCorrente, o construtor da classe mãe "ContaBancaria" inicialize o atributo 
    //numeroConta com um valor unico
    public ContaCorrente(){
        super();
    }

    
    //getters e setters

    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    public void setLimiteChequeEspecial(double limiteChequeEspecial) {
        this.limiteChequeEspecial = limiteChequeEspecial;
    }
	/*---------------------------------------------- */

    public int getNumeroTransferenciasGratuitas() {
        return numeroTransferenciasGratuitas;
    }

    public void setNumeroTransferenciasGratuitas(int numeroTransferenciasGratuitas) {
        this.numeroTransferenciasGratuitas = numeroTransferenciasGratuitas;
    }
	/*---------------------------------------------- */

    public double getValorTarifaTransferencia() {
        return valorTarifaTransferencia;
    }

    public void setValorTarifaTransferencia(double valorTarifaTransferencia) {
        this.valorTarifaTransferencia = valorTarifaTransferencia;
    }

    //Polimorfismo por sobrescrita - método abstrato da classe ContaBancária
    //Serve apenas como lembrete da necessidade de implementação de um método de saque em todas as classes filhas
    public void sacar(){}
    
    ////Polimorfismo por sobrecarga
    public void sacar(double valor) throws SemSaldoException{
        if(getSaldo() + limiteChequeEspecial >= valor){
            setSaldo(getSaldo()-valor);
        }else{
            throw new SemSaldoException();
        }
        
    }

}