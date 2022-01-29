public class OperacaoDeposito extends Operacao implements ITaxas{

    public OperacaoDeposito(double valor){
        setTipo('d');
        setValor(valor);
    }
    public double calculaTaxas() {
        return 0;
    }
}
