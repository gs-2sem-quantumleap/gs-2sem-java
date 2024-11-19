package globalSolution.infra.dao;

import globalSolution.dominio.ContaDeEnergia;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ContaDeEnergiaDAOTest {

    @Test
    public void adicionandoConta(){
        ContaDeEnergia conta = new ContaDeEnergia(100, LocalDate.of(2024,12,10), 100, 1l);
        ContaDeEnergia conta2 = new ContaDeEnergia(100, LocalDate.of(2024,12,10), 100, 1l);
        ContaDeEnergiaDAO contaDeEnergiaDAO = new ContaDeEnergiaDAO();
        contaDeEnergiaDAO.adicionarConta(conta);
        contaDeEnergiaDAO.adicionarConta(conta2);
        contaDeEnergiaDAO.fecharConexao();
    }

    @Test
    public void listandoContas(){
        ContaDeEnergiaDAO contaDeEnergiaDAO = new ContaDeEnergiaDAO();
        ArrayList<ContaDeEnergia> contas = contaDeEnergiaDAO.listarContas();

        for (ContaDeEnergia conta: contas){
            System.out.println("ID Conta: "+ conta.getIdContaDeEnergia());
            System.out.println("Valor Conta: "+ conta.getValorConta());
            System.out.println("Data Conta: "+ conta.getDataConta());
            System.out.println("Consumo kWh: :"+ conta.getConsumoKwh());
            System.out.println("ID Apartamento: "+ conta.getIdApartamento());
            System.out.println("-------------------------------------------");
        }
        contaDeEnergiaDAO.fecharConexao();
    }

    @Test
    public void buscandoContaPorID(){
        ContaDeEnergiaDAO contaDeEnergiaDAO = new ContaDeEnergiaDAO();
        ContaDeEnergia contaDeEnergia = contaDeEnergiaDAO.buscarContaPorId(1L);
        System.out.println("ID Conta: "+ contaDeEnergia.getIdContaDeEnergia());
        System.out.println("Valor Conta: "+ contaDeEnergia.getValorConta());
        System.out.println("Data Conta: "+ contaDeEnergia.getDataConta());
        System.out.println("Consumo kWh: "+ contaDeEnergia.getConsumoKwh());
        System.out.println("ID Apartamento: "+ contaDeEnergia.getIdApartamento());
        contaDeEnergiaDAO.fecharConexao();
    }

    @Test
    public void atualizandoConta(){
        ContaDeEnergiaDAO contaDeEnergiaDAO = new ContaDeEnergiaDAO();
        ContaDeEnergia conta = new ContaDeEnergia(111, LocalDate.of(2011,11,11), 111, 1l);
        contaDeEnergiaDAO.atualizarConta(2L, conta);
        contaDeEnergiaDAO.fecharConexao();
    }

    @Test
    public void apagarConta(){
        ContaDeEnergiaDAO contaDeEnergiaDAO = new ContaDeEnergiaDAO();
        contaDeEnergiaDAO.removerConta(2L);
        contaDeEnergiaDAO.fecharConexao();
    }

    @Test
    public void pesquisaDeContasPorCPF() {
        ContaDeEnergiaDAO contaDeEnergiaDAO = new ContaDeEnergiaDAO();
        List<ContaDeEnergia> contas = contaDeEnergiaDAO.buscarContaPorCpf("1");

        assertNotNull(contas, "A lista de contas deveria ser inicializada.");
        assertFalse(contas.isEmpty(), "Nenhuma conta encontrada para o CPF.");

        for (ContaDeEnergia conta : contas) {
            System.out.println("ID Conta: " + conta.getIdContaDeEnergia());
            System.out.println("Valor Conta: " + conta.getValorConta());
            System.out.println("Data Conta: " + conta.getDataConta());
            System.out.println("Consumo kWh: " + conta.getConsumoKwh());
            System.out.println("ID Apartamento: " + conta.getIdApartamento());
            System.out.println("-------------------------------------------");
        }
    }






}
