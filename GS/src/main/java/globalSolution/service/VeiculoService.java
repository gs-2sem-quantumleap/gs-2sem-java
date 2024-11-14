package globalSolution.service;

import globalSolution.dominio.Morador;
import globalSolution.dominio.RepositorioVeiculo;
import globalSolution.dominio.Veiculo;

import java.util.ArrayList;

public class VeiculoService {
    private RepositorioVeiculo repositorioVeiculo;

    public VeiculoService(RepositorioVeiculo repositorioVeiculo){
        this.repositorioVeiculo = repositorioVeiculo;
    }

    public void adicionar(Veiculo veiculo){
        repositorioVeiculo.adicionarVeiculo(veiculo);
        repositorioVeiculo.fecharConexao();
    }

    public Veiculo buscarMoradorPorID(long id){
        Veiculo veiculo = repositorioVeiculo.buscarVeiculoPorId(id);
        repositorioVeiculo.fecharConexao();
        return veiculo;
    }

    public ArrayList<Veiculo> listarVeiculos(){
        ArrayList<Veiculo> veiculos = repositorioVeiculo.listarVeiculo();
        repositorioVeiculo.fecharConexao();
        return veiculos;
    }

    public void atualizar(long id, Veiculo veiculo){
        repositorioVeiculo.atualizarVeiculo(id, veiculo);
        repositorioVeiculo.fecharConexao();
    }

    public void remover(long id){
        repositorioVeiculo.removerVeiculo(id);
        repositorioVeiculo.fecharConexao();
    }
}
