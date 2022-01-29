public class OperacaoSaque extends Operacao implements ITaxas{


    public OperacaoSaque(double valor){
        setTipo('s');
        setValor(valor);
    }
    public double calculaTaxas() {
        return 0.05;
    }

}
