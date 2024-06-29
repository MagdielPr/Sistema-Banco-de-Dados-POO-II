package classes;

import java.util.ArrayList;
import java.util.List;

public class BancoDados {
    private String nome;
    private List<Tabela> tabelas;

    public BancoDados(String nome) {
        this.nome = nome;
        this.tabelas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Tabela> getTabelas() {
        return tabelas;
    }

    public void adicionarTabela(Tabela tabela) {
        this.tabelas.add(tabela);
    }

    public void removerTabela(Tabela tabela) {
        this.tabelas.remove(tabela);
    }
}