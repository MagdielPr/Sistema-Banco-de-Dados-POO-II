package classes;

public class ColunaEK {
    private String nome;
    private Tipo tipo;
    private int tamanho;
    private boolean primaria;
    private boolean notnull;
    private boolean unique;
    private boolean autoincre;
    

    public ColunaEK(String nome, Tipo tipo, int tamanho, boolean pk, boolean nn, boolean uq, boolean ai) {
        this.nome = nome;
        this.tipo = tipo;
        this.tamanho = tamanho;
        this.primaria = pk;
        this.notnull = nn;
        this.unique = uq;
        this.autoincre = ai;
     }


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Tipo getTipo() {
		return tipo;
	}


	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}


	public int getTamanho() {
		return tamanho;
	}


	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}


	public boolean isPrimaria() {
		return primaria;
	}


	public void setPrimaria(boolean primaria) {
		this.primaria = primaria;
	}


	public boolean isNotnull() {
		return notnull;
	}


	public void setNotnull(boolean notnull) {
		this.notnull = notnull;
	}


	public boolean isUnique() {
		return unique;
	}


	public void setUnique(boolean unique) {
		this.unique = unique;
	}


	public boolean isAutoincre() {
		return autoincre;
	}


	public void setAutoincre(boolean autoincre) {
		this.autoincre = autoincre;
	}
    
}