package classes;

import java.util.ArrayList;
import java.util.List;

public class Tabela {
    private String nome;
    private List<Coluna> colunas;
    private List<Coluna> chavePrimaria;
    private List<ChaveFK> chaveEstrangeira;

    public Tabela(String nome) {
        this.nome = nome;
        this.colunas = new ArrayList<>();
        this.chavePrimaria = new ArrayList<>();
        this.chaveEstrangeira = new ArrayList<>();
    }
    
    public void setChavePrimaria(List<Coluna> colunas) {
        this.chavePrimaria = colunas;
        for (Coluna coluna : colunas) {
            coluna.setPrimaria(true);
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
        if (coluna.isPrimaria()) {
            this.chavePrimaria.add(coluna);
        }
    }

    public List<Coluna> getChavePrimaria() {
        return chavePrimaria;
    }

    public List<ChaveFK> getChaveEstrangeira() {
        return chaveEstrangeira;
    }

    public void adicionarChaveEstrangeira(ChaveFK chaveEstrangeira) {
        this.chaveEstrangeira.add(chaveEstrangeira);
    }

    public void removerColuna(Coluna coluna) {
        this.colunas.remove(coluna);
    }
}
