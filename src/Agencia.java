import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Agencia implements Comparable<Agencia>, Serializable {
    //atributos
    private String nome;
    private int numero;
    private List<Conta> contasvinculadas;

    public Agencia(String nome, int numero) {
        this.nome = nome;
        this.numero = numero;
        this.contasvinculadas=new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Conta> getContasvinculadas() {
         return (ArrayList<Conta>)contasvinculadas;
    }

    public void setContasvinculadas(Conta contas) {

        this.contasvinculadas.add(contas) ;
    }

    //precisa adequar
    @Override
    public int compareTo(Agencia o) {
        if(this.numero<o.getNumero()){
            return -1;
        }
        if(this.numero>o.getNumero()){
            return 1;
        }
        return 0;
    }

    public void salvaAgencia(String nomeAgencia) {

        try{
            String nomeArquivo=this.nome+"-"+this.numero;
            FileOutputStream fStream=new FileOutputStream("db.agencia"+"-"+nomeArquivo);
            ObjectOutputStream oStream=new ObjectOutputStream(fStream);
            oStream.writeObject(nomeAgencia);
            oStream.close();
        }catch (Exception exc){
            exc.printStackTrace();
        }

    }


    public static Object restaurAgencia( String nomeAgencia){
        Object agencia=null;
        try{
            FileInputStream fStream=new FileInputStream("db.agencia"+"-"+nomeAgencia);
            ObjectInputStream oStream=new ObjectInputStream(fStream);
            agencia=(Agencia) ((ObjectInputStream) oStream).readObject();
            oStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return agencia;
    }
}