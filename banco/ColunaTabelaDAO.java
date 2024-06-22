package banco;

import java.sql.SQLException;
import java.util.List;
import classes.Coluna;
//iNTERFACE PARA GERENCIAR AS COLUNAS DE UMA TABELA DO BANCO, TA SAFE '-'

public interface ColunaTabelaDAO {
    public void adicionarColuna(String nomeTabela, Coluna coluna) throws SQLException;
    public void alterarColuna(String nomeTabela, Coluna coluna) throws SQLException;
    public void removerColuna(String nomeTabela, String nomeColuna) throws SQLException;
    List<Coluna> listarColunas(String nomeTabela) throws SQLException;
}
