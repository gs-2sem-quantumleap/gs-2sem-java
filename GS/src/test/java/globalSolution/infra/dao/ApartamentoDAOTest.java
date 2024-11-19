package globalSolution.infra.dao;

import globalSolution.dominio.Apartamento;
import globalSolution.dominio.Condominio;
import globalSolution.dominio.ConsumoMorador;
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
        Apartamento apartamento = new Apartamento(100, 2, 1);
        ApartamentoDAO apartamentoDAO = new ApartamentoDAO();
        long id = 2l;
        apartamentoDAO.atualizarApartamento(id, apartamento);
        apartamentoDAO.fecharConexao();
    }

    @Test
    public void buscarTodosApartamentos(){
        ApartamentoDAO apartamentoDAO = new ApartamentoDAO();
        List<Apartamento> apartamentos = apartamentoDAO.buscarTodosApartamentos();

        for (Apartamento apartamento : apartamentos) {
            System.out.println("ID: "+ apartamento.getIdApartamento());
            System.out.println("Numero Apartamento: "+ apartamento.getNumeroApartamento());
            System.out.println("Id Morador: "+ apartamento.getIdMorador());
            System.out.println("Id condominio: "+ apartamento.getIdCondominio());
            System.out.println("---------------------------------");
        }
        apartamentoDAO.fecharConexao();
    }

    @Test
    public void buscarApartamentoPorId(){
        ApartamentoDAO apartamentoDAO = new ApartamentoDAO();
        Apartamento apartamento = apartamentoDAO.buscarApartamentoPorID(2l);
        System.out.println("ID: "+ apartamento.getIdApartamento());
        System.out.println("Numero Apartamento: "+ apartamento.getNumeroApartamento());
        System.out.println("Id Morador: "+ apartamento.getIdMorador());
        System.out.println("Id condominio: "+ apartamento.getIdCondominio());
        System.out.println("---------------------------------");
        apartamentoDAO.fecharConexao();
    }

    @Test
    public void apagarApartamento(){
        ApartamentoDAO apartamentoDAO = new ApartamentoDAO();
        apartamentoDAO.deletarApartamento(2l);
        apartamentoDAO.fecharConexao();
    }

    @Test
    public void buscarDadosConsumoEnergiaTotal(){
        ApartamentoDAO apartamentoDAO = new ApartamentoDAO();
        List<ConsumoMorador> lista = apartamentoDAO.buscarDadosConsumoEnergiaTotal();
        for (ConsumoMorador linha : lista) {
            System.out.printf("Morador: " +linha.getNomeMorador());
            System.out.println("Apartamento: "+ linha.getNumeroApartamento());
            System.out.println("Consumo: "+ linha.getConsumoTotalKwh());
            System.out.println("----------------");
        }
    }

    @Test
    public void buscarTop3MenoresConsumos(){
        ApartamentoDAO apartamentoDAO = new ApartamentoDAO();
        List<ConsumoMorador> lista = apartamentoDAO.buscarTop3MenoresConsumos();
        for (ConsumoMorador linha : lista) {
            System.out.printf("Morador: " +linha.getNomeMorador());
            System.out.println("Apartamento: "+ linha.getNumeroApartamento());
            System.out.println("Consumo: "+ linha.getConsumoTotalKwh());
            System.out.println("----------------");
        }
    }




}
