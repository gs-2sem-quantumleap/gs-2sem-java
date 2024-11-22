package globalSolution.service;

import globalSolution.dominio.Morador;
import globalSolution.dominio.RepositorioMorador;

import java.util.ArrayList;

public class MoradorService {
    private RepositorioMorador repositorioMorador;

    public MoradorService(RepositorioMorador repositorioMorador) {
        this.repositorioMorador = repositorioMorador;
    }

    public void adicionarMorador(Morador morador) {
        repositorioMorador.adicionarMorador(morador);
        repositorioMorador.fecharConexao();
    }

    public Morador buscarMoradorPorId(long id) {
        Morador morador =  repositorioMorador.buscarMoradorPorId(id);
        repositorioMorador.fecharConexao();
        return morador;
    }

    public ArrayList<Morador> listarMorador(){
        ArrayList<Morador> moradores = repositorioMorador.listarMorador();
        repositorioMorador.fecharConexao();
        return moradores;
    }

    public void atualizarMorador(long id,Morador morador) {
        repositorioMorador.atualizarMorador(id, morador);
        repositorioMorador.fecharConexao();
    }

    public void excluirMorador(long id) {
        repositorioMorador.removerMorador(id);
        repositorioMorador.fecharConexao();
    }

    public Morador buscarMoradorPorCpf(String cpf){
        Morador morador = repositorioMorador.buscarMoradorPorCpf(cpf);
        repositorioMorador.fecharConexao();
        return morador;
    }


}
