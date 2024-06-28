package banco;

import java.sql.SQLException;
import java.util.List;

public interface BancoDadosDAO<T> {
    void criarBancoDados(T bancoDados) throws SQLException;
    void removerBancoDados(String nomeBanco) throws SQLException;
    List<T> listarBancosDados() throws SQLException;
}
