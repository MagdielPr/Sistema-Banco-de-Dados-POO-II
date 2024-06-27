package classes;

import java.util.ArrayList;
import java.util.List;

public class Tabela {
    private String nome;
    private List<Coluna> colunas;
    private List<ColunaPK> chavePrimaria;
    private List<ColunaEK> chaveEstrangeira;
    

    public Tabela(String nome) {
        this.nome = nome;
        this.colunas = new ArrayList<>();
        this.chavePrimaria = new ArrayList<>();
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
        if (coluna.isChavePrimaria()) {
            this.chavePrimaria.add(coluna);
        }
    }
    public List<ColunaPK> getPrimaryKeys() {
        return chavePrimaria;
    }
    
    public List<ColunaEK> getExternalKeys() {
        return chaveEstrangeira;
    }

    public void removerColuna(Coluna coluna) {
        this.colunas.remove(coluna);
    }
}
