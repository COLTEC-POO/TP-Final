import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO extends DataAccessObject<Produto> {
    private Connection conexao;

    public ProdutoDAO() {
        this.conexao = Conexao.getConnection();
    }



    //lista todos os produtos
    @Override
    public List<Produto> findAll() {
        //ProdutoDAO produtoDAO = new ProdutoDAO();

        List<Produto> produtos = new ArrayList<>();
        String query = "SELECT * FROM produtos";

        try {
            PreparedStatement ps = this.conexao.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) { //pega todas as colunas do banco
                int id = resultSet.getInt("idProdutos");
                String tipoProduto = resultSet.getString("Tipo");
                String nome = resultSet.getString("Descricao");
                String marcaProduto = resultSet.getString("Marca");
                int quantidade = resultSet.getInt("Quantidade");
                double preco = resultSet.getInt("Preco");

                //inicializa a variavel que guarda as consultas
                Produto atual;

                if (tipoProduto.equals("Cosmeticos")) {
                    atual = new Cosmeticos(id, nome, marcaProduto, quantidade, preco);
                } else if (tipoProduto.equals("Padaria")) {
                    atual = new Padaria(id, nome, marcaProduto, quantidade, preco);
                } else{
                    atual = new Mercado(id, nome, marcaProduto, quantidade, preco);
                }

                //adiciona a arraylist
                produtos.add(atual);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter lista de produtos por tipo: " + ex);
        }

        return produtos;
    }

    //lista os produtos pela marca selecionada
    public List<Produto> findAllMarca(String marca) {
        ProdutoDAO produtoDAO = new ProdutoDAO();

        List<Produto> produtos = new ArrayList<>();
        String query = "SELECT * FROM produtos WHERE Marca = ?"; //coloquei o where Marca = ?

        try {
            PreparedStatement ps = this.conexao.prepareStatement(query);
            ps.setString(1, marca); //teste por causa da alteração
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) { //pega todas as colunas do banco
                int id = resultSet.getInt("idProdutos");
                String tipo = resultSet.getString("Tipo");
                String nome = resultSet.getString("Descricao");
                String marcaProduto = resultSet.getString("Marca");
                int quantidade = resultSet.getInt("Quantidade");
                double preco = resultSet.getInt("Preco");

                //inicializa a variavel que guarda as consultas
                Produto atual = null;

                //ve se a marca solicitada é igual a marca consultada
                if (marca.equals(marcaProduto)) {
                    //procura o tipo para criar o objeto
                    if(tipo.equals("Mercado")){
                        atual = new Mercado(id, nome, marcaProduto, quantidade, preco);
                    }else if(tipo.equals("Padaria")){
                        atual = new Padaria(id, nome, marcaProduto, quantidade, preco);
                    }else if(tipo.equals("Cosmeticos")){
                        atual = new Cosmeticos(id, nome, marcaProduto, quantidade, preco);
                    }
                }
                //adiciona a arraylist
                produtos.add(atual);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter lista de produtos pela marca: " + ex);
        }

        return produtos;
    }

    //procura o preco pelo id do produto
    public double findByIdPreco(int id){
        double preco = 0;
        String query = "SELECT Preco FROM produtos WHERE idProdutos = ?";

        try {
            PreparedStatement ps = this.conexao.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                preco = resultSet.getDouble("Preco");

            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter o preco " + ex);
        }

        return preco;
    }


    //lista os produtos pelo tipo de produto(cosmetico, mercado e padaria)
    public List<Produto> findAllTipo(String tipo){
        ProdutoDAO produtoDAO = new ProdutoDAO();

        List<Produto> produtos = new ArrayList<>();
        String query = "SELECT * FROM produtos WHERE Tipo = ?";

        try {
            PreparedStatement ps = this.conexao.prepareStatement(query);
            ps.setString(1, tipo);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) { //pega todas as colunas do banco
                int id = resultSet.getInt("idProdutos");
                String tipoProduto = resultSet.getString("Tipo");
                String nome = resultSet.getString("Descricao");
                String marcaProduto = resultSet.getString("Marca");
                int quantidade = resultSet.getInt("Quantidade");
                double preco = resultSet.getInt("Preco");

                //inicializa a variavel que guarda as consultas
                Produto atual = null;

                //ve se o tipo solicitado é igual a marca consultada
                if (tipo.equals(tipoProduto)) {
                    //procura o tipo para criar o objeto
                    if(tipo.equals("Mercado")){
                        atual = new Mercado(id, nome, marcaProduto, quantidade, preco);
                    }else if(tipo.equals("Padaria")){
                        atual = new Padaria(id, nome, marcaProduto, quantidade, preco);
                    }else if(tipo.equals("Cosmeticos")){
                        atual = new Cosmeticos(id, nome, marcaProduto, quantidade, preco);
                    }
                }
                //adiciona a arraylist
                produtos.add(atual);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter lista de produtos por tipo: " + ex);
        }

        return produtos;
    }


    public Produto findById(int id) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = null;
        String query = "SELECT * FROM produtos WHERE idProdutos = ?";

        try {
            PreparedStatement ps = this.conexao.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                String tipoProduto = resultSet.getString("Tipo");
                String nome = resultSet.getString("Descricao");
                String marcaProduto = resultSet.getString("Marca");
                int quantidade = resultSet.getInt("Quantidade");
                double preco = resultSet.getDouble("Preco");

                if (tipoProduto.equals("Mercado")){
                    produto = new Mercado(id,nome, marcaProduto, quantidade, preco);
                } else if (tipoProduto.equals("Cosmetico")) {
                    produto = new Cosmeticos(id,nome, marcaProduto, quantidade, preco);
                }else if (tipoProduto.equals("Padaria")) {
                    produto = new Padaria(id,nome, marcaProduto, quantidade, preco);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter produto: " + ex);
        }

        return produto;
    }

    public int findByQuantidade(int id){
        RelatorioDAO relatorioDAO = null;
        int quantidade = 0;
        String query = "SELECT Quantidade FROM produtos WHERE idProdutos = ?";

        try {
            PreparedStatement ps = this.conexao.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                quantidade = resultSet.getInt("Quantidade");

            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter a quantidade do produto: " + ex);
        }

        return quantidade;
    }
    @Override
    public Produto insert(Produto product) {
        Produto produtoInserido = null;
        String insertQuery = "INSERT INTO produtos (Tipo, Descricao, Marca, Quantidade, Preco) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = this.conexao.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getTipo());
            ps.setString(2, product.getDescricao());
            ps.setString(3, product.getMarca());
            ps.setInt(4, product.getQuantidade());
            ps.setDouble(5, product.getPreco());

            int rowAffected = ps.executeUpdate();
            if (rowAffected == 1) {
                int generatedId = this.getInsertedId(ps);
                product.setId(generatedId);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir o produto: " + e.getMessage());
        }
        return produtoInserido;
    }

    @Override
    public boolean update(Produto product) {
        RelatorioDAO relatorioDAO = null;

        boolean resposta = false;
        String updateQuery = "UPDATE produtos SET Quantidade = ? WHERE idProdutos = ?";

        try {
            PreparedStatement ps = this.conexao.prepareStatement(updateQuery);
            ps.setInt(1, product.getQuantidade());
            ps.setInt(2, product.getId());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                resposta = true;
                System.out.println("Produto atualizado com sucesso!");
            } else {
                System.out.println("Nenhum produto foi atualizado. Produto não encontrado ou quantidade igual à existente.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o produto: " + e);
        }

        return resposta;
    }

    @Override
    public boolean delete(Produto product) {

        boolean resposta = false;
        String deleteQuery = "DELETE FROM produtos WHERE idProdutos = ?";

        try {
            PreparedStatement ps = this.conexao.prepareStatement(deleteQuery);
            ps.setInt(1, product.getId());

            resposta = ps.execute();
            System.out.println("Produto excluído com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao excluir produto do estoque: " + e);
        }

        return resposta;
    }

    public String toString(Produto produto) {
        StringBuilder info = new StringBuilder();
        info.append("=================================================\n");


        info.append("ID do produto: ").append(produto.getId()).append("\n");
        info.append("Nome: ").append(produto.getDescricao()).append("\n");
        info.append("Tipo: ").append(produto.getTipo()).append("\n");
        info.append("Marca: ").append(produto.getMarca()).append("\n");
        info.append("Preço: ").append(produto.getPreco()).append("\n");
        info.append("Quantidade: ").append(produto.getQuantidade()).append("\n");
        info.append("=================================================\n");


        return info.toString();
    }


}
