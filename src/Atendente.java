package src;

public class Atendente extends Usuario{
    public Atendente(String nome, String senha){
        super(nome, senha, "Atendente");
    }

    public Atendente(int id, String nome){
        super(id, nome, "Atendente");
    }
    public Atendente(int id, String nome, String senha) {
        super(id, nome, "Cliente", senha);
    }
}
