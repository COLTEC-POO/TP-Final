import java.util.Date;

public class Relatorio{
    private int id;
    private Date data;
    private int idProduto;
    private int idUsuario;
    private String tipoMovimentacao;
    private int quantidade;
    private double receita;

    public Relatorio(int idProduto, int idUsuario, String tipoMovimentacao, int quantidade, double receita) {
        this.data = new Date();
        this.idProduto = idProduto;
        this.idUsuario = idUsuario;
        this.tipoMovimentacao = tipoMovimentacao;
        this.quantidade = quantidade;
        this.receita = receita;
    }
    public Relatorio(int id, Date data, int idProduto, int idUsuario, String tipoMovimentacao, int quantidade, double receita) {
        this.id = id;
        this.data = data;
        this.idProduto = idProduto;
        this.idUsuario = idUsuario;
        this.tipoMovimentacao = tipoMovimentacao;
        this.quantidade = quantidade;
        this.receita = receita;
    }
    public Relatorio() {

    }

    public double geradorReceita(int quantidade, double preco){
        return (double)quantidade * preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getReceita() {
        return receita;
    }

    public void setReceita(double receita) {
        this.receita = receita;
    }
}
