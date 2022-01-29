public class ContaCorrente extends Conta implements ITaxas {

    private double limite;


    public ContaCorrente(Cliente dono, String numConta, Agencia agencia) {
        super(dono, numConta,agencia);
        calculaTaxas();
    }

    public void setLimite (double valor) throws IllegalArgumentException {
        if(valor<500 || valor>1000){
            throw new IllegalArgumentException("Alteração não realizada!Valor de excedido!");
        }
        else{
            this.limite=valor;
            System.out.println("Limite alterado!");
        }
    }

    public double calculaTaxas() {

        if (dono instanceof PessoaFisica) {
            setTaxas(10);
            //taxas= 10;
        }
        if (dono instanceof PessoaJuridica) {
            setTaxas(20);
            //taxas=20;
        }
        return getTaxas();
    }

}
