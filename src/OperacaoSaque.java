public class OperacaoSaque extends Operacao{
    public OperacaoSaque (double valor){
        super('s', valor);
    }
    public double calculaTaxas() {
        return 0.05;

    }

}
