package globalSolution.dominio;

import java.util.ArrayList;

public interface RepositorioDesconto {
    void adicionarDesconto(Desconto desconto);
    ArrayList<Desconto> buscandoTodosDescontos();
    void atualizarDesconto(long idDesconto, Desconto desconto);
    void removerDesconto(long idDesconto);
    void fecharConexao();
}
