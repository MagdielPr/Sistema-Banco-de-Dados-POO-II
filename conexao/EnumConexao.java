package conexao;

public enum EnumConexao {
    SQLCONNECTION("sqlconnection"),
    TEST("test"),
    PRODUCTION("production");

    private final String name;

    EnumConexao(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}	