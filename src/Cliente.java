import java.io.Serializable;
import java.util.Date;

public abstract class Cliente implements Serializable {
    private String nome;
    private String endereco;
    private Date dataCliente;


    public Cliente(){
        this.nome="INVALIDO";
        //this.endereco=endereço;
        this.dataCliente=new Date();
    }

     public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome=nome;
    }

    public String getEndereco(){
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Date getDataCliente(){
        return this.dataCliente;
    }

    public void imprimirCliente(Cliente cliente){
        System.out.println("Nome:"+getNome());
        System.out.println("Endereço:"+getEndereco());
        System.out.println("Data da criação do cadastro:"+getDataCliente());
    }

    public abstract boolean autenticar(String chave);

}
