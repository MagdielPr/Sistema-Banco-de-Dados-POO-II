package Teste;

import classes.BancoDados;
import classes.ChaveFK;
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
            
            // Criar tabela normal
            Tabela tabela = criarTabela(nomeBanco, "MinhaTabelaTeste");
            adicionarColuna(tabela, "Nome", Tipo.VARCHAR, 50, false, true, false, false);
            adicionarColuna(tabela, "Descricao", Tipo.VARCHAR, 50, false, false, false, false);
            adicionarColuna(tabela, "Idade", Tipo.INT, 12, false, false, false, false);
            tabelaServico.criarTabela(nomeBanco, tabela);

            // Criar tabela associativa
            TabelaAssociativa tabelaAssociativa = new TabelaAssociativa("TabelaAssociativa");
            adicionarColuna(tabelaAssociativa, "Coluna1", Tipo.INT, 0, true, true, false, false);
            adicionarColuna(tabelaAssociativa, "Coluna2", Tipo.INT, 0, true, true, false, false);
            tabelaAssociativa.adicionarRelacao("Coluna1", "OutraTabela", "OutraColuna");
            tabelaAssociativa.adicionarRelacao("Coluna2", "MaisOutraTabela", "MaisOutraColuna");
            tabelaServico.criarTabela(nomeBanco, tabelaAssociativa);

            listarTabelas(nomeBanco);
            System.out.println("Dados adicionados!");
            listarColunas(nomeBanco, "MinhaTabelaTeste");
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }

    private static void criarBancoDados(String nomeBanco) throws SQLException {
        BancoDados bancoDados = new BancoDados(nomeBanco);
        bancoDadosServico.criarBancoDados(bancoDados);
        System.out.println("Banco de dados " + nomeBanco + " criado com sucesso!");
    }

    private static Tabela criarTabela(String nomeBanco, String nomeTabela) throws SQLException {
        Tabela tabela = new Tabela(nomeTabela);
        System.out.println("Tabela " + nomeTabela + " criada com sucesso no banco de dados " + nomeBanco);
        return tabela;
    }

    private static void adicionarColuna(Tabela tabela, String nomeColuna, Tipo tipo, int tamanho, boolean primaria, boolean notnull, boolean unique, boolean autoincre) throws SQLException {
        Coluna coluna = new Coluna(nomeColuna, tipo);
        coluna.setTamanho(tamanho);
        coluna.setPrimaria(primaria);
        coluna.setNotnull(notnull);
        coluna.setUnique(unique);
        coluna.setAutoincre(autoincre);
        tabela.adicionarColuna(coluna);
        System.out.println("-----------------");
        System.out.println("Coluna " + nomeColuna + " adicionada com sucesso à tabela " + tabela.getNome());
        System.out.println("-----------------");
    }

    private static void listarColunas(String nomeBanco, String nomeTabela) throws SQLException {
        System.out.println("Colunas da tabela " + nomeTabela + " do banco de dados " + nomeBanco + ":");
        System.out.println("-----------------");
        for (Coluna coluna : colunaServico.listarColunas(nomeTabela)) {
            System.out.println("- " + coluna.getNome() + " (" + coluna.getTipo() + ")");
        }
        System.out.println("-----------------");
    }

    private static void listarTabelas(String nomeBanco) throws SQLException {
        System.out.println("Tabelas do banco de dados " + nomeBanco + ":");
        List<Tabela> tabelas = tabelaServico.listarTabelas(nomeBanco);
        System.out.println("-----------------");
        for (Tabela tabela : tabelas) {
            System.out.println("- " + tabela.getNome());
        }
        System.out.println("-----------------");
    }
}
