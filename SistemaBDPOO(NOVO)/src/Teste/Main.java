package Teste;

import classes.BancoDados;
import classes.Coluna;
import classes.Tabela;
import classes.TabelaAssociativa;
import classes.Tipo;
import servico.BancoDadosServico;
import servico.ColunaServico;
import servico.TabelaServico;

import java.sql.SQLException;
import java.util.List;

public class Main {
    private static final BancoDadosServico bancoDadosServico = new BancoDadosServico();
    private static final TabelaServico tabelaServico = new TabelaServico();
    private static final ColunaServico colunaServico = new ColunaServico();

    public static void main(String[] args) {
        try {
            String nomeBanco = "MeuBancoCasemiro";
            criarBancoDados(nomeBanco);

            Tabela tabela1 = criarTabela(nomeBanco, "Tabela1");
            adicionarColuna(tabela1, "ID", Tipo.INT, 0, true, true, true);
            adicionarColuna(tabela1, "Nome", Tipo.VARCHAR, 50);
            tabelaServico.criarTabela(nomeBanco, tabela1);

            Tabela tabela2 = criarTabela(nomeBanco, "Tabela2");
            adicionarColuna(tabela2, "ID", Tipo.INT, 0, true, true, true);
            adicionarColuna(tabela2, "Descricao", Tipo.VARCHAR, 50);
            tabelaServico.criarTabela(nomeBanco, tabela2);

            TabelaAssociativa tabelaAssociativa = criarTabelaAssociativa(nomeBanco, "TabelaAssociativa");
            adicionarColuna(tabelaAssociativa, "Tabela1_ID", Tipo.INT, 0);
            adicionarColuna(tabelaAssociativa, "Tabela2_ID", Tipo.INT, 0);
            tabelaAssociativa.adicionarRelacao("Tabela1_ID", "Tabela1", "ID");
            tabelaAssociativa.adicionarRelacao("Tabela2_ID", "Tabela2", "ID");
            tabelaServico.criarTabela(nomeBanco, tabelaAssociativa);

            listarTabelas(nomeBanco);
            listarColunas(nomeBanco, "TabelaAssociativa");

            System.out.println("Dados adicionados!");
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }

    private static void criarBancoDados(String nomeBanco) throws SQLException {
        BancoDados bancoDados = new BancoDados(nomeBanco);
        bancoDadosServico.criarBancoDados(bancoDados);
        System.out.println("Banco de dados " + nomeBanco + " criado com sucesso!");
    }

    private static Tabela criarTabela(String nomeBanco, String nomeTabela) {
        Tabela tabela = new Tabela(nomeTabela);
        System.out.println("Tabela " + nomeTabela + " criada com sucesso no banco de dados " + nomeBanco);
        return tabela;
    }

    private static TabelaAssociativa criarTabelaAssociativa(String nomeBanco, String nomeTabela) {
        TabelaAssociativa tabelaAssociativa = new TabelaAssociativa(nomeTabela);
        System.out.println("Tabela associativa " + nomeTabela + " criada com sucesso no banco de dados " + nomeBanco);
        return tabelaAssociativa;
    }

    private static void adicionarColuna(Tabela tabela, String nomeColuna, Tipo tipo, int tamanho, boolean isPrimaria, boolean isAutoincre, boolean isNotnull) {
        Coluna coluna = new Coluna(nomeColuna, tipo);
        coluna.setTamanho(tamanho);
        coluna.setPrimaria(isPrimaria);
        coluna.setAutoincre(isAutoincre);
        coluna.setNotnull(isNotnull);
        tabela.adicionarColuna(coluna);
        System.out.println("Coluna " + nomeColuna + " adicionada com sucesso à tabela " + tabela.getNome());
    }

    private static void adicionarColuna(Tabela tabela, String nomeColuna, Tipo tipo, int tamanho) {
        adicionarColuna(tabela, nomeColuna, tipo, tamanho, false, false, false);
    }

    private static void listarColunas(String nomeBanco, String nomeTabela) throws SQLException {
        System.out.println("Colunas da tabela " + nomeTabela + " do banco de dados " + nomeBanco + ":");
        for (Coluna<?> coluna : colunaServico.listarColunas(nomeTabela)) {
            System.out.println("- " + coluna.getNome() + " (" + coluna.getTipo() + ")");
        }
    }

    private static void listarTabelas(String nomeBanco) throws SQLException {
        System.out.println("Tabelas do banco de dados " + nomeBanco + ":");
        List<Tabela> tabelas = tabelaServico.listarTabelas(nomeBanco);
        for (Tabela tabela : tabelas) {
            System.out.println("- " + tabela.getNome());
        }
    }
}
