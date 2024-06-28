package banco;

import java.sql.SQLException;
import java.util.List;

import classes.Coluna;
import classes.Tabela;

public interface TabelaDAO<T extends Tabela> {
    void criarTabela(T tabela) throws SQLException;
    void alterarTabela(T tabela, String operacao, Coluna coluna) throws SQLException;
    void removerTabela(String nomeTabela) throws SQLException;
    List<T> listarTabelas(String nomeBanco) throws SQLException;
}
