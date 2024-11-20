package globalSolution.dominio;

import java.util.List;

public interface RepositorioApartamento {
    void adicionarApartamento(Apartamento apartamento);
    void atualizarApartamento(long idApartamento, Apartamento apartamento);
    void deletarApartamento(long idApartamento);
    List<Apartamento> buscarTodosApartamentos();
    Apartamento buscarApartamentoPorID(long idApartamento);
    List<ConsumoMorador> buscarDadosConsumoEnergiaTotal();
    List<ConsumoMorador> buscarTop3MenoresConsumos();
    void fecharConexao();

}
