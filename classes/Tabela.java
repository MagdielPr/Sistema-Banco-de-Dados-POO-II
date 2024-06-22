package classes;

import java.util.ArrayList;
import java.util.List;

public class Tabela {
    private String nome;
    private List<Coluna> colunas;
    private List<Coluna> chavePrimaria;//pode ser null
    

    public Tabela(String nome) {
        this.nome = nome;
        this.colunas = new ArrayList<>();
        this.chavePrimaria = new ArrayList<>();
    }
    
    public void setChavePrimaria(List<Coluna> colunas) {
        this.chavePrimaria = colunas;
        for (Coluna coluna : colunas) {
            coluna.setChavePrimaria(true);
        }
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Coluna> getColunas() {
        return colunas;
    }

    public void adicionarColuna(Coluna coluna) {
        this.colunas.add(coluna);
        if (coluna.isChavePrimaria()) {
            this.chavePrimaria.add(coluna);
        }
    }
    public List<Coluna> getPrimaryKeys() {
        return chavePrimaria;
    }

    public void removerColuna(Coluna coluna) {
        this.colunas.remove(coluna);
    }
}
