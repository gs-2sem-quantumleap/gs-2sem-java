package globalSolution.dominio;

import java.util.ArrayList;

public interface RepositorioVeiculo {

    void adicionarVeiculo(Veiculo veiculo);
    Veiculo buscarVeiculoPorId(Long id);
    ArrayList<Veiculo> listarVeiculo();
    void atualizarVeiculo(long idVeiculo, Veiculo veiculo);
    void removerVeiculo(Long idVeiculo);
    void fecharConexao();
}
