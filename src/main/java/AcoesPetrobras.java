//Kevin Luiz Botelho Lima - RA: 2266423

public class AcoesPetrobras{
    
    //valor de cada ação
    private double valor;
    

    //Metodo construtor - Atribui o valor de 35 reais para o preço de cada ação quando o sistema começar a rodar
    public AcoesPetrobras(){
        valor = 35.0;
    }

    //Metodo para atualizar o preço de cada ação - Neste sistema, em carater representativo 
    //do funcionamento real de uma ação, assumi a valorização do preço das ações como 1% ao mês.
    public void atualizaValor(){
        valor = valor*1.1;
    }

    //getters e setters
    /*---------------------------------------------- */

    public double getValor() {
        return valor;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }
    /*---------------------------------------------- */

}