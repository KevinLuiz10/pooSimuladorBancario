//Kevin Luiz Botelho Lima - RA: 2266423

/*Esta exceção é lançada caso o cliente deseje realizar alguma operação, porém  
não possui saldo suficiente na conta para completá-la.
*/

public class SemSaldoException extends Exception{

    public String impErrSaldo(){
        return("Não possui saldo suficiente para finalizar a operação.");
    }
}