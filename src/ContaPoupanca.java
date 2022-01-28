public class ContaPoupanca extends Conta{

    public void setLimite(float limite) throws IllegalArgumentException {
        if (limite < 100) {
            throw new IllegalArgumentException("Limite inferior ao limite minimo dessa modalidade de conta. Limite minimo = 100");
        } else if (limite > 1000) {
            throw new IllegalArgumentException("Limite superior ao limite maximo dessa modalidade de conta. Limite maximo = 1000 reais.");
        }else{
            this.limite = limite;
        }
    }

    public double calculaTaxas(){
        return 0;
    }
}
