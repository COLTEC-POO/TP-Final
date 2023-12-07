import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private Connection databaseConnection;

    private static Conexao conexao = null;
    private static final String DB_URI = "jdbc:mysql://localhost:3306/mercearia";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private Conexao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //abrindo a conexão
            this.databaseConnection = DriverManager.getConnection(DB_URI, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver não carregado: " + e);
        } catch (SQLException e) {
            System.err.println("Conexão não realizada: " + e);
        }
    }

    public static Connection getConnection() {

        if (conexao == null)
            conexao = new Conexao();

        return conexao.databaseConnection;
    }
}
