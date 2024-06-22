package banco;

import java.sql.SQLException;
import java.util.List;
import classes.BancoDados;
// iNTERFACE PARA GERENCIAR O BANCO, TA FUNCIONANDO POR ENQUANTO '-'
public interface BancoDadosDAO {
	public void criarBancoDados(BancoDados bancoDados) throws SQLException;
	public void removerBancoDados(String nomeBanco) throws SQLException;
    List<BancoDados> listarBancosDados() throws SQLException;
}
