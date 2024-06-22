package servico;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import banco.BancoDadosDAO;
import classes.BancoDados;
import conexao.ConexaoBD;

public class BancoDadosServico implements BancoDadosDAO {
//Criação e gerenciamento dos bancos criados pela conexão, que o usuario vai passar, preciso ajustar
	
    @Override
    public void criarBancoDados(BancoDados bancoDados) throws SQLException {
        String sql = "CREATE DATABASE " + bancoDados.getNome();
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    @Override
    public void removerBancoDados(String nomeBanco) throws SQLException {
        String sql = "DROP DATABASE " + nomeBanco;
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    @Override
    public List<BancoDados> listarBancosDados() throws SQLException {
        String sql = "SHOW DATABASES";
        List<BancoDados> bancosDados = new ArrayList<>();
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nomeBanco = rs.getString("Database");
                bancosDados.add(new BancoDados(nomeBanco));
            }
        }
        return bancosDados;
    }
}
