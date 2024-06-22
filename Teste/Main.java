package Teste;
import classes.BancoDados;
import classes.ChaveFK;
import classes.Coluna;
import classes.Tabela;
import servico.BancoDadosServico;
import servico.ColunaServico;
import servico.TabelaServico;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final BancoDadosServico bancoDadosServico = new BancoDadosServico();
    private static final TabelaServico tabelaServico = new TabelaServico();
    private static final ColunaServico colunaServico = new ColunaServico();

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            try {
                processarOpcao(opcao);
            } catch (SQLException e) {
                System.out.println("Ocorreu um erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("Escolha uma opção:");
        System.out.println("1. Criar banco de dados");
        System.out.println("2. Remover banco de dados");
        System.out.println("3. Listar bancos de dados");
        System.out.println("4. Criar tabela");
        System.out.println("5. Alterar tabela");
        System.out.println("6. Remover tabela");
        System.out.println("7. Listar tabelas de um banco de dados");
        System.out.println("8. Adicionar coluna a uma tabela");
        System.out.println("9. Alterar coluna de uma tabela");
        System.out.println("10. Remover coluna de uma tabela");
        System.out.println("11. Listar colunas de uma tabela");
        System.out.println("12. Adicionar chave estrangeira a uma tabela");
        System.out.println("0. Sair");
    }

    private static void processarOpcao(int opcao) throws SQLException {
        switch (opcao) {
            case 1:
                criarBancoDados();
                break;
            case 2:
                removerBancoDados();
                break;
            case 3:
                listarBancosDados();
                break;
            case 4:
                criarTabela();
                break;
            case 5:
                alterarTabela();
                break;
            case 6:
                removerTabela();
                break;
            case 7:
                listarTabelasDeBancoDados();
                break;
            case 8:
                adicionarColuna();
                break;
            case 9:
                alterarColuna();
                break;
            case 10:
                removerColuna();
                break;
            case 11:
                listarColunas();
                break;
            case 12:
                adicionarChaveEstrangeira();
                break;
            case 0:
                System.out.println("Encerrando o programa...");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void criarBancoDados() throws SQLException {
        System.out.print("Digite o nome do banco de dados: ");
        String nomeBanco = scanner.nextLine();
        BancoDados bancoDados = new BancoDados(nomeBanco);
        bancoDadosServico.criarBancoDados(bancoDados);
        System.out.println("Banco de dados " + nomeBanco + " criado com sucesso!");
    }

    private static void removerBancoDados() throws SQLException {
        System.out.print("Digite o nome do banco de dados a ser removido: ");
        String nomeBanco = scanner.nextLine();
        bancoDadosServico.removerBancoDados(nomeBanco);
        System.out.println("Banco de dados " + nomeBanco + " removido com sucesso!");
    }

    private static void listarBancosDados() throws SQLException {
        List<BancoDados> bancosDados = bancoDadosServico.listarBancosDados();
        System.out.println("Bancos de dados:");
        for (BancoDados bancoDados : bancosDados) {
            System.out.println("- " + bancoDados.getNome());
        }
    }

    private static void criarTabela() throws SQLException {
        System.out.print("Digite o nome do banco de dados: ");
        String nomeBanco = scanner.nextLine();
        System.out.print("Digite o nome da tabela: ");
        String nomeTabela = scanner.nextLine();
        Tabela tabela = new Tabela(nomeTabela);
        System.out.print("Digite a definição das colunas separadas por vírgula (nome tipo, ...): ");
        String colunaDefinicoes = scanner.nextLine();
        String[] colunas = colunaDefinicoes.split(",");
        for (String coluna : colunas) {
            String[] definicao = coluna.trim().split(" ");
            String nomeColuna = definicao[0];
            String tipoColuna = definicao[1];
            tabela.adicionarColuna(new Coluna(nomeColuna, tipoColuna));
        }
        tabelaServico.criarTabela(tabela);
        System.out.println("Tabela " + nomeTabela + " criada com sucesso no banco de dados " + nomeBanco);
    }

    private static void alterarTabela() throws SQLException {
        System.out.print("Digite o nome do banco de dados: ");
        String nomeBanco = scanner.nextLine();
        System.out.print("Digite o nome da tabela: ");
        String nomeTabela = scanner.nextLine();
        Tabela tabela = new Tabela(nomeTabela);
        System.out.print("Digite a operação (ADD, DROP, MODIFY): ");
        String operacao = scanner.nextLine();
        System.out.print("Digite o nome da coluna: ");
        String nomeColuna = scanner.nextLine();
        System.out.print("Digite o tipo da coluna: ");
        String tipoColuna = scanner.nextLine();
        Coluna coluna = new Coluna(nomeColuna, tipoColuna);
        tabelaServico.alterarTabela(tabela, operacao, coluna);
        System.out.println("Tabela " + nomeTabela + " alterada com sucesso no banco de dados " + nomeBanco);
    }

    private static void removerTabela() throws SQLException {
        System.out.print("Digite o nome do banco de dados: ");
        String nomeBanco = scanner.nextLine();
        System.out.print("Digite o nome da tabela a ser removida: ");
        String nomeTabela = scanner.nextLine();
        tabelaServico.removerTabela(nomeTabela);
        System.out.println("Tabela " + nomeTabela + " removida com sucesso do banco de dados " + nomeBanco);
    }

    private static void listarTabelasDeBancoDados() throws SQLException {
        System.out.print("Digite o nome do banco de dados: ");
        String nomeBanco = scanner.nextLine();
        List<Tabela> tabelas = tabelaServico.listarTabelas(nomeBanco);
        System.out.println("Tabelas do banco de dados " + nomeBanco + ":");
        for (Tabela tabela : tabelas) {
            System.out.println("- " + tabela.getNome());
        }
    }

    private static void adicionarColuna() throws SQLException {
        System.out.print("Digite o nome do banco de dados: ");
        String nomeBanco = scanner.nextLine();
        System.out.print("Digite o nome da tabela: ");
        String nomeTabela = scanner.nextLine();
        System.out.print("Digite o nome da coluna: ");
        String nomeColuna = scanner.nextLine();
        System.out.print("Digite o tipo da coluna: ");
        String tipoColuna = scanner.nextLine();
        Coluna coluna = new Coluna(nomeColuna, tipoColuna);
        colunaServico.adicionarColuna(nomeTabela, coluna);
        System.out.println("Coluna " + nomeColuna + " adicionada com sucesso à tabela " + nomeTabela + " do banco de dados " + nomeBanco);
    }

    private static void alterarColuna() throws SQLException {
        System.out.print("Digite o nome do banco de dados: ");
        String nomeBanco = scanner.nextLine();
        System.out.print("Digite o nome da tabela: ");
        String nomeTabela = scanner.nextLine();
        System.out.print("Digite o nome da coluna a ser alterada: ");
        String nomeColuna = scanner.nextLine();
        System.out.print("Digite o novo tipo da coluna: ");
        String novoTipoColuna = scanner.nextLine();
        Coluna coluna = new Coluna(nomeColuna, novoTipoColuna);
        colunaServico.alterarColuna(nomeTabela, coluna);
        System.out.println("Coluna " + nomeColuna + " alterada com sucesso na tabela " + nomeTabela + " do banco de dados " + nomeBanco);
    }

    private static void removerColuna() throws SQLException {
        System.out.print("Digite o nome do banco de dados: ");
        String nomeBanco = scanner.nextLine();
        System.out.print("Digite o nome da tabela: ");
        String nomeTabela = scanner.nextLine();
        System.out.print("Digite o nome da coluna a ser removida: ");
        String nomeColuna = scanner.nextLine();
        colunaServico.removerColuna(nomeTabela, nomeColuna);
        System.out.println("Coluna " + nomeColuna + " removida com sucesso da tabela " + nomeTabela + " do banco de dados " + nomeBanco);
    }

    private static void listarColunas() throws SQLException {
        System.out.print("Digite o nome do banco de dados: ");
        String nomeBanco = scanner.nextLine();
        System.out.print("Digite o nome da tabela: ");
        String nomeTabela = scanner.nextLine();
        List<Coluna> colunas = colunaServico.listarColunas(nomeTabela);
        System.out.println("Colunas da tabela " + nomeTabela + " do banco de dados " + nomeBanco + ":");
        for (Coluna coluna : colunas) {
            System.out.println("- " + coluna.getNome() + " (" + coluna.getTipo() + ")");
        }
    }

    private static void adicionarChaveEstrangeira() throws SQLException {
        System.out.print("Digite o nome do banco de dados: ");
        String nomeBanco = scanner.nextLine();
        System.out.print("Digite o nome da tabela: ");
        String nomeTabela = scanner.nextLine();
        System.out.print("Digite o nome da chave estrangeira: ");
        String nomeChaveEstrangeira = scanner.nextLine();
        System.out.print("Digite o tipo da chave estrangeira: ");
        String tipoChaveEstrangeira = scanner.nextLine();
        System.out.print("Digite o nome da tabela referenciada: ");
        String tabelaReferenciada = scanner.nextLine();
        System.out.print("Digite o nome da coluna referenciada: ");
        String colunaReferenciada = scanner.nextLine();
        ChaveFK chaveEstrangeira = new ChaveFK(nomeChaveEstrangeira, tipoChaveEstrangeira, tabelaReferenciada, colunaReferenciada);
        tabelaServico.adicionarChaveEstrangeira(nomeTabela, chaveEstrangeira);
        System.out.println("Chave estrangeira " + nomeChaveEstrangeira + " adicionada com sucesso à tabela " + nomeTabela + " do banco de dados " + nomeBanco);
    }
}