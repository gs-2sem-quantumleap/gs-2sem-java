package globalSolution.infra.dao;

import globalSolution.dominio.Apartamento;
import globalSolution.dominio.Condominio;
import globalSolution.dominio.ContaDeEnergia;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ApartamentoDAOTest {

    @Test
    public void testAdicionaApartamento(){
        Apartamento apartamento = new Apartamento(1, 2, 1);
        Apartamento outroApartamento = new Apartamento(2, 3, 1);

        ApartamentoDAO apartamentoDAO = new ApartamentoDAO();
        apartamentoDAO.adicionarApartamento(apartamento);
        apartamentoDAO.adicionarApartamento(outroApartamento);
        apartamentoDAO.fecharConexao();
    }

    @Test
    public void atualizarApartamento(){
        Apartamento apartamento = new Apartamento(10, 2, 1);
        ApartamentoDAO apartamentoDAO = new ApartamentoDAO();

        long id = 2l;
        apartamentoDAO.atualizarApartamento(id, apartamento);
        apartamentoDAO.fecharConexao();
    }

    @Test
    public void buscandoContasDeEnergiaPeloApartamento(){
        ApartamentoDAO apartamentoDAO = new ApartamentoDAO();
        long id = 1l;
        List<ContaDeEnergia> lista = apartamentoDAO.buscarTodasAsContasDeEnergia(id);
        for (ContaDeEnergia a : lista){
            System.out.println("Id Apartamento: "+a.getIdApartamento());
            System.out.println("ID Conta:"+a.getIdContaDeEnergia());
            System.out.println("Data: "+a.getDataConta());
            System.out.println("Valor conta: "+a.getValorConta());
        }
    }


}
