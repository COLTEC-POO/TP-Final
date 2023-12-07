public abstract class Usuario {
    private int id;
    private String nome;
    private String senha;
    private String posicao;

    public Usuario(String nome, String senha, String posicao){
        this.nome = nome;
        this.senha = senha;
        this.posicao = posicao;
    }

    public Usuario(int id, String nome, String posicao){
        this.id = id;
        this.nome = nome;
        this.posicao = posicao;
    }
    public Usuario(int id, String nome, String posicao, String senha){
        this.id = id;
        this.nome = nome;
        this.posicao = posicao;
        this.senha = senha;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }
}


