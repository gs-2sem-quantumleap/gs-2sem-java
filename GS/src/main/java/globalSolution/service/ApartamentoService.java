package globalSolution.service;

import globalSolution.dominio.Apartamento;
import globalSolution.dominio.RepositorioApartamento;
import java.util.List;

public class ApartamentoService {
    private RepositorioApartamento repositorioApartamento;

    public ApartamentoService(RepositorioApartamento repositorioApartamento) {
        this.repositorioApartamento = repositorioApartamento;
    }

    public void adicionarApartamento(Apartamento apartamento) {
        repositorioApartamento.adicionarApartamento(apartamento);
        repositorioApartamento.fecharConexao();
    }

    public Apartamento buscarApartamento(int id) {
        repositorioApartamento.buscarApartamentoPorID(id);
        return repositorioApartamento.buscarApartamentoPorID(id);
    }

    public List<Apartamento> buscarApartamentos() {
        List<Apartamento> apartamentos = repositorioApartamento.buscarTodosApartamentos();
        repositorioApartamento.fecharConexao();
        return apartamentos;
    }

    public void alterarApartamento(long id, Apartamento apartamento) {
        repositorioApartamento.atualizarApartamento(id,apartamento);
        repositorioApartamento.fecharConexao();
    }

    public void excluirApartamento(long id) {
        repositorioApartamento.deletarApartamento(id);
        repositorioApartamento.fecharConexao();
    }




}
