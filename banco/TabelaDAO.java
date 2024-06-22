package banco;

import java.sql.SQLException;
import java.util.List;
import classes.Tabela;
//iNTERFACE PARA GERENCIAR AS TABELAS DO BANCO, :)

public interface TabelaDAO {
    public void criarTabela(Tabela tabela) throws SQLException;
    public void alterarTabela(Tabela tabela) throws SQLException;
    public void removerTabela(String nomeTabela) throws SQLException;
    List<Tabela> listarTabelas(String nomeBanco) throws SQLException;
}
