package servico;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import banco.ColunaTabelaDAO;
import classes.Coluna;
import classes.Tipo;
import conexao.ConexaoBD;
import conexao.MySqlConfig;
import conexao.EnumConexao;

public class ColunaServico<T extends Coluna> implements ColunaTabelaDAO<T> {
    private ConexaoBD<MySqlConfig> conexaoBD;

    public ColunaServico() {
        MySqlConfig config = new MySqlConfig("localhost", 3306, EnumConexao.SQLCONNECTION, "root", "1234");
        this.conexaoBD = new ConexaoBD<>(config);
    }

    @Override
    public void adicionarColuna(String nomeTabela, T coluna) throws SQLException {
        String sql = "ALTER TABLE " + nomeTabela + " ADD " + coluna.getNome() + " " + coluna.getTipo();
        try (Connection conn = conexaoBD.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    @Override
    public void alterarColuna(String nomeTabela, T coluna) throws SQLException {
        String sql = "ALTER TABLE " + nomeTabela + " MODIFY COLUMN " + coluna.getNome() + " " + coluna.getTipo();
        try (Connection conn = conexaoBD.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    @Override
    public void removerColuna(String nomeTabela, String nomeColuna) throws SQLException {
        String sql = "ALTER TABLE " + nomeTabela + " DROP COLUMN " + nomeColuna;
        try (Connection conn = conexaoBD.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    @Override
    public List<T> listarColunas(String nomeTabela) throws SQLException {
        String sql = "SHOW COLUMNS FROM " + nomeTabela;
        List<T> colunas = new ArrayList<>();
        try (Connection conn = conexaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nomeColuna = rs.getString("Field");
                String tipoColuna = rs.getString("Type");

                // Aqui assumimos que T tem um construtor que aceita nome e tipo
                T coluna = (T) new Coluna(nomeColuna, Tipo.fromString(tipoColuna));
                colunas.add(coluna);
            }
        }
        return colunas;
    }
}
