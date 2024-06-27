package servico;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import banco.TabelaDAO;
import classes.ChaveFK;
import classes.Coluna;
import classes.Tabela;
import conexao.ConexaoBD;
import conexao.MySqlConfig;
import conexao.EnumConexao;

public class TabelaServico implements TabelaDAO {
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
        for (Coluna coluna : tabela.getColunas()) {
            String definicaoColuna = coluna.getNome() + " " + coluna.getTipo();
            if (coluna.isChavePrimaria()) {
                chavePrimaria.add(coluna.getNome());
                definicaoColuna += " PRIMARY KEY";
            }
            colunaDefinicoes.add(definicaoColuna);
        }
        sql.append(String.join(", ", colunaDefinicoes));
        if (!chavePrimaria.isEmpty()) {
            sql.append(", PRIMARY KEY (").append(String.join(", ", chavePrimaria)).append(")");
        }
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
                break;
            case "DROP":
                sql = "ALTER TABLE " + tabela.getNome() + " DROP COLUMN " + coluna.getNome();
                break;
            case "MODIFY":
                sql = "ALTER TABLE " + tabela.getNome() + " MODIFY COLUMN " + coluna.getNome() + " " + coluna.getTipo();
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
                     " ADD CONSTRAINT " + chaveEstrangeira.getNome() +
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
}
