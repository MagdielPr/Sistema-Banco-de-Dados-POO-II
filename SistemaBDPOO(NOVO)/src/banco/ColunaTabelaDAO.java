package banco;

import classes.Coluna;
import classes.Tipo;
import java.sql.SQLException;
import java.util.List;

public interface ColunaTabelaDAO<T extends Coluna<? extends Tipo>> {
    void adicionarColuna(String nomeTabela, T coluna) throws SQLException;
    void alterarColuna(String nomeTabela, T coluna) throws SQLException;
    void removerColuna(String nomeTabela, String nomeColuna) throws SQLException;
    List<T> listarColunas(String nomeTabela) throws SQLException;
}