package globalSolution.service;

import globalSolution.dominio.ContaDeEnergia;
import globalSolution.dominio.RepositorioContaDeEnergia;
import globalSolution.dominio.RepositorioVeiculo;
import globalSolution.dominio.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class ContaDeEnergiaService {
    private RepositorioContaDeEnergia repositorioContaDeEnergia;
    private RepositorioVeiculo repositorioVeiculo;

    public ContaDeEnergiaService(RepositorioContaDeEnergia repositorioContaDeEnergia, RepositorioVeiculo repositorioVeiculo) {
        this.repositorioContaDeEnergia = repositorioContaDeEnergia;
        this.repositorioVeiculo = repositorioVeiculo;
    }



    public void adicionarConta(ContaDeEnergia conta) {
        List<Veiculo> veiculos = repositorioVeiculo.buscarVeiculosPorApartamento(conta.getIdApartamento());
        conta.verificaCarro(veiculos, conta);
        repositorioContaDeEnergia.adicionarConta(conta);
        repositorioContaDeEnergia.fecharConexao();
    }

    public ContaDeEnergia buscarConta(long id) {
        ContaDeEnergia conta = repositorioContaDeEnergia.buscarContaPorId(id);
        repositorioContaDeEnergia.fecharConexao();
        return conta;
    }

    public ArrayList<ContaDeEnergia> buscarContas() {
        ArrayList<ContaDeEnergia> contas = repositorioContaDeEnergia.listarContas();
        repositorioContaDeEnergia.fecharConexao();
        return contas;
    }

    public void atualizarContas(long id, ContaDeEnergia conta) {
        repositorioContaDeEnergia.atualizarConta(id, conta);
        repositorioContaDeEnergia.fecharConexao();
    }
    public void removerConta(long id) {
        repositorioContaDeEnergia.removerConta(id);
        repositorioContaDeEnergia.fecharConexao();
    }

    public List<ContaDeEnergia> buscarContasPorCPF(String cpf){
        List<ContaDeEnergia> contaDeEnergias = repositorioContaDeEnergia.buscarContaPorCpf(cpf);
        repositorioContaDeEnergia.fecharConexao();
        return contaDeEnergias;
    }

}
