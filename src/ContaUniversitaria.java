public class ContaUniversitaria extends Conta implements ITaxas {
    private double limite;

    public ContaUniversitaria(Cliente dono, String numConta, Agencia agencia) {
        super(dono, numConta,agencia);
    }

    public void setLimite(double valor)throws IllegalArgumentException{
        if(valor<0 || valor>500){
            throw new IllegalArgumentException("Alteração não realizada!Valor de excedido!");
        }
        else{
            this.limite=valor;
            System.out.println("Limite alterado!");
        }
    }
    public double calculaTaxas() {
        setTaxas(0);
        //taxas=0;
        return getTaxas();
    }
}