import java.io.Serializable;
import java.util.Date;

public abstract class Cliente implements Serializable {

    //Conteudo classe cliente

    String nome;
    String endereco;
    Date data;


    //-----------------------------------------------------------------------------------------------------//

    Cliente (){
        this.data =  new Date();
    }

    public String toString(){
        return "Cliente invalido.";
    }

    //-----------------------------------------------------------------------------------------------------//
    public abstract boolean autenticar(String chave, String chave1);{

    }

    //-----------------------------------------------------------------------------------------------------//
}
