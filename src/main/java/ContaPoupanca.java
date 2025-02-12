//Kevin Luiz Botelho Lima - RA: 2266423

public class ContaPoupanca extends ContaBancaria{
    //porcentagem de rendimento mensal.
    private double rendimentoMensal;

    //A conta do tipo poupança permite, no máximo, 3 saques diários.
    private int numeroSaquesDiarios;

    //Construtor da classe filha - Estou usando a palavra-chave super para que, assim que houver a instanciação
    //de um objeto do tipo ContaCorrente, o construtor da classe mãe "ContaBancaria" inicialize o atributo 
    //numeroConta com um valor unico
    public ContaPoupanca(){
        super();
    }

    //getters e setters

    public double getRendimentoMensal() {
        return rendimentoMensal;
    }

    public void setRendimentoMensal(double rendimentoMensal) {
        if (rendimentoMensal < 0) { // Validação opcional
            throw new IllegalArgumentException("O rendimento mensal não pode ser negativo.");
        }
        this.rendimentoMensal = rendimentoMensal;
    }
    /*---------------------------------------------- */

    public int getNumeroSaquesDiarios() {
        return numeroSaquesDiarios;
    }

    public void setNumeroSaquesDiarios(int numeroSaquesDiarios) {
        if (numeroSaquesDiarios < 0) { // Validação opcional
            throw new IllegalArgumentException("O número de saques diários não pode ser negativo.");
        }
        this.numeroSaquesDiarios = numeroSaquesDiarios;
    }

    //Polimorfismo por sobrescrita - método abstrato da classe ContaBancária
    //Serve apenas como lembrete da necessidade de implementação de um método de saque em todas as classes filhas
    public void sacar(){}

    //Polimorfismo por sobrecarga
    public void sacar(double valor) throws NaoPossuiSaqueException, SemSaldoException{
        
        if(getNumeroSaquesDiarios() > 0){
            if(getSaldo()>= valor){
                setSaldo(getSaldo()-valor);
                numeroSaquesDiarios--;
            }else{
                throw new SemSaldoException();
            }
        }else{
            throw new NaoPossuiSaqueException();
        }
        
    }

    //Método para atualizar o saldo (virada de mês)
    public void rendimentoMensal(){
        if(getSaldo()>0){
            setSaldo(getSaldo()*(1+rendimentoMensal/100));
        } 
    }

    //Método para resetar a quantidade de saques diários permitidos
    public void resetQuantSaques(){
        numeroSaquesDiarios = 3;
    }

}