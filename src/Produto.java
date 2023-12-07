public abstract class Produto{
    private int id;
    private String tipo;
    private String descricao;
    private String marca;
    private int quantidade;
    private double preco;

    public Produto(int id, String tipo, String descricao, String marca, int quantidade, double preco) {
        this.id = id;
        this.tipo = tipo;
        this.descricao = descricao;
        this.marca = marca;
        this.quantidade = quantidade;
        this.preco = preco;
    }
    public Produto(String tipo, String descricao, String marca, int quantidade, double preco) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.marca = marca;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
