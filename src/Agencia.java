import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Agencia implements Serializable {
    private int numeroDaAgencia;
    List<Conta> contasPertencentes;

    public Agencia(int numeroDaAgencia) {
        this.numeroDaAgencia = numeroDaAgencia;
        this.contasPertencentes = new ArrayList<>();
    }
    // Setters

    public void setNumeroDaAgencia(int numeroDaAgencia) {
        this.numeroDaAgencia = numeroDaAgencia;
    }

    // Getters
    public int getNumeroDaAgencia() {
        return numeroDaAgencia;
    }

    // Métodos

    public boolean equals(Object obj) {
        if (obj instanceof Agencia) {
            Agencia that = (Agencia) obj;
            return (this.numeroDaAgencia == that.numeroDaAgencia);
        } else {
            return false;
        }
    }

    static boolean existe(int numeroDaAgencia, List<Agencia> agencias) {
        for (Agencia atual : agencias) {
            if (numeroDaAgencia == atual.numeroDaAgencia) {
                return true;
            }
        }
        return false;
    }
    static Agencia retornaAgenciaIgual(int numeroDaAgencia, List<Agencia> agencias) {
        for (Agencia atual : agencias) {
            if (numeroDaAgencia == atual.numeroDaAgencia) {
                return atual;
            }
        }
        return null;
    }

    void excluirAgencia(Agencia agencia, List<Agencia> agencias, List<Conta> contas) {

        for (Conta atual : agencia.contasPertencentes) {
            atual.exluirConta(atual, contas, agencia);
        }

        agencias.remove(agencia);

        System.out.println("Agência removida com sucesso.");

    }

    static void salvarAgencias(String caminhoPasta, List<Agencia> agencias) {

        File pastaAgencias = new File(caminhoPasta);
        if (pastaAgencias.isDirectory()) {
            for (File atual : pastaAgencias.listFiles()) {
                atual.delete();
            }
            for (Agencia atual : agencias) {
                atual.salvarAgencia(caminhoPasta);
            }
        }
    }

    void salvarAgencia(String caminhoPasta) {

        String nomeDoArquivo = caminhoPasta + this.numeroDaAgencia + ".ser";
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

    static List<Agencia> carregaAgencias(String caminho) {

        List<Agencia> agencias = new ArrayList<>();
        File pastaAgencias = new File(caminho);
        if (pastaAgencias.isDirectory()) {
            File[] arquivosAgencias = pastaAgencias.listFiles();
            for (File atual : arquivosAgencias) {
                Agencia agencia = Agencia.carregaAgencia(atual);
                agencias.add(agencia);
            }
        }
        return agencias;
    }

    private static Agencia carregaAgencia(File arquivo) {

        try {
            FileInputStream fStream = new FileInputStream(arquivo);
            ObjectInputStream oStream = new ObjectInputStream(fStream);

            Agencia agencia = (Agencia) oStream.readObject();

            oStream.close();
            return agencia;

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
}

