public class ContaUniversitaria extends Conta{
    public void setLimite(float limite) throws IllegalArgumentException{
        if (limite < 0) {
            throw new IllegalArgumentException("Limite inferior ao limite minimo dessa modalidade de conta. Limite minimo = 0 reais.");
        } else if (limite > 500) {
            throw new IllegalArgumentException("Limite superior ao limite maximo dessa modalidade de conta. Limite maximo = 500 reais.");
        }else{
            this.limite = limite;
        }
    }

    public double calculaTaxas(){
        return 0;
    }
}
