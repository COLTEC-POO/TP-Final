public class Gerente extends Usuario {
    public Gerente(String nome, String senha){
        super(nome, senha, "Gerente");
    }

    public Gerente(int id, String nome){
        super(id, nome, "Gerente");
    }
    public Gerente(int id, String nome, String senha) {
        super(id, nome, "Cliente", senha);
    }
}
