import java.io.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Conta implements ITaxas, Serializable{

    //Conteudo class conta

    private Cliente memoryCliente;
    private int memoryId;
    private double memorySaldo;
    private double memoryLimite;
    private int memoryAgencia;
    static int totalContas = 0;
    private int a;



    List<Operacao> operacoes;

    //-----------------------------------------------------------------------------------------------------//
    public Conta (){
        this.memoryAgencia = 0;
        this.memoryId = -1;
        this.memoryCliente = null;
        this.memoryLimite = 0.00;
        this.memorySaldo = 0.00;

        this.operacoes = new ArrayList<>();

        totalContas++;
    }

    //-----------------------------------------------------------------------------------------------------//
    //Getters
    //-----------------------------------------------------------------------------------------------------//
    public int getMemoryId(){
        return this.memoryId;
    }

    public int getA(){
        return this.a;
    }

    public Cliente getMemoryCliente(){
        return this.memoryCliente;
    }

    public double getMemorySaldo(){
        return this.memorySaldo;
    }

    public double getMemoryLimite(){
        return this.memoryLimite;
    }

    public int getMemoryAgencia(){
        return this.memoryAgencia;
    }

    //-----------------------------------------------------------------------------------------------------//
    //Setters
    //-----------------------------------------------------------------------------------------------------//
    public void setMemoryCliente(Cliente memoryCliente){
        this.memoryCliente = memoryCliente;
    }


    public void setMemoryAgencia(int memoryAgencia){
        this.memoryAgencia = memoryAgencia;
    }

    public void setMemoryId(int memoryId){
        this.memoryId = memoryId;
    }

    public void setA(int a){
        this.a = a;
    }

    public void setLimite(double limite) {
        double minLimite = 0.00;
        if (limite < minLimite) {
            this.memoryLimite = 0.00;
        } else {
            this.memoryLimite = limite;
        }
    }

    //-----------------------------------------------------------------------------------------------------//
    //Métodos
    //-----------------------------------------------------------------------------------------------------//
    boolean sacar(double valor) throws ValorNegativoException {

        if(valor < 0){
            throw new ValorNegativoException("Error: Saque Negativo $:" + valor);

        }

        if (valor <= this.memorySaldo) {
            this.memorySaldo -= valor;
            this.operacoes.add(new OperacaoSaque(valor));
            return true;
        } else {
            return false;
        }
    }

    boolean sacarLimite(double valor) throws SemLimiteException {

        if(valor < 0){
            throw new SemLimiteException("Error: Saque de Limite Negativo $:");

        }
        if (valor <= this.memoryLimite) {
            this.memoryLimite -= valor;
            this.operacoes.add(new OperacaoSaque(valor));
            return true;
        } else {
            return false;
        }
    }

    void depositar(double valor) throws ValorNegativoException {

        if(valor < 0){
            throw new ValorNegativoException("Error: Deposito Negativo $:" + valor);

        }

        this.memorySaldo += valor;
        this.operacoes.add(new OperacaoDeposito(valor));
    }

    boolean transferir(Conta destino, double valor) throws ValorNegativoException {
        boolean saqueRealizado;
        saqueRealizado = this.sacar(valor);
        if (saqueRealizado) {
            destino.depositar(valor);
            return true;
        } else {
            return false;
        }
    }

    public boolean equals(Object obj){
        Conta that = (Conta) obj;

        return (this.memoryId == that.memoryId);
    }

    void imprimirExtrato (int a){
        if(a == 1){
            for(Operacao atual : this.operacoes)
                System.out.println(atual);
        }
        if(a == 2){
            Collections.sort(operacoes);
            for(Operacao atual : this.operacoes)
                System.out.println(atual);
        }
        else if((a >= 3) || (a <= 0)){
            throw new IllegalArgumentException("Error ao imprimir extrato: Escolha 1 para padrão ou 2 ordenados pelo seu tipo");

        }
    }

    void ImprimeTaxas(){
        System.out.println("Manutenção da Conta\t$:\t" + this.calculaTaxas());
        for(Operacao atual : this.operacoes)
            System.out.println(atual.getDataOperacao() + "\tTaxa paga por realizar essa operação:\t" + atual.calculaTaxas() + "\t"+ "Tipo:\t" + atual.getTipo() + "\t");
    }

    public String toString(){
        return String.format("%s \nID: %d\nSaldo: %f \nLimite: %f", this.memoryCliente.toString(), this.memoryId, this.memorySaldo, this.memoryLimite);
    }

    void saveContas (){
        File arquivo = new File("db\\contas\\"+memoryAgencia+"-"+memoryId+".ser");
            try {
                FileOutputStream fStream = new FileOutputStream(arquivo);
                ObjectOutputStream oStream = new ObjectOutputStream(fStream);
                oStream.writeObject(this);
                oStream.close();
            }catch(FileNotFoundException e) {
                System.out.println("Arquivo não encontrado");
            } catch(IOException e) {
                System.out.println("Erro inesperado na escrita do dado");
                e.printStackTrace();
            }
    }

    void showConta (int memoryAgencia, int memoryId){

        File arquivo = new File("db\\contas\\"+memoryAgencia+"-"+memoryId+".ser");

        try{
            FileInputStream fStream = new FileInputStream(arquivo);
            ObjectInputStream oStream = new ObjectInputStream(fStream);

            Conta conta = (Conta) oStream.readObject();

            oStream.close();
        }catch(FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        } catch(IOException e) {
            System.out.println("Erro inesperado na leitura do arquivo");
            e.printStackTrace();
        }catch(ClassNotFoundException e) {
            System.out.println("Classe não encontrada");
        }
    }

    void deleteConta(int memoryAgencia, int memoryId){
        File arquivo = new File(this.memoryAgencia + "-" + this.memoryId+ ".ser");;
        if(arquivo.delete()){
            System.out.println("Arquivo excluido com sucesso.");
        }
        else{
            System.out.println("Não foi possivel excluir o arquivo");
        }
    }

    void saveAgencia (){
        File arquivo = new File("db\\agencias\\"+memoryAgencia+"-.ser");
        try {
            FileOutputStream fStream = new FileOutputStream(arquivo);
            ObjectOutputStream oStream = new ObjectOutputStream(fStream);
            oStream.writeObject(this);
            oStream.close();
        }catch(FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        } catch(IOException e) {
            System.out.println("Erro inesperado na escrita do dado");
            e.printStackTrace();
        }
    }
    void deleteAgencia(int memoryAgencia){
        File arquivo = new File(this.memoryAgencia + "-.ser");;
        if(arquivo.delete()){
            System.out.println("Arquivo excluido com sucesso.");
        }
        else{
            System.out.println("Não foi possivel excluir o arquivo");
        }
    }

    //-----------------------------------------------------------------------------------------------------//

}
