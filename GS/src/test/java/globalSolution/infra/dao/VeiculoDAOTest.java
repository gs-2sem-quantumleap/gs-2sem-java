package globalSolution.infra.dao;

import globalSolution.dominio.Morador;
import globalSolution.dominio.Veiculo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class VeiculoDAOTest {

    @Test
    public void testeAdicionandoVeiculo(){
        Veiculo veiculo = new Veiculo("1234567", 2002, true, 22L);
        //Veiculo veiculo2 = new Veiculo("1311567", 2002, true, 3L);
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        veiculoDAO.adicionarVeiculo(veiculo);
        //veiculoDAO.adicionarVeiculo(veiculo2);
        veiculoDAO.fecharConexao();
    }

    @Test
    public void buscandoVeiculoPorId() {
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        Veiculo veiculo = veiculoDAO.buscarVeiculoPorId(1l);

        if (veiculo == null) {
            System.out.println(".");
        } else {
            System.out.println("ID:" + veiculo.getIdVeiculo());
            System.out.println("Placa:" + veiculo.getPlacaVeiculo());
            System.out.println("Ano:" + veiculo.getAnoVeiculo());
            System.out.println("É eletrico:" + veiculo.isEletrico());
            System.out.println("ID Apartamento: " + veiculo.getIdApartamento());
        }
        veiculoDAO.fecharConexao();
    }

    @Test
    public void testListarVeiculos() {
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        ArrayList<Veiculo> veiculos = veiculoDAO.listarVeiculo();

        for (Veiculo veiculo : veiculos) {
            System.out.println("ID:" + veiculo.getIdVeiculo());
            System.out.println("Placa:" + veiculo.getPlacaVeiculo());
            System.out.println("Ano:" + veiculo.getAnoVeiculo());
            System.out.println("É eletrico:" + veiculo.isEletrico());
            System.out.println("ID Apartamento: " + veiculo.getIdApartamento());
            System.out.println("---------------------------");
        }
        veiculoDAO.fecharConexao();

    }

    @Test
    public void atualizarVeiculo() {
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        Veiculo veiculo = new Veiculo("NOVAPLA", 2002, false, 2L);
        long idMorador = 2L;

        if(veiculoDAO.buscarVeiculoPorId(idMorador) == null){
            System.out.println("ID Veiculo não encontrado.");
        }else {
            System.out.println("Veiculo atualizado com sucesso! .");
            veiculoDAO.atualizarVeiculo(idMorador, veiculo);
        }
    }

    @Test
    public void deletarMorador() {
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        long idVeiculo = 2l;
        veiculoDAO.removerVeiculo(idVeiculo);
        veiculoDAO.fecharConexao();
    }

    @Test
    public void buscandoVeiculoPorApartamento(){
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        long id = 22;
        List<Veiculo> veiculos = veiculoDAO.buscarVeiculosPorApartamento(id);
        for (Veiculo veiculo : veiculos) {
            System.out.println("ID:" + veiculo.getIdVeiculo());
            System.out.println("Placa:" + veiculo.getPlacaVeiculo());
            System.out.println("Ano:" + veiculo.getAnoVeiculo());
            System.out.println("É eletrico:" + veiculo.isEletrico());
            System.out.println("ID Apartamento: " + veiculo.getIdApartamento());
            System.out.println("---------------------------");
        }
        veiculoDAO.fecharConexao();

    }
}
