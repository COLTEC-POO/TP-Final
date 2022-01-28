import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.*;

public abstract class Conta implements ITaxas, Serializable {

    private int numeroDaConta;
    private Agencia agencia;
    private Cliente dono;
    private double saldo;
    protected double limite;
    static int totalDeContas = 0;
    private List<Operacao> operacoes;
    private int senha;
    private static List<Conta> contas = new ArrayList<>();

//    construtores

    public Conta() {
        totalDeContas++;
        this.operacoes = new ArrayList<>();
        contas.add(this);
    }

    //     getters
    public int getSenha() {
        return senha;
    }

    public int getNumeroDaConta() {
        return this.numeroDaConta;
    }

    public Agencia getAgencia() {
        return this.agencia;
    }

    public Cliente getDono() {
        return this.dono;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public double getLimite() {
        return this.limite;
    }

// setters

    public void setDono(Cliente dono) {
        this.dono = dono;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public void setNumeroDaConta(int numeroDaConta) {
        this.numeroDaConta = numeroDaConta;
    }

    public abstract void setLimite(float limite);

    public void setSenha(int senha) {
        this.senha = senha;
    }

    //    Métodos

    public String toString() {
        return String.format("%s \nNumero da conta: %d\nSaldo atual da conta: R$ %f \nLimite: R$ %f", this.dono.toString(), this.numeroDaConta, this.saldo, this.limite);
    }

    boolean sacar(double valor) throws ValorNegativoException, SemLimiteException {
        Operacao opr;

        if (valor < 0) {
            throw new ValorNegativoException("Valor de saque negativo " + valor);
        }

        if (valor > this.limite) {
            throw new SemLimiteException("Valor de saque acima do limite da conta ");
        }

        if (valor <= this.saldo) {
            this.saldo -= valor;
            opr = new OperacaoSaque(valor);
            this.operacoes.add(opr);
            return true;
        } else {
            return false;
        }
    }

    void depositar(double valor) throws ValorNegativoException {
        Operacao opr;

        if (valor < 0) {
            throw new ValorNegativoException("Valor de deposito negativo " + valor);
        }

        this.saldo += valor;
        opr = new OperacaoDeposito(valor);
        this.operacoes.add(opr);
    }

    boolean transferir(Conta destino, double valor) throws ValorNegativoException, SemLimiteException {
        boolean saqueRealizado;
        saqueRealizado = this.sacar(valor);
        if (saqueRealizado) {
            destino.depositar(valor);
            return true;
        } else {
            return false;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof Conta) {
            Conta that = (Conta) obj;
            return (this.numeroDaConta == that.numeroDaConta);
        } else {
            return false;
        }
    }

    void imprimirExtrato() {

        System.out.println("\t //Extrato de trasaçoes da conta " + getDono().nome + " // \n");
        System.out.println("Por data da transacao:\n");
        for (Operacao atual : this.operacoes) {
            System.out.println(atual);
        }
        System.out.println("Por tipo de transacao:\n");

        Collections.sort(operacoes);
        for (Operacao atual : this.operacoes) {
            System.out.println(atual);
        }
    }

    void imprimirExtratoTaxas() {
        int i;
        double totalTaxas = 0;

        System.out.println("\t //Extrato de taxas " + getDono().nome + " // \n");
        System.out.println("Manutencao da conta \nR$" + calculaTaxas() + "\n");
        System.out.println("Operacoes");

        for (Operacao operacaoAtual : this.operacoes) {
            System.out.println(operacaoAtual.getTipo() + ": " + operacaoAtual.calculaTaxas());
            totalTaxas = totalTaxas + operacaoAtual.calculaTaxas();
        }

        totalTaxas = totalTaxas + calculaTaxas();
        System.out.println("\n");
        System.out.println("Total de taxas: R$ " + totalTaxas);
    }

    static boolean existe(int numeroDaConta, List<Conta> contas) {
        for (Conta atual : contas) {
            if (numeroDaConta == atual.numeroDaConta) {
                return true;
            }
        }
        return false;
    }

    static Conta retornaContaIgual(int numeroDaConta, List<Conta> contas) {
        for (Conta atual : contas) {
            if (numeroDaConta == atual.numeroDaConta) {
                return atual;
            }
        }
        return null;
    }

    static void salvarContas(String caminhoPasta, List<Conta> contas) {

        File pastaContas = new File(caminhoPasta);
        if (pastaContas.isDirectory()) {
            for (File atual : pastaContas.listFiles()) {
                atual.delete();
            }
            for (Conta atual : contas) {
                atual.salvarConta(caminhoPasta);
            }
        }
    }

    void salvarConta(String caminhoPasta) {

        String nomeDoArquivo = caminhoPasta + this.agencia.getNumeroDaAgencia() + "-" + this.numeroDaConta + ".ser";
        try {
            FileOutputStream fStream = new FileOutputStream(nomeDoArquivo);
            ObjectOutputStream oStream = new ObjectOutputStream(fStream);
            oStream.writeObject(this);
            oStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        } catch (IOException e) {
            System.out.println("Erro inesperado na escrita do dado");
            e.printStackTrace();
        }

    }

    static List<Conta> carregaContas(String caminho) {

        List<Conta> contas = new ArrayList<>();
        File pastaContas = new File(caminho);
        if (pastaContas.isDirectory()) {
            File[] arquivosContas = pastaContas.listFiles();
            for (File atual : arquivosContas) {
                Conta conta = Conta.carregaConta(atual);
                contas.add(conta);
            }
        }
        return contas;
    }

    private static Conta carregaConta(File arquivo) {

        try {
            FileInputStream fStream = new FileInputStream(arquivo);
            ObjectInputStream oStream = new ObjectInputStream(fStream);

            Conta conta = (Conta) oStream.readObject();

            oStream.close();
            return conta;

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        } catch (IOException e) {
            System.out.println("Erro inesperado na leitura do arquivo");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Classe não encontrada");
        }
        return null;
    }

    void exluirConta(Conta conta, List<Conta> contas, Agencia agencia) {

        contas.remove(conta);
    }
}


