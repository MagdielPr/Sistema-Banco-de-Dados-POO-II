package banco;

import classes.Tabela;
import classes.Coluna;
import classes.Tipo;
import java.sql.SQLException;
import java.util.List;

public interface TabelaDAO<T extends Tabela<? extends Coluna<? extends Tipo>>> {
    void criarTabela(T tabela) throws SQLException;
    void alterarTabela(T tabela, String operacao, Coluna<? extends Tipo> coluna) throws SQLException;
    void removerTabela(String nomeTabela) throws SQLException;
    List<T> listarTabelas(String nomeBanco) throws SQLException;
}
