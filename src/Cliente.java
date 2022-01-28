import java.io.Serializable;
import java.util.Date;

public abstract class Cliente implements Serializable {
    String nome;
    Date data;
    String endereco;

    Cliente (){
        this.data =  new Date();
    }
    public String toString(){
        return "Cliente invalido.";
    }

    public abstract boolean autenticar (String chaveDeIdentificacao);
}
