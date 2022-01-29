import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public abstract class Conta implements ITaxas, Serializable {

    //atributos da classe Conta
    protected Cliente dono;
    private String numero;//transformei em string para usar o equals

    private Agencia agencia;

    private double saldo;

    private List<Operacao> operacoes;

    private double taxas;

    //variavel para controlar o indice do vetor de operações
    public int numeroOp;

    //numero de contas criadas durante a execução do sistema
    public static int totalContas=0;

    //armazena o número de saques feito por essa conta
    public int numSaques;

    //limite da conta
    private double limite;

    //metodos da classe conta:
    //-construtor:
    //metodo para criar
    public Conta(Cliente dono,String numero, Agencia agencia){
        this.saldo=0;
        this.numero=numero;
        this.agencia=agencia;
        this.dono=dono;
        this.operacoes=new ArrayList<>();
        numeroOp=0;
        Conta.totalContas++;
        this.numSaques=0;
        this.taxas=0;
        this.limite=0;
    }

    //-getters e setters:
    public String getDono(){
        return dono.getNome(); }

    public void setDono(String nome){
        dono.setNome(nome); }

    public String getNumero(){
        return this.numero;}

    public void setNumero(String numero){
        this.numero=numero; }

    public double getSaldo(){
        return this.saldo; }

    public void setAgencia(int numero){
        this.agencia.setNumero(numero); }

    public int getAgencia(){
        return this.agencia.getNumero();  }

    public ArrayList<Operacao> getOperacoes() {
        return (ArrayList<Operacao>) operacoes;
    }

    public void setOperacoes(ArrayList operacoes) {
        this.operacoes = operacoes;
    }
    public double getTaxas() {
        return taxas;
    }
    public void setTaxas(double taxas) {
        this.taxas = taxas;
    }

    public void setLimite(double valor) {
        this.limite=valor;
    };




    //-saque - implementado com tratamento de exceção
    public boolean sacar(double valor) throws ValorNegativoException,SemLimiteException {
        if (valor<0){
            throw new ValorNegativoException("Saque negativo- não pode ser feito com valor informado  "+valor);//condição que dispara a exceção
        }
        else{
            if(valor>this.saldo){
                throw new SemLimiteException("Saque acima do saldo/limite em conta "+this.saldo);//condição que dispara a exceção
            }else{
                operacoes.add(new OperacaoSaque(valor));
                this.saldo-=valor;
                numeroOp++;
                numSaques++;
                return true;
            }
        }
    }

    //- depositar- implementado com tratamento de exceção
    public void depositar(double valor) throws ValorNegativoException{
        if (valor < 0) {
            throw new ValorNegativoException("O depósito negativo- não pode ser feito com valor informado "+valor);
        }else{
            operacoes.add(new OperacaoDeposito(valor));
            this.saldo=this.saldo+valor;
            numeroOp++;
        }
    }

    //transferir
    public boolean transferir(Conta destino, double valor) throws ValorNegativoException, SemLimiteException {
        boolean saqueRealizado=this.sacar(valor);
        if(saqueRealizado){
            destino.depositar(valor);
            return true;
        }else{
            return false;
        }
    }

    public String toString(){
        String contaStr= "Número:"+this.numero+"\n"+
                "Nome:"+this.dono.getNome()+"\n"+
                "Saldo:"+this.saldo+"\n";
        return contaStr;
    }

    public boolean equals(Object obj){

        if(obj instanceof Conta){
            Conta objConta=(Conta)obj;
            if(this.numero.equals(objConta.numero)){
                return true;
            }
            else{
                return false;
            }
        }else{
            return false;
        }

    }

    public int imprimirExtrato(int opcao){
        System.out.println("====Extrato de Operações ====");
        if(opcao==1){
            System.out.println("Visualização: por data de operação");
            for(Operacao atual: this.operacoes){
                System.out.println(atual.getData()+" "+atual.getTipo()+" "+atual.getValor());
            }
        }
        if(opcao==2){
            System.out.println("Visualização: por tipo de operação");
            Collections.sort(this.operacoes);
            for(Operacao atual: this.operacoes){
                System.out.println(atual.getData()+" "+atual.getTipo()+" "+atual.getValor());
            }
        }
        return opcao;
    }

    //-imprimir extrato de taxas
    public void imprimirExtratoTaxas(){
        double totalTaxas=this.calculaTaxas();
        System.out.println("===Extrato de Taxas===");
        System.out.println();
        System.out.println("Manutenção de Conta:"+this.calculaTaxas());
        System.out.println();
        System.out.println("Operações:");
        for(Operacao atual:this.operacoes){
            if(atual instanceof OperacaoSaque){
                totalTaxas+=((OperacaoSaque) atual).calculaTaxas();
                System.out.println("Saque:"+((OperacaoSaque) atual).calculaTaxas());
            }
        }
        System.out.println();
        System.out.println("Total de taxas:"+totalTaxas);
    }

    public void salvaconta(Conta nomeconta) {

        try{
            String nomearquivo=this.agencia+"-"+this.numero;
            FileOutputStream fStream=new FileOutputStream(nomearquivo);
            ObjectOutputStream oStream=new ObjectOutputStream(fStream);
            oStream.writeObject(nomeconta);
            oStream.close();
        }catch (Exception exc){
            exc.printStackTrace();
        }

    }

    public static Object restauraconta( String nomearquivo){
        Object conta=null;
        try{
            FileInputStream fStream=new FileInputStream(nomearquivo);
            ObjectInputStream oStream=new ObjectInputStream(fStream);
            conta=(Conta) ((ObjectInputStream) oStream).readObject();
            oStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return conta;
    }

}
