//Kevin Luiz Botelho Lima - RA: 2266423
import java.time.LocalDate;

public class Cliente{
    private long cpf;
    private String nome;
    private LocalDate dataNascimento;
    private String sexo;
    private long celular;
	private ContaCorrente contaCorrente;
	private ContaPoupanca contaPoupanca;
	private ContaInvestimento contaInvestimento;

    //getters e setters

    public long getCpf(){
		return cpf;
	}

	public void setCpf(long cpf){
		this.cpf = cpf;
	}
    /*------------------------------------------- */

    public String getNome(){
		return nome;
	}

	public void setNome(String nome){
		this.nome = nome;
	}
    /*------------------------------------------- */

    public LocalDate getDataNascimento(){
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento){
		this.dataNascimento = dataNascimento;
	}
    /*------------------------------------------- */

    public String getSexo(){
		return sexo;
	}

	public void setSexo(int sexo){
		if(sexo == 1){
			this.sexo = "Masculino";
		} else if(sexo == 2){
			this.sexo = "Feminino";
		}
		
	}
    /*------------------------------------------- */

    public long getCelular(){
		return celular;
	}

	public void setCelular(long celular){
		this.celular = celular;
	}
	/*------------------------------------------- */

	public ContaCorrente getContaCorrente() {
    return contaCorrente;
	}

	public void setContaCorrente(ContaCorrente contaCorrente) {
		this.contaCorrente = contaCorrente;
	}
	/*------------------------------------------- */

	public ContaPoupanca getContaPoupanca() {
		return contaPoupanca;
	}

	public void setContaPoupanca(ContaPoupanca contaPoupanca) {
		this.contaPoupanca = contaPoupanca;
	}
	/*------------------------------------------- */

	public ContaInvestimento getContaInvestimento() {
		return contaInvestimento;
	}

	public void setContaInvestimento(ContaInvestimento contaInvestimento) {
		this.contaInvestimento = contaInvestimento;
	}
	/*Métodos para instanciar uma conta*/

	public void criarCc(){
		contaCorrente = new ContaCorrente();
	}

	public void criarCp(){
		contaPoupanca = new ContaPoupanca();
	}

	public void criarCi(){
		contaInvestimento = new ContaInvestimento();
	}
}