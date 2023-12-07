public class Padaria extends Produto{
    public Padaria(int id, String descricao, String marca, int quantidade, double preco){
        super(id, "Padaria", descricao, marca, quantidade, preco);
    }
    public Padaria(String descricao, String marca, int quantidade, double preco){
        super("Padaria", descricao, marca, quantidade, preco);
    }
}
