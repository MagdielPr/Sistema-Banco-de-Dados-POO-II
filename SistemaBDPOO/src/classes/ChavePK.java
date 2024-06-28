package classes;

public class ChavePK extends Coluna {

    public ChavePK(String nome, String tipo) {
        super(nome, tipo);
        this.setPrimaria(true);
    }
}
