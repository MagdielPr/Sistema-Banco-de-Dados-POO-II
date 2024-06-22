package servico;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import banco.ColunaTabelaDAO;
import classes.Coluna;
import conexao.ConexaoBD;

public class ColunaServico implements ColunaTabelaDAO {
//criação, alteração e remoção de colunas
	
    @Override
    public void adicionarColuna(String nomeTabela, Coluna coluna) throws SQLException {
        String sql = "ALTER TABLE " + nomeTabela + " ADD " + coluna.getNome() + " " + coluna.getTipo();
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    @Override
    public void alterarColuna(String nomeTabela, Coluna coluna) throws SQLException {
        String sql = "ALTER TABLE " + nomeTabela + " MODIFY COLUMN " + coluna.getNome() + " " + coluna.getTipo();
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    @Override
    public void removerColuna(String nomeTabela, String nomeColuna) throws SQLException {
        String sql = "ALTER TABLE " + nomeTabela + " DROP COLUMN " + nomeColuna;
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    @Override
    public List<Coluna> listarColunas(String nomeTabela) throws SQLException {
        String sql = "SHOW COLUMNS FROM " + nomeTabela;
        List<Coluna> colunas = new ArrayList<>();
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nomeColuna = rs.getString("Field");
                String tipoColuna = rs.getString("Type");
                colunas.add(new Coluna(nomeColuna, tipoColuna));
            }
        }
        return colunas;
    }
}
