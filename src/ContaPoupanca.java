public class ContaPoupanca extends Conta implements ITaxas {

    private double limite;

    public ContaPoupanca(Cliente dono, String numConta, Agencia agencia) {
        super(dono, numConta,agencia);
    }

    public void setLimite(double valor) throws IllegalArgumentException{
        if(valor<100 || valor>1000){
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
