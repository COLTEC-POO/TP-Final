import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends DataAccessObject<Usuario> {
    private Connection conexao;

    public UsuarioDAO() {
        this.conexao = Conexao.getConnection();
    }

    @Override
    public List<Usuario> findAll() {
        //UsuarioDAO clienteDAO = new UsuarioDAO();

        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM usuarios";

        try {
            PreparedStatement ps = this.conexao.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("idUsuarios");
                String nome = resultSet.getString("Nome");
                String posicao = resultSet.getString("Posicao");

                Usuario atual;

                if (posicao.equals("Gerente")) {
                    atual = new Gerente(id, nome);
                } else if (posicao.equals("Atendente")) {
                    atual = new Atendente(id, nome);
                } else{
                    atual = new Cliente(id, nome);
                }

                usuarios.add(atual);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter lista de contas: " + ex);
        }

        return usuarios;
    }

    @Override
    public Usuario findById(int id) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = null;
        String query = "SELECT * FROM usuarios WHERE idUsuarios = ?";

        try {
            PreparedStatement ps = this.conexao.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                String nome = resultSet.getString("Nome");
                String senha = resultSet.getString("Senha");
                String posicao = resultSet.getString("posicao");

                if (posicao.equals("Atendente")){
                    usuario = new Atendente(id, nome, posicao);
                } else if (posicao.equals("Gerente")) {
                    usuario = new Gerente(id, nome, posicao);
                }else if (posicao.equals("Cliente")) {
                    usuario = new Cliente(id, nome, posicao);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao obter usuário: " + ex);
        }

        return usuario;
    }

    public boolean confereCadastro(int id,String senha){
        String query = "SELECT Senha FROM usuarios WHERE idUsuarios = ?";

        try {
            PreparedStatement ps = this.conexao.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                String Senha = resultSet.getString("senha");

                if (Senha.equals(senha)){
                    return true;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao obter operacao: " + e);
        }

        return false;
    }

    public String findByCargo(int id){
        String query = "SELECT posicao FROM usuarios WHERE idUsuarios = ?";
        String cargo="";
        try {
            PreparedStatement ps = this.conexao.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                cargo = resultSet.getString("posicao");

            }

        } catch (SQLException e) {
            System.err.println("Erro ao obter operacao: " + e);
        }

        return cargo;
    }
    public boolean conferePermissao(int id, String senha, String cargo){
        String query = "SELECT Senha, posicao FROM usuarios WHERE idUsuarios = ?";

        try {
            PreparedStatement ps = this.conexao.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                String Senha = resultSet.getString("Senha");
                String cargoUser = resultSet.getString("posicao");

                if (senha.equals(Senha)){
                    if(cargoUser.equals("Gerente") || cargoUser.equals("Atendente")) {
                        return true;
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao obter usuario: " + e);
        }

        return false;
    }
    @Override
    public Usuario insert(Usuario user) {

        Usuario usuarioInserido = user;
        String insertQuery = "INSERT INTO usuarios (nome, senha, posicao) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = this.conexao.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getNome());
            ps.setString(2, user.getSenha());
            ps.setString(3, user.getPosicao());


            int rowAffected = ps.executeUpdate();
            if (rowAffected == 1) {
                int generatedId = this.getInsertedId(ps);
                user.setId(generatedId);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir usuario: " + e.getMessage());
        }

        return usuarioInserido;
    }

    @Override
    public boolean update(Usuario user) {
        boolean resposta = false;
        String updateQuery = "UPDATE usuarios SET Senha = ? WHERE idUsuarios = ?";

        try {
            PreparedStatement ps = this.conexao.prepareStatement(updateQuery);
            ps.setString(1, user.getSenha());
            ps.setInt(2, user.getId());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                resposta = true;
                System.out.println("Senha atualizada com sucesso!");
            } else {
                System.out.println("Usuário não encontrado ou quantidade igual à existente.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
        }

        return resposta;
    }

    @Override
    public boolean delete(Usuario user) {

        boolean resposta = false;
        String deleteQuery = "DELETE FROM usuarios WHERE idUsuarios = ?";

        try {
            PreparedStatement ps = this.conexao.prepareStatement(deleteQuery);
            ps.setInt(1, user.getId());

            resposta = ps.execute();
            System.out.println("Usuário excluído com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao excluir usuário: " + e);
        }

        return resposta;
    }

    public String toString(Usuario user) {
        StringBuilder info = new StringBuilder();
        info.append("=================================================\n");

        info.append("ID do usuário: ").append(user.getId()).append("\n");
        info.append("Nome: ").append(user.getNome()).append("\n");
        info.append("Posição: ").append(user.getPosicao()).append("\n");
        info.append("=================================================\n");


        return info.toString();
    }

}


