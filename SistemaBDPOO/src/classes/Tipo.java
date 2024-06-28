package classes;

public enum Tipo {
    VARCHAR, INT, DATE, BOOLEAN;

    public static Tipo fromString(String tipo) {
        switch (tipo.toUpperCase()) {
            case "VARCHAR":
                return VARCHAR;
            case "INT":
                return INT;
            case "DATE":
                return DATE;
            case "BOOLEAN":
                return BOOLEAN;
            default:
                throw new IllegalArgumentException("Tipo desconhecido: " + tipo);
        }
    }
}
