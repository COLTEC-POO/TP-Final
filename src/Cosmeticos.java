public class Cosmeticos extends Produto{
    public Cosmeticos(int id, String descricao, String marca, int quantidade, double preco){
        super(id, "Cosmeticos", descricao, marca, quantidade, preco);
    }
    public Cosmeticos(String descricao, String marca, int quantidade, double preco){
        super("Cosmeticos", descricao, marca, quantidade, preco);
    }
}
