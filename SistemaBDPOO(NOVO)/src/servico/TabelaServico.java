package servico;

import banco.TabelaDAO;
import classes.ChaveFK;
import classes.Coluna;
import classes.Tabela;
import conexao.ConexaoBD;
import conexao.EnumConexao;
import conexao.MySqlConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TabelaServico implements TabelaDAO {
    private ConexaoBD<MySqlConfig> conexaoBD;

    public TabelaServico() {
        MySqlConfig config = new MySqlConfig("localhost", 3306, EnumConexao.SQLCONNECTION, "root", "1234");
        this.conexaoBD = new ConexaoBD<>(config);
    }

    @Override
    public void criarTabela(String nomeBanco, Tabela tabela) throws SQLException {
        StringBuilder sql = new StringBuilder("CREATE TABLE " + nomeBanco + "." + tabela.getNome() + " (");
        List<String> colunaDefinicoes = new ArrayList<>();
        List<String> chavePrimaria = new ArrayList<>();
        for (Coluna coluna : tabela.getColunas()) {
            String definicaoColuna = coluna.getNome() + " " + coluna.getTipo();
            if ("VARCHAR".equals(coluna.getTipo().toString())) {
                definicaoColuna += "(" + coluna.getTamanho() + ")";
            }
            if (coluna.isPrimaria()) {
                chavePrimaria.add(coluna.getNome());
            }
            if (coluna.isNotnull()) {
                definicaoColuna += " NOT NULL";
            }
            if (coluna.isUnique()) {
                definicaoColuna += " UNIQUE";
            }
            if (coluna.isAutoincre()) {
                definicaoColuna += " AUTO_INCREMENT";
            }
            colunaDefinicoes.add(definicaoColuna);
        }
        sql.append(String.join(", ", colunaDefinicoes));
        if (!chavePrimaria.isEmpty()) {
            sql.append(", PRIMARY KEY (").append(String.join(", ", chavePrimaria)).append(")");
        }
        for (ChaveFK chaveFK : tabela.getChavesEstrangeiras()) {
            sql.append(", FOREIGN KEY (").append(chaveFK.getNome()).append(") REFERENCES ")
               .append(chaveFK.getReferenciaTabela()).append("(").append(chaveFK.getReferenciaColuna()).append(")");
        }
        sql.append(")");
        try (Connection conn = conexaoBD.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql.toString());
        }
    }

    @Override
    public void alterarTabela(String nomeBanco, Tabela tabela, String operacao, Coluna coluna) throws SQLException {
        String sql;
        switch (operacao) {
            case "ADD":
                sql = "ALTER TABLE " + nomeBanco + "." + tabela.getNome() + " ADD COLUMN " + coluna.getNome() + " " + coluna.getTipo();
                break;
            case "DROP":
                sql = "ALTER TABLE " + nomeBanco + "." + tabela.getNome() + " DROP COLUMN " + coluna.getNome();
                break;
            case "MODIFY":
                sql = "ALTER TABLE " + nomeBanco + "." + tabela.getNome() + " MODIFY COLUMN " + coluna.getNome() + " " + coluna.getTipo();
                break;
            default:
                throw new IllegalArgumentException("Operação inválida: " + operacao);
        }

        try (Connection conn = conexaoBD.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    @Override
    public void removerTabela(String nomeBanco, String nomeTabela) throws SQLException {
        String sql = "DROP TABLE " + nomeBanco + "." + nomeTabela;
        try (Connection conn = conexaoBD.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    @Override
    public List<Tabela> listarTabelas(String nomeBanco) throws SQLException {
        String sql = "SHOW TABLES FROM " + nomeBanco;
        List<Tabela> tabelas = new ArrayList<>();
        try (Connection conn = conexaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String nomeTabela = rs.getString(1);
                tabelas.add(new Tabela(nomeTabela));
            }
        }
        return tabelas;
    }
}
