package classes;

public class ChaveFK extends Coluna {
    private String referenciaTabela;
    private String referenciaColuna;

    public ChaveFK(String nome, String tipo, String referenciaTabela, String referenciaColuna) {
        super(nome, tipo);
        this.setChaveEstrangeira(true);
        this.referenciaTabela = referenciaTabela;
        this.referenciaColuna = referenciaColuna;
    }

    public String getReferenciaTabela() {
        return referenciaTabela;
    }

    public void setReferenciaTabela(String referenciaTabela) {
        this.referenciaTabela = referenciaTabela;
    }

    public String getReferenciaColuna() {
        return referenciaColuna;
    }

    public void setReferenciaColuna(String referenciaColuna) {
        this.referenciaColuna = referenciaColuna;
    }
}
