package globalSolution.service;

import globalSolution.dominio.Apartamento;
import globalSolution.dominio.Condominio;
import globalSolution.dominio.RepositorioCondominio;

import java.util.List;

public class CondominioService {
    private RepositorioCondominio repositorioCondominio;

    public CondominioService(RepositorioCondominio repositorioCondominio) {
        this.repositorioCondominio = repositorioCondominio;
    }

    public void adicionarCondominio(Condominio condominio) {
        repositorioCondominio.adicionarCondominio(condominio);
        repositorioCondominio.fecharConexao();
    }

    public List<Apartamento> listarTodosApartamentosPorCondominio(long id) {
        List<Apartamento> apartamentos = repositorioCondominio.listarTodosApartamentos(id);
        repositorioCondominio.fecharConexao();
        return apartamentos;
    }

    public List<Condominio> listarTodosCondominios(){
        List<Condominio> condominios = repositorioCondominio.listarTodosCondominios();
        repositorioCondominio.fecharConexao();
        return condominios;
    }

    public Condominio retornarCondominioPorId(long id) {
        Condominio condominio = repositorioCondominio.buscarCondominio(id);
        repositorioCondominio.fecharConexao();
        return condominio;
    }

    public void atualizarCondominio(long id, Condominio condominio) {
        repositorioCondominio.atualizarCondominio(id, condominio);
        repositorioCondominio.fecharConexao();
    }

    public void excluirCondominio(long id) {
        repositorioCondominio.removerCondiminio(id);
        repositorioCondominio.fecharConexao();
    }


}
