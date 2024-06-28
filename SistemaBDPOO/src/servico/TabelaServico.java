package servico;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import banco.TabelaDAO;
import classes.ChaveFK;
import classes.ChavePK;
import classes.Coluna;
import classes.Tabela;
import conexao.ConexaoBD;
import conexao.MySqlConfig;
import conexao.EnumConexao;

public class TabelaServico implements TabelaDAO<Tabela> {
    private ConexaoBD<MySqlConfig> conexaoBD;

    public TabelaServico() {
        MySqlConfig config = new MySqlConfig("localhost", 3306, EnumConexao.SQLCONNECTION, "root", "1234");
        this.conexaoBD = new ConexaoBD<>(config);
    }

    @Override
    public void criarTabela(Tabela tabela) throws SQLException {
        StringBuilder sql = new StringBuilder("CREATE TABLE " + tabela.getNome() + " (");
        List<String> colunaDefinicoes = new ArrayList<>();
        List<String> chavePrimaria = new ArrayList<>();
        List<String> chavesEstrangeiras = new ArrayList<>();

        for (Coluna coluna : tabela.getColunas()) {
            String definicaoColuna = coluna.getNome() + " " + coluna.getTipo();
            if (coluna.getTamanho() > 0) {
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

        if (!chavePrimaria.isEmpty()) {
            colunaDefinicoes.add("PRIMARY KEY (" + String.join(", ", chavePrimaria) + ")");
        }

        for (ChaveFK fk : tabela.getChavesEstrangeiras()) {
            String definicaoFK = "FOREIGN KEY (" + fk.getNome() + ") REFERENCES " + fk.getReferenciaTabela() + "(" + fk.getReferenciaColuna() + ")";
            chavesEstrangeiras.add(definicaoFK);
        }

        colunaDefinicoes.addAll(chavesEstrangeiras);
        sql.append(String.join(", ", colunaDefinicoes));
        sql.append(")");

        try (Connection conn = conexaoBD.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql.toString());
        }
    }

    @Override
    public void alterarTabela(Tabela tabela, String operacao, Coluna coluna) throws SQLException {
        String sql;
        switch (operacao) {
            case "ADD":
                sql = "ALTER TABLE " + tabela.getNome() + " ADD COLUMN " + coluna.getNome() + " " + coluna.getTipo();
                if (coluna.getTamanho() > 0) {
                    sql += "(" + coluna.getTamanho() + ")";
                }
                break;
            case "DROP":
                sql = "ALTER TABLE " + tabela.getNome() + " DROP COLUMN " + coluna.getNome();
                break;
            case "MODIFY":
                sql = "ALTER TABLE " + tabela.getNome() + " MODIFY COLUMN " + coluna.getNome() + " " + coluna.getTipo();
                if (coluna.getTamanho() > 0) {
                    sql += "(" + coluna.getTamanho() + ")";
                }
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
    public void removerTabela(String nomeTabela) throws SQLException {
        String sql = "DROP TABLE " + nomeTabela;
        try (Connection conn = conexaoBD.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    public void adicionarChaveEstrangeira(String nomeTabela, ChaveFK chaveEstrangeira) throws SQLException {
        String sql = "ALTER TABLE " + nomeTabela +
                     " ADD CONSTRAINT fk_" + chaveEstrangeira.getNome() +
                     " FOREIGN KEY (" + chaveEstrangeira.getNome() + ") " +
                     " REFERENCES " + chaveEstrangeira.getReferenciaTabela() + "(" + chaveEstrangeira.getReferenciaColuna() + ")";

        try (Connection conn = conexaoBD.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    @Override
    public List<Tabela> listarTabelas(String nomeBanco) throws SQLException {
        String sql = "SHOW TABLES";
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

    public void criarTabelaAssociativa(String nomeTabela, Coluna coluna1, Coluna coluna2) throws SQLException {
        Tabela tabela = new Tabela(nomeTabela);
        tabela.adicionarColuna(coluna1);
        tabela.adicionarColuna(coluna2);
        criarTabela(tabela);
        adicionarChaveEstrangeira(nomeTabela, new ChaveFK(coluna1.getNome(), coluna1.getTipo(), coluna1.getReferenciaTabela(), coluna1.getReferenciaColuna()));
        adicionarChaveEstrangeira(nomeTabela, new ChaveFK(coluna2.getNome(), coluna2.getTipo(), coluna2.getReferenciaTabela(), coluna2.getReferenciaColuna()));
    }
}
