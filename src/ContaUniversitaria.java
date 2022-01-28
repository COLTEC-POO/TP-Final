public class ContaUniversitaria extends Conta{

    public void setLimite(double limite){
        if (limite > 500.00) {
            throw new IllegalArgumentException("O limite não pode ser maior que 500!");
        }else{
            if (limite < 0.00){
                throw new IllegalArgumentException("O limite não pode ser menor que 0!");
            }
        }
    }
    public double calculaTaxas(){
        return 0;
    }
}
