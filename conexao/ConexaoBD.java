package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

	// criarbanco(nome banco)
    // Dados da conexão do workbench senha 1234, lembrar de implementar para o usuário passar a conexão/usuario/senha/porta
    private static final String URL = "jdbc:mysql://localhost:3306/sqlconnection";
    private static final String BANCO = "";
    private static final String USUARIO = "root";
    private static final String SENHA = "1234";
    private static final String PORTA = "";
    
    // Método para pegar a conexão
    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conectado ao banco de dados com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            throw e;
        }
        return connection;
    }

    // Fechar a conexão
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexão fechada com sucesso!");
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }

    // Teste de conexão '-'
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = ConexaoBD.getConnection();
        } catch (SQLException e) {
            System.err.println("Erro durante o teste de conexão: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }
}
