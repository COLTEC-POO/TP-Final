package src;

public class Cliente extends Usuario{
    public Cliente(String nome, String senha){
        super(nome, senha, "Cliente");
    }

    public Cliente(int id, String nome){
        super(id, nome, "Cliente");
    }
    public Cliente(int id, String nome, String senha) {
        super(id, nome, "Cliente", senha);
    }
}