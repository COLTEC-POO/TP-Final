public class ContaCorrente extends Conta{

    public void setLimite(float limite) throws IllegalArgumentException {
        if (limite < -100) {
            throw new IllegalArgumentException("Limite inferior ao limite minimo dessa modalidade de conta. Limite minimo = -100 reais.");
        } else {
            this.limite = limite;
        }
    }

    public double calculaTaxas(){
        double taxas = 0;

        if (getDono() instanceof PessoaJuridica){
            taxas = 20;
        }

        if (getDono() instanceof PessoaFisica){
            taxas = 10;
        }

        return taxas;
    }
}

