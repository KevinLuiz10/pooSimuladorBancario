//Kevin Luiz Botelho Lima - RA: 2266423

import java.time.LocalDate;

public class AcoesCliente{
    //quantidade de ações compradas pelo cliente
    private int quantAcoes;
    
    //valor pelo qual o cliente comprou cada ação
    private double valorCompra;

    //Data de compra das ações
    private LocalDate dataCompra;

    //Atributo que informa se, no estado atual do objeto do tipo AcoesCliente, ele representa uma compra já 
    //realizada ou não
    private boolean compraRealizada = false;
    
    //getters e setters

    public int getQuantAcoes() {
        return quantAcoes;
    }
    
    public void setQuantAcoes(int quantAcoes) {
        this.quantAcoes = quantAcoes;
    }
    /*---------------------------------------------- */

    public double getValorCompra() {
        return valorCompra;
    }
    
    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }
    /*---------------------------------------------- */
    
    public LocalDate getDataCompra() {
        return dataCompra;
    }
    
    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }
    /*---------------------------------------------- */

    public boolean getCompraRealizada() {
        return compraRealizada;
    }


    public void setCompraRealizada(boolean compraRealizada) {
        this.compraRealizada = compraRealizada;
    }
    

}