import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RelatorioDAO extends DataAccessObject<Relatorio> {
    private Connection conexao;

    public RelatorioDAO() {
        this.conexao = Conexao.getConnection();
    }


    @Override
    public List<Relatorio> findAll() {
        //RelatorioDAO relatorio = new RelatorioDAO();

        List<Relatorio> relatorios = new ArrayList<>();
        String query = "SELECT * FROM relatorio";

        try {
            PreparedStatement ps = this.conexao.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            //RELATORIO - id, data, idProduto, idUsuario, modificacao, quantidade, valorTotal
            while (resultSet.next()) { //pega todas as colunas do banco
                int idRelatorio = resultSet.getInt("id");
                Date data = resultSet.getDate("data");
                int idProduto = resultSet.getInt("idProduto");
                int idUsuario = resultSet.getInt("idUsuario");
                String modificacao = resultSet.getString("tipoMovimentacao");
                int quantidade = resultSet.getInt("quantidade");
                double valorTotal = resultSet.getDouble("receita");

                //inicializa a variavel que guarda as consultas
                Relatorio atual = new Relatorio();

                if (modificacao != null){
                    atual = new Relatorio(idRelatorio, data, idProduto, idUsuario, modificacao, quantidade, valorTotal);
                }

                //adiciona a arraylist
                relatorios.add(atual);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter lista de produtos por tipo: " + ex);
        }

        return relatorios;
    }


    @Override
    public Relatorio findById(int id) {
        return null;
    }

    public List<Relatorio> findAllIdProduto(int id) {
        //RelatorioDAO relatorio = new RelatorioDAO();

        List<Relatorio> relatorios = new ArrayList<>();
        String query = "SELECT * FROM relatorio";

        try {
            PreparedStatement ps = this.conexao.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            //RELATORIO - id, data, idProduto, idUsuario, modificacao, quantidade, valorTotal
            while (resultSet.next()) { //pega todas as colunas do banco
                int idRelatorio = resultSet.getInt("id");
                Date data = resultSet.getDate("data");
                int idProduto = resultSet.getInt("idProduto");
                int idUsuario = resultSet.getInt("idUsuario");
                String modificacao = resultSet.getString("tipoMovimentacao");
                int quantidade = resultSet.getInt("quantidade");
                double valorTotal = resultSet.getDouble("receita");

                //inicializa a variavel que guarda as consultas
                Relatorio atual;

                if (id==idProduto) {
                    atual = new Relatorio(idRelatorio, data, idProduto, idUsuario, modificacao, quantidade, valorTotal);
                    relatorios.add(atual);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter lista de produtos por tipo: " + ex);
        }

        return relatorios;
    }
    public List<Relatorio> findAllIdUsuario(int id) {
        //RelatorioDAO relatorio = new RelatorioDAO();

        List<Relatorio> relatorios = new ArrayList<>();
        String query = "SELECT * FROM relatorio";

        try {
            PreparedStatement ps = this.conexao.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            //RELATORIO - id, data, idProduto, idUsuario, modificacao, quantidade, valorTotal
            while (resultSet.next()) { //pega todas as colunas do banco
                int idRelatorio = resultSet.getInt("id");
                Date data = resultSet.getDate("data");
                int idProduto = resultSet.getInt("idProduto");
                int idUsuario = resultSet.getInt("idUsuario");
                String modificacao = resultSet.getString("tipoMovimentacao");
                int quantidade = resultSet.getInt("quantidade");
                double valorTotal = resultSet.getDouble("receita");

                //inicializa a variavel que guarda as consultas
                Relatorio atual;


                if (id==idUsuario) {
                    atual = new Relatorio(idRelatorio, data, idProduto, idUsuario, modificacao, quantidade, valorTotal);
                    relatorios.add(atual);
                }
                //adiciona a arraylist
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter lista de produtos por tipo: " + ex);
        }

        return relatorios;
    }

    @Override
    /*
    public Relatorio insert(Relatorio rel) {
        Relatorio operacaoInserida = null;
        String insertQuery = "INSERT INTO relatorio (data, idProduto, idUsuario, tipoMovimentacao, quantidade, receita) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            java.sql.Date sqlDate = new java.sql.Date(rel.getData().getTime());

            PreparedStatement ps = this.conexao.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setDate(1, sqlDate);
            ps.setInt(2, rel.getIdProduto());
            ps.setInt(3, rel.getIdUsuario());
            ps.setString(4, rel.getTipoMovimentacao() + "");
            ps.setInt(5, rel.getQuantidade());
            ps.setDouble(6, rel.getReceita());

            int rowAffected = ps.executeUpdate();
            if (rowAffected == 1) {
                int generatedId = this.getInsertedId(ps);
                rel.setId(generatedId);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados no relatório: " + e.getMessage());
        }

        return operacaoInserida;
    }

     */

    public Relatorio insert(Relatorio rel) {
        Relatorio operacaoInserida = null;
        String insertQuery = "INSERT INTO relatorio (data, idProduto, idUsuario, tipoMovimentacao, quantidade, receita) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            java.sql.Date sqlDate = new java.sql.Date(rel.getData().getTime());

            PreparedStatement ps = this.conexao.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setDate(1, sqlDate);
            ps.setInt(2, rel.getIdProduto());
            ps.setInt(3, rel.getIdUsuario());
            ps.setString(4, rel.getTipoMovimentacao());
            ps.setInt(5, rel.getQuantidade());
            ps.setDouble(6, rel.getReceita());

            int rowAffected = ps.executeUpdate();

            if (rowAffected == 1) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    rel.setId(generatedId);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados no relatório: " + e.getMessage());
        }

        return operacaoInserida;
    }


    @Override
    public boolean update(Relatorio obj) {
        return false;
    }

    @Override
    public boolean delete(Relatorio rel) {
        boolean resposta = false;
        String deleteQuery = "DELETE FROM relatorio WHERE id = ?";

        try {
            PreparedStatement ps = this.conexao.prepareStatement(deleteQuery);
            ps.setInt(1, rel.getId());

            resposta = ps.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir linha de relatório selecionada: " + e);
        }

        return resposta;
    }

    public String toString(Relatorio rel) {
        StringBuilder info = new StringBuilder();
        info.append("=================================================\n");


        info.append("ID: ").append(rel.getId()).append("\n");
        info.append("Data: ").append(rel.getData()).append("\n");
        info.append("ID do produto: ").append(rel.getIdProduto()).append("\n");
        info.append("ID do usuario: ").append(rel.getIdUsuario()).append("\n");
        info.append("Movimentação: ").append(rel.getTipoMovimentacao()).append("\n");
        info.append("Quantidade: ").append(rel.getQuantidade()).append("\n");
        info.append("Receita: ").append(rel.getReceita()).append("\n");
        info.append("=================================================\n");


        return info.toString();
    }
}
