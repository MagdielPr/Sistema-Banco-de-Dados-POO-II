package classes;

import java.util.ArrayList;
import java.util.List;

public class Tabela {
    private String nome;
    private List<Coluna> colunas;
    private List<Coluna> chavePrimaria;
    private List<ChaveFK> chavesEstrangeiras;

    public Tabela(String nome) {
        this.nome = nome;
        this.colunas = new ArrayList<>();
        this.chavePrimaria = new ArrayList<>();
        this.chavesEstrangeiras = new ArrayList<>();
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
        if (coluna.isPrimaria()) {
            this.chavePrimaria.add(coluna);
        }
        if (coluna instanceof ChaveFK) {
            this.chavesEstrangeiras.add((ChaveFK) coluna);
        }
    }

    public List<Coluna> getChavePrimaria() {
        return chavePrimaria;
    }

    public List<ChaveFK> getChavesEstrangeiras() {
        return chavesEstrangeiras;
    }
}
