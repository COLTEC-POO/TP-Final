public class ContaPoupanca extends Conta{



    public void setLimite(double limite){
        if (limite < 100.00 || limite>1000.00) {
            throw new IllegalArgumentException("O limite não pode ser maior que 1000 ou menor que 100!");
        }
    }
    public double calculaTaxas(){
        return 0;
    }
}
