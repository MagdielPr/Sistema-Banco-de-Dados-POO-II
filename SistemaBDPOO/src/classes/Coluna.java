package classes;

public class Coluna {
    private String nome;
    private String tipo;
    private int tamanho;
    private boolean isPrimaria;
    private boolean isNotnull;
    private boolean isUnique;
    private boolean isAutoincre;

    public Coluna(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
        this.tamanho = 0;
        this.isPrimaria = false;
        this.isNotnull = false;
        this.isUnique = false;
        this.isAutoincre = false;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public boolean isPrimaria() {
        return isPrimaria;
    }

    public void setPrimaria(boolean primaria) {
        isPrimaria = primaria;
    }

    public boolean isNotnull() {
        return isNotnull;
    }

    public void setNotnull(boolean notnull) {
        isNotnull = notnull;
    }

    public boolean isUnique() {
        return isUnique;
    }

    public void setUnique(boolean unique) {
        isUnique = unique;
    }

    public boolean isAutoincre() {
        return isAutoincre;
    }

    public void setAutoincre(boolean autoincre) {
        isAutoincre = autoincre;
    }
}
