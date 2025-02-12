//Kevin Luiz Botelho Lima - RA: 2266423

/*Esta exceção é lançada caso o cliente deseje realizar uma nova compra de ações 
 *Mesmo já possuindo uma compra ativa.
 
 Para realizar uma nova compra de ações, deverá primeiramente vender as ações que possui.
*/
public class JaPossuiAcoesException extends Exception{

    public String impErrCompra(){
        return("Já adquiriu acoes\nNao e possivel comprar mais de 1 pacote de acoes.");
    }
}