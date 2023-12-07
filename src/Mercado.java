public class Mercado extends Produto{
    public Mercado(int id, String descricao, String marca, int quantidade, double preco){
        super(id, "Mercado", descricao, marca, quantidade, preco);
    }
    public Mercado(String descricao, String marca, int quantidade, double preco){
        super("Mercado", descricao, marca, quantidade, preco);
    }
}
