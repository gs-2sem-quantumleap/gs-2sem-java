package globalSolution.dominio;

import java.util.ArrayList;
import java.util.List;

public interface RepositorioContaDeEnergia {
    void adicionarConta(ContaDeEnergia contaDeEnergia);
    ContaDeEnergia buscarContaPorId(Long id);
    ArrayList<ContaDeEnergia> listarContas();
    void atualizarConta(long idConta, ContaDeEnergia contaDeEnergia);
    void removerConta(Long idConta);
    List<ContaDeEnergia> buscarContaPorCpf(String cpf);
    void fecharConexao();
}
