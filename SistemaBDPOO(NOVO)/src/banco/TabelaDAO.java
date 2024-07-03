package banco;

import classes.Coluna;
import classes.Tabela;

import java.sql.SQLException;
import java.util.List;

public interface TabelaDAO {
    void criarTabela(String nomeBanco, Tabela tabela) throws SQLException;
    void alterarTabela(String nomeBanco, Tabela tabela, String operacao, Coluna<?> coluna) throws SQLException;
    void removerTabela(String nomeBanco, String nomeTabela) throws SQLException;
    List<Tabela> listarTabelas(String nomeBanco) throws SQLException;
}
