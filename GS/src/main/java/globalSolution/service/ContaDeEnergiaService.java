package globalSolution.service;

import globalSolution.dominio.ContaDeEnergia;
import globalSolution.dominio.RepositorioContaDeEnergia;

import java.util.ArrayList;

public class ContaDeEnergiaService {
    private RepositorioContaDeEnergia repositorioContaDeEnergia;

    public ContaDeEnergiaService(RepositorioContaDeEnergia repositorioContaDeEnergia) {
        this.repositorioContaDeEnergia = repositorioContaDeEnergia;
    }

    public void adicionarConta(ContaDeEnergia conta) {
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

}
