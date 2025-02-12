//Kevin Luiz Botelho Lima - RA: 2266423

/*Esta exceção é lançada caso o cliente tente realizar mais saques do que o limite permitido
 * para uma conta do tipo poupança.
 */
public class NaoPossuiSaqueException extends Exception{

    public String impErrSaque(){
        return("Não possui saques disponíveis para o dia de hoje.");
    }
}