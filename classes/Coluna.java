package classes;

public class Coluna {
    private String nome;
    private String tipo; // colocar enum/classe 4a5 tipos
    private boolean isChavePrimaria;
    private boolean isChaveEstrangeira;

    public Coluna(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
        this.isChavePrimaria = false;
        this.isChaveEstrangeira = false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isChavePrimaria() {
        return isChavePrimaria;
    }

    public void setChavePrimaria(boolean isChavePrimaria) {
        this.isChavePrimaria = isChavePrimaria;
    }

    public boolean isChaveEstrangeira() {
        return isChaveEstrangeira;
    }

    public void setChaveEstrangeira(boolean isChaveEstrangeira) {
        this.isChaveEstrangeira = isChaveEstrangeira;
    }
}
