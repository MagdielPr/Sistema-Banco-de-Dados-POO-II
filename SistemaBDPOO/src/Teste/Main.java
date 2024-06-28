package Teste;

import classes.BancoDados;
import classes.Coluna;
import classes.Tabela;
import classes.Tipo;
import classes.ChaveFK;
import servico.BancoDadosServico;
import servico.ColunaServico;
import servico.TabelaServico;

import java.sql.SQLException;

public class Main {
    private static final BancoDadosServico bancoDadosServico = new BancoDadosServico();
    private static final TabelaServico tabelaServico = new TabelaServico();
    private static final ColunaServico colunaServico = new ColunaServico();

    public static void main(String[] args) {
        try {
            criarBancoDados("MeuBanco");
            criarTabelaSimples("MeuBanco", "MinhaTabela");
            adicionarColuna("MeuBanco", "MinhaTabela", "Nome", Tipo.VARCHAR, 50);
            listarColunas("MeuBanco", "MinhaTabela");

            criarTabelaComChaveEstrangeira("MeuBanco", "MinhaTabela2");
            listarColunas("MeuBanco", "MinhaTabela2");

            criarTabelaAssociativa("MeuBanco", "TabelaAssociativa", "Tabela1", "Coluna1", "Tabela2", "Coluna2");
            listarColunas("MeuBanco", "TabelaAssociativa");
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }

    private static void criarBancoDados(String nomeBanco) throws SQLException {
        BancoDados bancoDados = new BancoDados(nomeBanco);
        bancoDadosServico.criarBancoDados(bancoDados);
        System.out.println("Banco de dados " + nomeBanco + " criado com sucesso!");
    }

    private static void criarTabelaSimples(String nomeBanco, String nomeTabela) throws SQLException {
        Tabela tabela = new Tabela(nomeTabela);
        tabela.addColuna(new Coluna("id", Tipo.INT, 0, true, true, true, true));
        tabela.addColuna(new Coluna("nome", Tipo.VARCHAR, 50, false, false, false, false));
        tabelaServico.criarTabela(tabela);
        System.out.println("Tabela " + nomeTabela + " criada com sucesso no banco de dados " + nomeBanco);
    }

    private static void criarTabelaComChaveEstrangeira(String nomeBanco, String nomeTabela) throws SQLException {
        Tabela tabela = new Tabela(nomeTabela);
        tabela.addColuna(new Coluna("id", Tipo.INT, 0, true, true, true, true));
        tabela.addColuna(new Coluna("id_ref", Tipo.INT, 0, false, false, false, false));
        tabela.addChaveEstrangeira(new ChaveFK("id_ref", "id", "MinhaTabela", "id"));
        tabelaServico.criarTabela(tabela);
        System.out.println("Tabela " + nomeTabela + " criada com chave estrangeira no banco de dados " + nomeBanco);
    }

    private static void criarTabelaAssociativa(String nomeBanco, String nomeTabela, String tabela1, String coluna1, String tabela2, String coluna2) throws SQLException {
        Tabela tabela = new Tabela(nomeTabela);
        tabela.addColuna(new Coluna("id_" + coluna1, Tipo.INT, 0, false, true, false, false));
        tabela.addColuna(new Coluna("id_" + coluna2, Tipo.INT, 0, false, true, false, false));
        tabela.addChaveEstrangeira(new ChaveFK("id_" + coluna1, coluna1, tabela1, coluna1));
        tabela.addChaveEstrangeira(new ChaveFK("id_" + coluna2, coluna2, tabela2, coluna2));
        tabelaServico.criarTabela(tabela);
        System.out.println("Tabela associativa " + nomeTabela + " criada com sucesso no banco de dados " + nomeBanco);
    }

    private static void adicionarColuna(String nomeBanco, String nomeTabela, String nomeColuna, Tipo tipo, int tamanho) throws SQLException {
        Coluna coluna = new Coluna(nomeColuna, tipo, tamanho, false, false, false, false);
        colunaServico.adicionarColuna(nomeTabela, coluna);
        System.out.println("Coluna " + nomeColuna + " adicionada com sucesso à tabela " + nomeTabela + " do banco de dados " + nomeBanco);
    }

    private static void listarColunas(String nomeBanco, String nomeTabela) throws SQLException {
        System.out.println("Colunas da tabela " + nomeTabela + " do banco de dados " + nomeBanco + ":");
        for (Coluna coluna : colunaServico.listarColunas(nomeTabela)) {
            System.out.println("- " + coluna.getNome() + " (" + coluna.getTipo() + ")");
        }
    }
}
