package Teste;

import classes.BancoDados;
import classes.Coluna;
import classes.Tabela;
import classes.Tipo;
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
            criarTabela("MeuBanco", "MinhaTabela");
            adicionarColuna("MeuBanco", "MinhaTabela", "Nome", Tipo.VARCHAR, 50);
            listarColunas("MeuBanco", "MinhaTabela");
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }

    private static void criarBancoDados(String nomeBanco) throws SQLException {
        BancoDados bancoDados = new BancoDados(nomeBanco);
        bancoDadosServico.criarBancoDados(bancoDados);
        System.out.println("Banco de dados " + nomeBanco + " criado com sucesso!");
    }

    private static void criarTabela(String nomeBanco, String nomeTabela) throws SQLException {
        Tabela tabela = new Tabela(nomeTabela);
        tabelaServico.criarTabela(tabela);
        System.out.println("Tabela " + nomeTabela + " criada com sucesso no banco de dados " + nomeBanco);
    }

    private static void adicionarColuna(String nomeBanco, String nomeTabela, String nomeColuna, Tipo tipo, int tamanho) throws SQLException {
        Coluna coluna = new Coluna(nomeColuna, tipo, tamanho, false,  false, false, false);
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
