package globalSolution.infra.dao;

import globalSolution.dominio.ContaDeEnergia;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class ContaDeEnergiaDAOTest {

    @Test
    public void adicionandoConta(){
        ContaDeEnergia conta = new ContaDeEnergia(100, LocalDate.of(2024,12,10), 100, 1l);
        ContaDeEnergia conta2 = new ContaDeEnergia(100, LocalDate.of(2024,12,10), 100, 1l);
        ContaDeEnergiaDAO contaDeEnergiaDAO = new ContaDeEnergiaDAO();
        contaDeEnergiaDAO.adicionarConta(conta);
        contaDeEnergiaDAO.adicionarConta(conta2);
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
    }

    @Test
    public void buscandoContaPorID(){
        ContaDeEnergiaDAO contaDeEnergiaDAO = new ContaDeEnergiaDAO();
        ContaDeEnergia contaDeEnergia = contaDeEnergiaDAO.buscarContaPorId(1L);
        System.out.println("ID Conta: "+ contaDeEnergia.getIdContaDeEnergia());
        System.out.println("Valor Conta: "+ contaDeEnergia.getValorConta());
        System.out.println("Data Conta: "+ contaDeEnergia.getDataConta());
        System.out.println("Consumo kWh: :"+ contaDeEnergia.getConsumoKwh());
        System.out.println("ID Apartamento: "+ contaDeEnergia.getIdApartamento());
    }




}
