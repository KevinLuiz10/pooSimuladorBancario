//Kevin Luiz Botelho Lima - RA: 2266423
import java.time.LocalDate;

public abstract class ContaBancaria{
    private static int contador = 0;
    private final int numeroConta;
	private double saldo;
	private int agencia;
	private LocalDate dataAbertura;

    //atribui o numero da conta no ato de instanciação da mesma.
    public ContaBancaria(){
        numeroConta = ++contador;
    }

    //getters e setters

    public int getNumeroConta() {
        return numeroConta;
    }
	/*---------------------------------------------- */
	
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
	/*---------------------------------------------- */
	
    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }
	/*---------------------------------------------- */

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }
    /*---------------------------------------------- */

    //Método abstrato para servir de lembrete da necessidade de implementação nas classes filhas.
    public abstract void sacar();

    public void depositar(double valor){
        saldo += valor;
    }

}