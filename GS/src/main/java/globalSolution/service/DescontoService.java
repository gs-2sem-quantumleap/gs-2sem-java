package globalSolution.service;

import globalSolution.dominio.Desconto;
import globalSolution.dominio.RepositorioDesconto;
import globalSolution.infra.dao.DescontoDAO;

import java.util.ArrayList;

public class DescontoService {
    private RepositorioDesconto repositorioDesconto;

    public DescontoService(RepositorioDesconto repositorioDesconto){
        this.repositorioDesconto = repositorioDesconto;
    }

    public void adicionar(Desconto desconto){
        repositorioDesconto.adicionarDesconto(desconto);
        repositorioDesconto.fecharConexao();
    }

    public ArrayList<Desconto> listaDesconto(){
        ArrayList<Desconto> descontos = repositorioDesconto.buscandoTodosDescontos();
        repositorioDesconto.fecharConexao();
        return descontos;
    }

    public void atualizarDesconto(long idDesconto, Desconto desconto){
        repositorioDesconto.atualizarDesconto(idDesconto, desconto);
        repositorioDesconto.fecharConexao();
    }

    public void removerDesconto(long idDesconto){
        repositorioDesconto.removerDesconto(idDesconto);
        repositorioDesconto.fecharConexao();
    }
}
